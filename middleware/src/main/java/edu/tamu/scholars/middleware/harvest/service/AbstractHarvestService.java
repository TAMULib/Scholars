package edu.tamu.scholars.middleware.harvest.service;

import static edu.tamu.scholars.middleware.harvest.service.helper.SolrDocumentBuilder.parse;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.shared.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.service.SolrIndexService;
import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;
import edu.tamu.scholars.middleware.harvest.annotation.Property;
import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;
import edu.tamu.scholars.middleware.harvest.service.helper.SolrDocumentBuilder;
import edu.tamu.scholars.middleware.service.TemplateService;
import edu.tamu.scholars.middleware.service.Triplestore;

public abstract class AbstractHarvestService<D extends AbstractSolrDocument, S extends SolrIndexService<D>> implements HarvestService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private Triplestore triplestore;

    @Autowired
    private S indexer;

    public void harvest() {
        triplestore.open();
        CollectionSource source = indexer.type().getAnnotation(CollectionSource.class);
        String query = templateService.templateSparql(source.template(), resolve(source.key()));
        triplestore.dataset().getLock().enterCriticalSection(Lock.READ);
        try (QueryExecution qe = QueryExecutionFactory.create(query, triplestore.dataset())) {
            Model collection = qe.execConstruct();
            ResIterator resources = collection.listSubjects();
            if (resources.hasNext()) {
                Iterable<Resource> resourceIterable = () -> resources;
                Stream<Resource> resourceStream = StreamSupport.stream(resourceIterable.spliterator(), true);
                resourceStream.forEach(resource -> harvest(SolrDocumentBuilder.of(collection, resource, indexer.type())));
            } else {
                logger.warn(String.format("No %s found!", name()));
            }
        } finally {
            triplestore.dataset().getLock().leaveCriticalSection();
        }
        triplestore.close();
    }

    public String name() {
        return indexer.type().getSimpleName();
    }

    private void harvest(SolrDocumentBuilder builder) {
        Instant start = Instant.now();
        try {
            D document = createDocument(builder);
            indexer.index(document);
            logger.info(String.format("%s %s indexed in %f seconds", name(), parse(builder.getSubject()), Duration.between(start, Instant.now()).toMillis() / 1000.0));
            // System.exit(0);
        } catch (NullPointerException e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            logger.error(String.format("Unable to index %s: %s", name(), parse(builder.getSubject())));
            logger.error(String.format("Error: %s", e.getMessage()));
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }

    private D createDocument(SolrDocumentBuilder builder) throws Exception {
        D document = construct();
        lookupProperties(builder);
        populate(document, builder);
        return document;
    }

    private void lookupProperties(SolrDocumentBuilder builder) throws IllegalArgumentException, IllegalAccessException {
        for (Property property : builder.getCollectionSource().properties()) {
            builder.lookupProperty(property, resolve(property.key()));
        }
        for (Field field : builder.getFields()) {
            builder.setField(field);
            PropertySource source = field.getAnnotation(PropertySource.class);
            String query = templateService.templateSparql(source.template(), builder.getSubject());
            try (QueryExecution qe = QueryExecutionFactory.create(query, triplestore.dataset())) {
                Model model = qe.execConstruct();
                builder.setModel(model);
                String predicate = resolve(source.key());
                ResIterator resources = model.listSubjects();
                while (resources.hasNext()) {
                    builder.setResource(resources.next());
                    builder.lookupProperty(source, predicate);
                }
            }
        }
    }

    private void populate(D document, SolrDocumentBuilder builder) throws IllegalArgumentException, IllegalAccessException {
        for (Map.Entry<String, List<String>> entry : builder.getCollections().entrySet()) {
            List<String> values = entry.getValue();
            if (values.isEmpty()) {
                logger.debug(String.format("Could not find values for %s", entry.getKey()));
            } else {
                Field field = field(entry.getKey());
                field.setAccessible(true);
                if (Collection.class.isAssignableFrom(field.getType())) {
                    field.set(document, values);
                } else {
                    field.set(document, values.get(0));
                }
            }
        }
    }

    private String resolve(String key) {
        return environment.getProperty(key);
    }

    @SuppressWarnings("unchecked")
    private D construct() throws Exception {
        return (D) indexer.type().getConstructor().newInstance(new Object[0]);
    }

    private Field field(String name) {
        return FieldUtils.getField(indexer.type(), name, true);
    }

}

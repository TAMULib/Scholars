package edu.tamu.scholars.middleware.harvest.service;

import static edu.tamu.scholars.middleware.harvest.service.helper.SolrDocumentBuilder.parse;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessResourceFailureException;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.service.SolrIndexService;
import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;
import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;
import edu.tamu.scholars.middleware.harvest.service.helper.SolrDocumentBuilder;
import edu.tamu.scholars.middleware.service.TemplateService;
import edu.tamu.scholars.middleware.service.Triplestore;

public abstract class AbstractHarvestService<D extends AbstractSolrDocument, S extends SolrIndexService<D>> implements HarvestService {

    private final static String COLLECTION_SPARQL_TEMPLATE = "collection";

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
        CollectionSource source = indexer.type().getAnnotation(CollectionSource.class);
        String query = templateService.templateSparql(COLLECTION_SPARQL_TEMPLATE, resolve(source.key()));
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s:\n%s", COLLECTION_SPARQL_TEMPLATE, query));
        }
        try (QueryExecution qe = QueryExecutionFactory.create(query, triplestore.dataset())) {
            Iterator<Triple> tripleIterator = qe.execConstructTriples();
            if (tripleIterator.hasNext()) {
                Iterable<Triple> tripleIterable = () -> tripleIterator;
                Stream<Triple> tripleStream = StreamSupport.stream(tripleIterable.spliterator(), true);
                tripleStream.forEach(triple -> harvest(triple));
            } else {
                logger.warn(String.format("No %s found!", name()));
            }
        }
    }

    public String name() {
        return indexer.type().getSimpleName();
    }

    private void harvest(Triple triple) {
        String subject = triple.getSubject().toString();
        Instant start = Instant.now();
        try {
            D document = createDocument(SolrDocumentBuilder.of(subject, indexer.type()));
            indexer.index(document);
            logger.info(String.format("%s %s indexed in %f seconds", name(), parse(subject), Duration.between(start, Instant.now()).toMillis() / 1000.0));
        } catch (DataAccessResourceFailureException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            logger.error(String.format("Unable to index %s: %s", name(), parse(subject)));
            logger.error(String.format("Error: %s", e.getMessage()));
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
    }

    private D createDocument(SolrDocumentBuilder builder) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        D document = construct();
        lookupProperties(builder);
        populate(document, builder);
        return document;
    }

    private void lookupProperties(SolrDocumentBuilder builder) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : builder.getPropertySourceFields()) {
            builder.setField(field);
            PropertySource source = field.getAnnotation(PropertySource.class);
            String query = templateService.templateSparql(source.template(), builder.getSubject());
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("%s:\n%s", source.template(), query));
            }
            try (QueryExecution qe = QueryExecutionFactory.create(query, triplestore.dataset())) {
                Model model = qe.execConstruct();
                if (logger.isDebugEnabled()) {
                    model.write(System.out, "RDF/XML");
                }
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
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("Could not find values for %s", entry.getKey()));
                }
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
    private D construct() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return (D) indexer.type().getConstructor().newInstance(new Object[0]);
    }

    private Field field(String name) {
        Field field = FieldUtils.getField(indexer.type(), name, true);
        if (field != null) {
            return field;
        }
        throw new RuntimeException(String.format("%s does not have property %s!", name(), name));
    }

}

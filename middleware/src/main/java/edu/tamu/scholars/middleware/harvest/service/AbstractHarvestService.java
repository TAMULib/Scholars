package edu.tamu.scholars.middleware.harvest.service;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sdb.SDB;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;
import org.apache.jena.shared.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import edu.tamu.scholars.middleware.config.TriplestoreConfig;
import edu.tamu.scholars.middleware.config.VivoConfig;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.service.SolrIndexService;
import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;
import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;
import edu.tamu.scholars.middleware.harvest.service.helper.SolrDocumentBuilder;
import edu.tamu.scholars.middleware.service.HttpService;
import edu.tamu.scholars.middleware.service.TemplateService;
import edu.tamu.scholars.middleware.service.request.HttpRequest;

public abstract class AbstractHarvestService<D extends AbstractSolrDocument, S extends SolrIndexService<D>> implements HarvestService {

    private final static String RDF_XML_LANG = "RDF/XML";

    private final static String FORWARD_SLASH = "/";

    private final static String HASH_TAG = "#";

    private final static String ID = "id";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private HttpService httpService;

    @Autowired
    private VivoConfig vivoConfig;

    @Autowired
    private S indexer;

    private Store store;

    private Dataset dataset;

    public void harvest() {
        openTriplestore();
        CollectionSource source = indexer.type().getAnnotation(CollectionSource.class);
        Model listModel = list(resolve(source.key()));
        StmtIterator stmtIterator = listModel.listStatements();
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "16");
        if (stmtIterator.hasNext()) {
            Iterable<Statement> stmtIterable = () -> stmtIterator;
            Stream<Statement> stmtStream = StreamSupport.stream(stmtIterable.spliterator(), false);
            stmtStream.parallel().forEach(statement -> {
                Instant start = Instant.now();
                String subject = statement.getSubject().toString();
                try {
                    D document = createDocument(subject);
                    indexer.index(document);
                    logger.info(String.format("%s %s indexed in %f seconds", name(), parse(subject), Duration.between(start, Instant.now()).toMillis() / 1000.0));
                    // System.exit(0);
                } catch (NullPointerException e) {
                    if (logger.isDebugEnabled()) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    logger.error(String.format("Unable to index %s: %s", name(), parse(subject)));
                    logger.error(String.format("Error: %s", e.getMessage()));
                    if (logger.isDebugEnabled()) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            logger.warn(String.format("No %s found!", name()));
        }
        closeTriplestore();
    }

    public String name() {
        return indexer.type().getSimpleName();
    }

    protected Model list(String predicate) {
        logger.debug("Listing");
        logger.debug("   predicate: " + predicate);
        String response = httpService.get(HttpRequest.listRdf(vivoConfig.getListRdfEndpointUrl(), predicate));
        logger.debug("   response:\n" + response);
        return toRdfModel(response);
    }

    protected Model individual(String id) {
        logger.debug("Getting individual");
        logger.debug("   id: " + id);
        String response = httpService.get(HttpRequest.linkedOpenDataRdf(vivoConfig.getLinkedOpenDataEndpointUrl(), id));
        logger.debug("   response:\n" + response);
        return toRdfModel(response);
    }

    protected Model construct(String query) {
        logger.debug("Constructing");
        logger.debug("   query: " + query);
        if (vivoConfig.isDirectSparQL()) {
            dataset.getLock().enterCriticalSection(Lock.READ);
            try (QueryExecution qe = QueryExecutionFactory.create(query, dataset)) {
                return qe.execConstruct();
            } finally {
                dataset.getLock().leaveCriticalSection();
            }
        } else {
            String response = httpService.get(HttpRequest.sparqlRdf(vivoConfig.getSparqlQueryEndpointUrl(), vivoConfig.getEmail(), vivoConfig.getPassword(), query));
            logger.debug(" response:\n" + response);
            return toRdfModel(response);
        }
    }

    private void openTriplestore() {
        // NOTE: only supporting SDB with MySQL for now
        if (vivoConfig.isDirectSparQL()) {
            TriplestoreConfig tripleStoreConfig = vivoConfig.getTriplestore();
            SDB.getContext().set(SDB.unionDefaultGraph, true);
            StoreDesc storeDesc = new StoreDesc(LayoutType.fetch(tripleStoreConfig.getLayoutType()), DatabaseType.fetch(tripleStoreConfig.getDatabaseType()));
            SDBConnection conn = new SDBConnection(tripleStoreConfig.getDatasourceUrl(), tripleStoreConfig.getUsername(), tripleStoreConfig.getPassword());
            store = SDBFactory.connectStore(conn, storeDesc);
            dataset = SDBFactory.connectDataset(store);
        }
    }

    private void closeTriplestore() {
        if (vivoConfig.isDirectSparQL()) {
            store.getConnection().close();
            store.close();
            dataset.close();
        }
    }

    private D createDocument(String subject) throws Exception {
        D document = construct();
        Field idField = field(ID);
        idField.setAccessible(true);
        idField.set(document, parse(subject));
        SolrDocumentBuilder builder = SolrDocumentBuilder.of(subject, indexer.type());
        lookupProperties(builder);
        populate(document, builder);
        return document;
    }

    private void lookupProperties(SolrDocumentBuilder builder) throws IllegalArgumentException, IllegalAccessException {
        builder.getFields().forEach(field -> {
            PropertySource source = field.getAnnotation(PropertySource.class);
            String query = templateService.templateSparql(source.template(), builder.getSubject());
            Model model = construct(query);
            builder.setField(field);
            builder.setModel(model);
            lookupProperty(builder);
        });
    }

    private void lookupProperty(SolrDocumentBuilder builder) {
        String name = builder.getPropertyName();
        PropertySource source = builder.getPropertySource();
        String predicate = resolve(source.key());
        Model model = builder.getModel();
        // NOTE: this could be more efficient if the resource and property are known
        StmtIterator statements = model.listStatements();
        while (statements.hasNext()) {
            Statement statement = statements.next();
            // System.out.println(statement);
            if (statement != null) {
                String object = statement.getObject().toString();
                if (statement.getPredicate().toString().equals(predicate)) {
                    builder.add(name, source.parse() ? parse(object) : object);
                    if (!source.id().isEmpty()) {
                        String subject = statement.getSubject().toString();
                        builder.add(source.id(), parse(subject));
                    }
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

    private Model toRdfModel(String rdf) {
        InputStream stream = new ByteArrayInputStream(rdf.getBytes(UTF_8));
        Model model = ModelFactory.createDefaultModel();
        model.read(stream, null, RDF_XML_LANG);
        return model;
    }

    private String parse(String uri) {
        return uri.substring(uri.lastIndexOf(uri.contains(HASH_TAG) ? HASH_TAG : FORWARD_SLASH) + 1);
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

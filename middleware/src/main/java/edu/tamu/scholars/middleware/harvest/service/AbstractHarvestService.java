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

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import edu.tamu.scholars.middleware.config.VivoConfig;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.service.SolrIndexService;
import edu.tamu.scholars.middleware.harvest.annotation.Source;
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

    public void harvest() {
        Source source = indexer.type().getAnnotation(Source.class);
        Model listModel = list(resolve(source.key()));
        StmtIterator stmtIterator = listModel.listStatements();
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
    }

    public String name() {
        return indexer.type().getSimpleName();
    }

    protected Model list(String predicate) {
        String response = httpService.get(HttpRequest.listRdf(vivoConfig.getListRdfEndpointUrl(), predicate));
        logger.debug("Listing");
        logger.debug("   predicate: " + predicate);
        logger.debug("   response:\n" + response);
        return toRdfModel(response);
    }

    protected Model individual(String id) {
        String response = httpService.get(HttpRequest.linkedOpenDataRdf(vivoConfig.getLinkedOpenDataEndpointUrl(), id));
        logger.debug("Getting individual");
        logger.debug("   id: " + id);
        logger.debug("   response:\n" + response);
        return toRdfModel(response);
    }

    protected Model construct(String query) {
        String response = httpService.get(HttpRequest.sparqlRdf(vivoConfig.getSparqlQueryEndpointUrl(), vivoConfig.getEmail(), vivoConfig.getPassword(), query));
        logger.debug("Constructing");
        logger.debug("   query: " + query);
        logger.debug("   response:\n" + response);
        return toRdfModel(response);
    }

    private D createDocument(String subject) throws Exception {
        D document = construct();

        Field idField = field(ID);
        idField.setAccessible(true);
        idField.set(document, parse(subject));

        Source source = indexer.type().getAnnotation(Source.class);
        SolrDocumentBuilder builder = SolrDocumentBuilder.of(subject, source);

        lookup(builder);
        populate(document, builder);

        return document;
    }

    private void lookup(SolrDocumentBuilder builder) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        Source source = builder.getSource();
        for (Source.Sparql sparql : source.sparql()) {
            String query = templateService.templateSparql(sparql.template(), builder.getSubject());
            // NOTE: model response of a SparQL query must be homogeneous subjects, i.e. only one subject defined in the CONSTRUCT
            Model model = construct(query);
            builder.setModel(model);
            for (Source.Property property : sparql.properties()) {
                lookup(builder, property);
            }
        }
    }

    private void lookup(SolrDocumentBuilder builder, Source.Property property) {
        String name = property.name();
        String predicate = resolve(property.key());
        Model model = builder.getModel();
        // NOTE: this could be more efficient if the resource and property are known
        StmtIterator statements = model.listStatements();
        while (statements.hasNext()) {
            Statement statement = statements.next();
            // System.out.println(statement);
            if (statement != null) {
                String object = statement.getObject().toString();
                if (statement.getPredicate().toString().equals(predicate)) {
                    builder.add(name, property.parse() ? parse(object) : object);
                    if (!property.id().isEmpty()) {
                        String subject = statement.getSubject().toString();
                        builder.add(property.id(), parse(subject));
                    }
                }
            }
        }
    }

    private void populate(D document, SolrDocumentBuilder builder) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
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

    private Field field(String name) throws NoSuchFieldException, SecurityException {
        return getField(indexer.type(), name);
    }

    private Field getField(Class<?> clazz, String name) throws NoSuchFieldException, SecurityException {
        Field field = null;
        try {
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            // do nothing
        }
        if (field != null) {
            return field;
        } else if (clazz.getSuperclass() != null) {
            return getField(clazz.getSuperclass(), name);
        } else {
            throw new NoSuchFieldException(String.format("Unabled to find %s", name));
        }
    }

}

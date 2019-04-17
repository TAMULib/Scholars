package edu.tamu.scholars.middleware.discovery.service.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.shared.InvalidPropertyURIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

public class SolrDocumentBuilder {

    private final static Logger logger = LoggerFactory.getLogger(SolrDocumentBuilder.class);

    private final static String ID_PROPERTY_NAME = "id";

    private final static String FORWARD_SLASH = "/";

    private final static String HASH_TAG = "#";

    private final Map<String, List<String>> collections;

    private final String subject;

    private final Class<?> type;

    public SolrDocumentBuilder(String subject, Class<?> type) {
        this.subject = subject;
        this.type = type;
        this.collections = new HashMap<String, List<String>>();
        for (Field field : FieldUtils.getFieldsListWithAnnotation(this.type, PropertySource.class)) {
            this.collections.put(field.getName(), new ArrayList<String>());
        }
        List<String> id = new ArrayList<String>();
        id.add(parse(subject));
        this.collections.put(ID_PROPERTY_NAME, id);
    }

    public Map<String, List<String>> getCollections() {
        return collections;
    }

    public String getSubject() {
        return subject;
    }

    public Class<?> getType() {
        return type;
    }

    public void lookupProperty(String property, PropertySource source, Model model) {
        ResIterator resources = model.listSubjects();
        while (resources.hasNext()) {
            Resource resource = resources.next();
            lookupProperty(property, source, model, resource);
        }
    }

    public void lookupProperty(String property, PropertySource source, Model model, Resource resource) {
        StmtIterator statements;
        try {
            statements = resource.listProperties(model.createProperty(source.predicate()));
        } catch (InvalidPropertyURIException exception) {
            logger.error(String.format("%s lookup by %s", property, source.predicate()));
            throw exception;
        }
        while (statements.hasNext()) {
            Statement statement = statements.next();
            String object = statement.getObject().toString();
            String value = source.parse() ? parse(object) : object;
            if (this.collections.containsKey(property)) {

                if (value.contains("^^")) {
                    value = value.substring(0, value.indexOf("^^"));
                }

                List<String> values = this.collections.get(property);
                if (source.unique() && values.stream().anyMatch(value::equalsIgnoreCase)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(String.format("%s has duplicate value %s", property, value));
                    }
                } else {
                    values.add(value);
                }
            }
        }
    }

    public static String parse(String uri) {
        return uri.substring(uri.lastIndexOf(uri.contains(HASH_TAG) ? HASH_TAG : FORWARD_SLASH) + 1);
    }

    public static SolrDocumentBuilder of(String subject, Class<?> type) {
        return new SolrDocumentBuilder(subject, type);
    }

}

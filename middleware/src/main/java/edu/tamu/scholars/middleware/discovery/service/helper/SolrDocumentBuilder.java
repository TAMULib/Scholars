package edu.tamu.scholars.middleware.discovery.service.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.shared.InvalidPropertyURIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

public class SolrDocumentBuilder {

    private final static Logger logger = LoggerFactory.getLogger(SolrDocumentBuilder.class);

    private final static String FORWARD_SLASH = "/";

    private final static String HASH_TAG = "#";

    public final static String ID_PROPERTY_NAME = "id";

    public final static String NESTED_ID_DELIMITER = "::";

    private final static String NESTED_VALUE_TEMPLATE = NESTED_ID_DELIMITER + "%s";

    private final Map<String, List<String>> collections;

    private final String subject;

    private final Class<?> type;

    private Field field;

    private Model model;

    private Resource resource;

    private String[] predicates;

    public SolrDocumentBuilder(String subject, Class<?> type) {
        this.subject = subject;
        this.type = type;
        this.collections = new HashMap<String, List<String>>();
        for (Field field : getPropertySourceFields()) {
            this.collections.put(field.getName(), new ArrayList<String>());
        }
        List<String> id = new ArrayList<String>();
        id.add(parse(subject));
        this.collections.put(ID_PROPERTY_NAME, id);
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String[] getPredicates() {
        return predicates;
    }

    public void setPredicates(String[] predicates) {
        this.predicates = predicates;
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

    public CollectionSource getCollectionSource() {
        return type.getAnnotation(CollectionSource.class);
    }

    public String getPropertyName() {
        return field.getName();
    }

    public PropertySource getPropertySource() {
        return field.getAnnotation(PropertySource.class);
    }

    public List<Field> getPropertySourceFields() {
        return FieldUtils.getFieldsListWithAnnotation(type, PropertySource.class);
    }

    public void lookupProperty(PropertySource source, String predicate) {
        lookupProperty(PropertyLookup.of(predicate, field.getName(), source));
    }

    public void lookupProperty(PropertyLookup lookup) {
        Model model = getModel();
        Resource resource = getResource();
        StmtIterator statements;
        try {
            statements = resource.listProperties(model.createProperty(lookup.getPredicate()));
        } catch (InvalidPropertyURIException exception) {
            logger.error(String.format("%s lookup by %s", lookup.getName(), lookup.getPredicate()));
            throw exception;
        }
        while (statements.hasNext()) {
            Statement statement = statements.next();
            String object = statement.getObject().toString();
            String value = lookup.isParse() ? parse(object) : object;
            if (this.collections.containsKey(lookup.getName())) {
                if (value.contains("^^")) {
                    value = value.substring(0, value.indexOf("^^"));
                }
                List<String> values = this.collections.get(lookup.getName());
                if (lookup.isUnique() && values.stream().anyMatch(value::equalsIgnoreCase)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug(String.format("%s has duplicate value %s", lookup.getName(), value));
                    }
                } else {
                    if (lookup.isNested() || lookup.isId()) {
                        value += String.format(NESTED_VALUE_TEMPLATE, parse(statement.getSubject().toString()));
                    }
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

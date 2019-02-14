package edu.tamu.scholars.middleware.harvest.service.helper;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;
import edu.tamu.scholars.middleware.harvest.annotation.Property;
import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;

public class SolrDocumentBuilder {

    private final static Logger logger = LoggerFactory.getLogger(SolrDocumentBuilder.class);

    private final static String FORWARD_SLASH = "/";

    private final static String HASH_TAG = "#";

    private final static String ID = "id";

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
            PropertySource source = field.getAnnotation(PropertySource.class);
            this.collections.put(field.getName(), new ArrayList<String>());
            if (!source.id().isEmpty()) {
                this.collections.put(source.id(), new ArrayList<String>());
            }
        }
        for (Property property : getCollectionSource().properties()) {
            this.collections.put(property.name(), new ArrayList<String>());
            if (!property.id().isEmpty()) {
                this.collections.put(property.id(), new ArrayList<String>());
            }
        }
        this.collections.put(ID, new ArrayList<String>());
        add(ID, parse(subject), true);
    }

    public SolrDocumentBuilder(Model model, Resource resource, Class<?> type) {
        this(resource.getURI(), type);
        this.model = model;
        this.resource = resource;
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

    public void lookupProperty(Property property, String predicate) {
        lookupProperty(PropertyLookup.of(predicate, property));
    }

    public void lookupProperty(PropertySource source, String predicate) {
        lookupProperty(PropertyLookup.of(predicate, field.getName(), source));
    }

    public void lookupProperty(PropertyLookup lookup) {
        Model model = getModel();
        Resource resource = getResource();
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("%s lookup by %s", lookup.getName(), lookup.getPredicate()));
        }
        StmtIterator statements = resource.listProperties(model.createProperty(lookup.getPredicate()));
        while (statements.hasNext()) {
            Statement statement = statements.next();
            String object = statement.getObject().toString();
            String value = lookup.isParse() ? parse(object) : object;
            if (add(lookup.getName(), value, lookup.isUnique())) {
                if (lookup.hasId()) {
                    String subject = statement.getSubject().toString();
                    add(lookup.getId(), parse(subject), false);
                }
            }
        }
    }

    public boolean add(String property, String value, boolean unique) {
        if (this.collections.containsKey(property)) {
            if (value.contains("^^")) {
                value = value.substring(0, value.indexOf("^^"));
            }
            List<String> values = this.collections.get(property);
            if (unique && values.stream().anyMatch(value::equalsIgnoreCase)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("%s has duplicate value %s", property, value));
                }
            } else {
                values.add(value);
                return true;
            }
        }
        return false;
    }

    public static String parse(String uri) {
        return uri.substring(uri.lastIndexOf(uri.contains(HASH_TAG) ? HASH_TAG : FORWARD_SLASH) + 1);
    }

    public static SolrDocumentBuilder of(Model model, Resource resource, Class<?> type) {
        return new SolrDocumentBuilder(model, resource, type);
    }

    public static SolrDocumentBuilder of(String subject, Class<?> type) {
        return new SolrDocumentBuilder(subject, type);
    }

}

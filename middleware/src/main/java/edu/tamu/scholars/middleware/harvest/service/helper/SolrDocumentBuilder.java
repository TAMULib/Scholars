package edu.tamu.scholars.middleware.harvest.service.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;

public class SolrDocumentBuilder {

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
        getFields().forEach(field -> {
            PropertySource source = field.getAnnotation(PropertySource.class);
            this.collections.put(field.getName(), new ArrayList<String>());
            if (!source.id().isEmpty()) {
                this.collections.put(source.id(), new ArrayList<String>());
            }

        });
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

    public String getPropertyName() {
        return field.getName();
    }

    public PropertySource getPropertySource() {
        return field.getAnnotation(PropertySource.class);
    }

    public List<Field> getFields() {
        return FieldUtils.getFieldsListWithAnnotation(type, PropertySource.class);
    }

    public void add(String property, String value) {
        if (this.collections.containsKey(property)) {
            if (value.contains("^^")) {
                value = value.substring(0, value.indexOf("^^"));
            }
            this.collections.get(property).add(value);
        }
    }

    public static SolrDocumentBuilder of(String subject, Class<?> type) {
        return new SolrDocumentBuilder(subject, type);
    }

}

package edu.tamu.scholars.middleware.harvest.service.helper;

import edu.tamu.scholars.middleware.harvest.annotation.Property;
import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;

public class PropertyLookup {

    private final String predicate;

    private final String name;

    private final String id;

    private final boolean parse;

    public PropertyLookup(String predicate, String name, String id, boolean parse) {
        this.predicate = predicate;
        this.name = name;
        this.id = id;
        this.parse = parse;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public boolean hasId() {
        return !id.isEmpty();
    }

    public boolean isParse() {
        return parse;
    }

    public static PropertyLookup of(String predicate, String propertyName, PropertySource source) {
        return new PropertyLookup(predicate, propertyName, source.id(), source.parse());
    }

    public static PropertyLookup of(String predicate, Property property) {
        return new PropertyLookup(predicate, property.name(), property.id(), property.parse());
    }

}
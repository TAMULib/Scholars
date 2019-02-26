package edu.tamu.scholars.middleware.discovery.service.helper;

import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

public class PropertyLookup {

    private final String predicate;

    private final String name;

    private final String id;

    private final boolean parse;

    private final boolean unique;

    public PropertyLookup(String predicate, String name, String id, boolean parse, boolean unique) {
        this.predicate = predicate;
        this.name = name;
        this.id = id;
        this.parse = parse;
        this.unique = unique;
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

    public boolean isUnique() {
        return unique;
    }

    public static PropertyLookup of(String predicate, String propertyName, PropertySource source) {
        return new PropertyLookup(predicate, propertyName, source.id(), source.parse(), source.unique());
    }

}
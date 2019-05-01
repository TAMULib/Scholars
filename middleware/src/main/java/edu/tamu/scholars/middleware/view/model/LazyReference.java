package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Embeddable;

@Embeddable
public class LazyReference {

    private String field;

    private String collection;

    public LazyReference() {

    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

}

package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import edu.tamu.scholars.middleware.view.annotation.ValidResourceCollection;

@MappedSuperclass
public abstract class ResourceView extends View {

    private static final long serialVersionUID = -6885745913576337584L;

    @ValidResourceCollection(message = "{ResourceView.validResourceCollection}")
    @Column(nullable = false)
    private String collection;

    public ResourceView() {
        super();
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

}

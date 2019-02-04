package edu.tamu.scholars.middleware.discovery.model;

import org.springframework.data.annotation.Id;

public abstract class AbstractSolrDocument {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

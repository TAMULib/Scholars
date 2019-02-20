package edu.tamu.scholars.middleware.discovery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;

public abstract class AbstractSolrDocument {

    @Id
    @Indexed
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

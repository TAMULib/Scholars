package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.solr.core.query.FacetOptions;

@Embeddable
public class Facet {

    @Column(nullable = false)
    private String field;

    @Column(nullable = false)
    private int limit;

    @Enumerated(EnumType.STRING)
    private FacetOptions.FacetSort sort;

    public Facet() {
        limit = 10;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public FacetOptions.FacetSort getSort() {
        return sort;
    }

    public void setSort(FacetOptions.FacetSort sort) {
        this.sort = sort;
    }

}

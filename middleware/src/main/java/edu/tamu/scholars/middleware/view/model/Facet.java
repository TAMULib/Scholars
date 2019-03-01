package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;

@Embeddable
public class Facet {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String field;

    @Column(nullable = false, name = "default_limit")
    private int limit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "default_sort")
    private FacetOptions.FacetSort sort;

    public Facet() {
        limit = 10;
        sort = FacetSort.COUNT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

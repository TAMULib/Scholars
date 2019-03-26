package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.FacetOptions;

@Embeddable
public class Facet {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String field;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FacetOptions.FacetSort sort;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sort.Direction direction;

    @Column(nullable = false, name = "default_limit")
    private int limit;

    @Column(nullable = false)
    private boolean hidden;

    public Facet() {
        sort = FacetOptions.FacetSort.COUNT;
        direction = Sort.Direction.DESC;
        limit = 10;
        hidden = false;
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

    public FacetOptions.FacetSort getSort() {
        return sort;
    }

    public void setSort(FacetOptions.FacetSort sort) {
        this.sort = sort;
    }

    public Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

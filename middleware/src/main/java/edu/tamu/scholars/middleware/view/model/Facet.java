package edu.tamu.scholars.middleware.view.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;

import edu.tamu.scholars.middleware.discovery.model.SdrFacetEntry;

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

    @Column(nullable = false)
    private boolean hidden;

    @Transient
    private List<SdrFacetEntry> entries;

    @Transient
    private Pageable page;

    public Facet() {
        limit = 10;
        sort = FacetSort.COUNT;
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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<SdrFacetEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<SdrFacetEntry> entries) {
        this.entries = entries;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

}

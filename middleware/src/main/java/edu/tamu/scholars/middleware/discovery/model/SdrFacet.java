package edu.tamu.scholars.middleware.discovery.model;

import java.util.List;

import org.springframework.data.domain.Pageable;

public class SdrFacet {

    private String field;

    private List<SdrFacetEntry> entries;

    private Pageable page;

    public SdrFacet() {

    }

    public SdrFacet(String field, List<SdrFacetEntry> entries, Pageable page) {
        super();
        this.field = field;
        this.entries = entries;
        this.page = page;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

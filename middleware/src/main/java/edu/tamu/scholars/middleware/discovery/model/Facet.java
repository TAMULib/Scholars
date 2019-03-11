package edu.tamu.scholars.middleware.discovery.model;

import java.util.List;

import org.springframework.data.domain.Pageable;

public class Facet {

    private String field;

    private List<Entry> entries;

    private Pageable page;

    public Facet() {

    }

    public Facet(String field, List<Entry> entries, Pageable page) {
        super();
        this.field = field;
        this.entries = entries;
        this.page = page;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public static class Entry {

        private String value;

        private long count;

        public Entry() {

        }

        public Entry(String value, long count) {
            super();
            this.value = value;
            this.count = count;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

    }

}

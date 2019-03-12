package edu.tamu.scholars.middleware.discovery.model;

public class SdrFacetEntry {

    private String value;

    private long count;

    public SdrFacetEntry() {

    }

    public SdrFacetEntry(String value, long count) {
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

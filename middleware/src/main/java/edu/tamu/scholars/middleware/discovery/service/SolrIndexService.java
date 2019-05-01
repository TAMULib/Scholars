package edu.tamu.scholars.middleware.discovery.service;

public interface SolrIndexService {

    public void index();

    public Class<?> type();

    public String name();

}

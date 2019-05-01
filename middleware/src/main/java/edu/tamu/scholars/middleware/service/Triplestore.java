package edu.tamu.scholars.middleware.service;

import org.apache.jena.query.Dataset;

public interface Triplestore {

    public void init();

    public void destroy();

    public Dataset getDataset();

}

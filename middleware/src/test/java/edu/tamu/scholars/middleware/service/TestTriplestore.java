package edu.tamu.scholars.middleware.service;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;

public class TestTriplestore implements Triplestore {

    private Dataset dataset;

    @Override
    public void init() {
        dataset = TDBFactory.createDataset();
    }

    @Override
    public void destroy() {
        dataset.close();
    }

    @Override
    public Dataset getDataset() {
        return dataset;
    }

}

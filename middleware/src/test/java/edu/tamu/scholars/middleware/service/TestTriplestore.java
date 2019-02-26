package edu.tamu.scholars.middleware.service;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;

public class TestTriplestore implements Triplestore {

    private Dataset dataset = TDBFactory.createDataset();

    @Override
    public Dataset dataset() {
        return dataset;
    }

}

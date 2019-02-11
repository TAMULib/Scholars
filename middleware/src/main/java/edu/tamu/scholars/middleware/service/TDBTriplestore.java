package edu.tamu.scholars.middleware.service;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDB;
import org.apache.jena.tdb.TDBFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.config.TriplestoreConfig;

public class TDBTriplestore implements Triplestore {

    @Autowired
    private TriplestoreConfig triplestoreConfig;

    private Dataset dataset;

    @Override
    public void open() {
        TDB.getContext().setTrue(TDB.symUnionDefaultGraph);
        dataset = TDBFactory.createDataset(triplestoreConfig.getDirectory());
    }

    @Override
    public Dataset dataset() {
        return dataset;
    }

    @Override
    public void close() {
        dataset.close();
    }

}

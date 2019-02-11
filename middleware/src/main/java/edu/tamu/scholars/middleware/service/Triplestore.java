package edu.tamu.scholars.middleware.service;

import org.apache.jena.query.Dataset;
import org.apache.jena.sdb.Store;

public interface Triplestore {

    public void open();

    public Store store();

    public Dataset dataset();

    public void close();

}

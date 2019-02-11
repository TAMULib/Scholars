package edu.tamu.scholars.middleware.service;

import org.apache.jena.query.Dataset;

public interface Triplestore {

    public void open();

    public Dataset dataset();

    public void close();

}

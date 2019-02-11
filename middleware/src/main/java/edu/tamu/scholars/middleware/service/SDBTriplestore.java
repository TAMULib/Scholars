package edu.tamu.scholars.middleware.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.jena.query.Dataset;
import org.apache.jena.sdb.SDB;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.config.TriplestoreConfig;

public class SDBTriplestore implements Triplestore {

    @Autowired
    private TriplestoreConfig triplestoreConfig;

    private Store store;

    private Dataset dataset;

    @PostConstruct
    public void open() {
        // TODO: handle missing configurations
        SDB.getContext().setTrue(SDB.unionDefaultGraph);
        SDB.getContext().set(SDB.jdbcStream, triplestoreConfig.isJdbcStream());
        SDB.getContext().set(SDB.jdbcFetchSize, triplestoreConfig.getJdbcFetchSize());
        SDB.getContext().set(SDB.streamGraphAPI, triplestoreConfig.isStreamGraphAPI());
        SDB.getContext().set(SDB.annotateGeneratedSQL, triplestoreConfig.isAnnotateGeneratedSQL());
        StoreDesc storeDesc = new StoreDesc(LayoutType.fetch(triplestoreConfig.getLayoutType()), DatabaseType.fetch(triplestoreConfig.getDatabaseType()));
        SDBConnection conn = new SDBConnection(triplestoreConfig.getDatasourceUrl(), triplestoreConfig.getUsername(), triplestoreConfig.getPassword());
        store = SDBFactory.connectStore(conn, storeDesc);
        dataset = SDBFactory.connectDataset(store);
    }

    @Override
    public Dataset dataset() {
        return dataset;
    }

    @PreDestroy
    public void close() {
        store.getConnection().close();
        store.close();
        dataset.close();
    }

}

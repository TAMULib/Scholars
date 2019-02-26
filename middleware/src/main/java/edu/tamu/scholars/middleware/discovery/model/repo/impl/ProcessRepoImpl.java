package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import edu.tamu.scholars.middleware.discovery.model.Process;

public class ProcessRepoImpl extends AbstractSolrDocumentRepoImpl<Process> {

    @Override
    public String collection() {
        return "processes";
    }

    @Override
    public Class<Process> type() {
        return Process.class;
    }

}

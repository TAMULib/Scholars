package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Process;
import edu.tamu.scholars.middleware.discovery.model.repo.ProcessRepo;

@Service
public class ProcessIndexService extends AbstractSolrIndexService<Process, ProcessRepo> {

    @Override
    public Class<?> type() {
        return Process.class;
    }

    @Override
    public void index() {

    }

}

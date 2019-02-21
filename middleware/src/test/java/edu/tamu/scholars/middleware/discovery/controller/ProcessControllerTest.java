package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import edu.tamu.scholars.middleware.discovery.model.Process;
import edu.tamu.scholars.middleware.discovery.model.repo.ProcessRepo;

public class ProcessControllerTest extends AbstractSolrDocumentControllerTest<Process, ProcessRepo> {

    @Value("classpath:mock/discovery/process")
    private Resource mocksDirectory;

    @Override
    protected Resource getMocksDirectory() {
        return mocksDirectory;
    }

    @Override
    protected Class<?> getType() {
        return Process.class;
    }

    @Override
    protected String getPath() {
        return "/processes";
    }

}

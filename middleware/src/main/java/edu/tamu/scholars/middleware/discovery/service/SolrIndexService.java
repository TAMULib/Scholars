package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.repo.PersonRepo;

@Service
public class SolrIndexService {

    @Autowired
    private PersonRepo personRepo;

}

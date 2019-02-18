package edu.tamu.scholars.middleware.harvest.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Concept;
import edu.tamu.scholars.middleware.discovery.service.ConceptIndexService;

@Service
public class ConceptHarvestService extends AbstractHarvestService<Concept, ConceptIndexService> {

}

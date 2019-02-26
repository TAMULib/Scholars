package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.discovery.assembler.ConceptResourceAssembler;
import edu.tamu.scholars.middleware.discovery.model.Concept;
import edu.tamu.scholars.middleware.discovery.model.repo.ConceptRepo;
import edu.tamu.scholars.middleware.discovery.resource.ConceptResource;

@BasePathAwareController
@RequestMapping("/concepts")
public class ConceptController extends AbstractSolrDocumentController<Concept, ConceptRepo, ConceptResource, ConceptResourceAssembler> {

}

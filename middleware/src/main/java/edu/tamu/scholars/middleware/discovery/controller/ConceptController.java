package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.discovery.model.Concept;
import edu.tamu.scholars.middleware.discovery.model.repo.ConceptRepo;

@BasePathAwareController
@RequestMapping("/concepts")
public class ConceptController extends AbstractSolrDocumentController<Concept, ConceptRepo> {

}

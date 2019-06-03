package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.discovery.assembler.CollectionResourceAssembler;
import edu.tamu.scholars.middleware.discovery.model.Collection;
import edu.tamu.scholars.middleware.discovery.model.repo.CollectionRepo;
import edu.tamu.scholars.middleware.discovery.resource.CollectionResource;

@BasePathAwareController
@RequestMapping("/collections")
public class CollectionController extends AbstractSolrDocumentController<Collection, CollectionRepo, CollectionResource, CollectionResourceAssembler> {

}

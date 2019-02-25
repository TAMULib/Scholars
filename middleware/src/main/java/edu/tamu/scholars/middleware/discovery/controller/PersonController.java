package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.discovery.assembler.PersonResourceAssembler;
import edu.tamu.scholars.middleware.discovery.model.Person;
import edu.tamu.scholars.middleware.discovery.model.repo.PersonRepo;
import edu.tamu.scholars.middleware.discovery.resource.PersonResource;

@BasePathAwareController
@RequestMapping("/persons")
public class PersonController extends AbstractSolrDocumentController<Person, PersonRepo, PersonResource, PersonResourceAssembler> {

}

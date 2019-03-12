package edu.tamu.scholars.middleware.view.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.view.assembler.DiscoveryViewResourceAssembler;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.repo.DiscoveryViewRepo;
import edu.tamu.scholars.middleware.view.resource.DiscoveryViewResource;

@RepositoryRestController
@RequestMapping("/discoveryViews")
public class DiscoveryViewController extends AbstractCollectionViewController<DiscoveryView, DiscoveryViewRepo, DiscoveryViewResource, DiscoveryViewResourceAssembler> {

}

package edu.tamu.scholars.middleware.view.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.tamu.scholars.middleware.view.assembler.DirectoryViewResourceAssembler;
import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.repo.DirectoryViewRepo;
import edu.tamu.scholars.middleware.view.resource.DirectoryViewResource;

@RepositoryRestController
@RequestMapping("/directoryViews")
public class DirectoryViewController extends AbstractCollectionViewController<DirectoryView, DirectoryViewRepo, DirectoryViewResource, DirectoryViewResourceAssembler> {

}

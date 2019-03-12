package edu.tamu.scholars.middleware.view.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.view.controller.DirectoryViewController;
import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.resource.DirectoryViewResource;

@Component
public class DirectoryViewResourceAssembler extends AbstractCollectionViewResourceAssembler<DirectoryView, DirectoryViewResource> {

    public DirectoryViewResourceAssembler() {
        super(DirectoryViewController.class, DirectoryViewResource.class);
    }

    @Override
    protected DirectoryViewResource createResource(DirectoryView view, Iterable<Link> links) {
        return new DirectoryViewResource(enrich(view), links);
    }

}

package edu.tamu.scholars.middleware.view.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.view.controller.DiscoveryViewController;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.resource.DiscoveryViewResource;

@Component
public class DiscoveryViewResourceAssembler extends AbstractCollectionViewResourceAssembler<DiscoveryView, DiscoveryViewResource> {

    public DiscoveryViewResourceAssembler() {
        super(DiscoveryViewController.class, DiscoveryViewResource.class);
    }

    @Override
    protected DiscoveryViewResource createResource(DiscoveryView view, Iterable<Link> links) {
        return new DiscoveryViewResource(enrich(view), links);
    }

}

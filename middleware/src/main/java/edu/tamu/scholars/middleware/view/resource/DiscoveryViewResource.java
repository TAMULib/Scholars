package edu.tamu.scholars.middleware.view.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.view.model.DiscoveryView;

public class DiscoveryViewResource extends AbstractCollectionViewResource<DiscoveryView> {

    public DiscoveryViewResource(DiscoveryView content, Iterable<Link> links) {
        super(content, links);
    }

}

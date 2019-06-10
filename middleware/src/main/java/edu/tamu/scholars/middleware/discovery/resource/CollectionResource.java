package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.discovery.model.Collection;

public class CollectionResource extends AbstractSolrDocumentResource<Collection> {

    public CollectionResource(Collection collection, Iterable<Link> links) {
        super(collection, links);
    }

}

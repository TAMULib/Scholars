package edu.tamu.scholars.middleware.discovery.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.discovery.controller.CollectionController;
import edu.tamu.scholars.middleware.discovery.model.Collection;
import edu.tamu.scholars.middleware.discovery.resource.CollectionResource;

@Component
public class CollectionResourceAssembler extends AbstractSolrDocumentResourceAssembler<Collection, CollectionResource> {

    public CollectionResourceAssembler() {
        super(CollectionController.class, CollectionResource.class);
    }

    @Override
    protected CollectionResource createResource(Collection collection, Iterable<Link> links) {
        return new CollectionResource(collection, links);
    }

}

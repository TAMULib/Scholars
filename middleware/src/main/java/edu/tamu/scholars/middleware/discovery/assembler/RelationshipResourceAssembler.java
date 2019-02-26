package edu.tamu.scholars.middleware.discovery.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.discovery.controller.RelationshipController;
import edu.tamu.scholars.middleware.discovery.model.Relationship;
import edu.tamu.scholars.middleware.discovery.resource.RelationshipResource;

@Component
public class RelationshipResourceAssembler extends AbstractSolrDocumentResourceAssembler<Relationship, RelationshipResource> {

    public RelationshipResourceAssembler() {
        super(RelationshipController.class, RelationshipResource.class);
    }

    @Override
    protected RelationshipResource createResource(Relationship relationship, Iterable<Link> links) {
        return new RelationshipResource(relationship, links);
    }

}

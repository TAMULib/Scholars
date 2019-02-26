package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

public class RelationshipResource extends AbstractSolrDocumentResource<Relationship> {

    public RelationshipResource(Relationship relatiopnship, Iterable<Link> links) {
        super(relatiopnship, links);
    }

}

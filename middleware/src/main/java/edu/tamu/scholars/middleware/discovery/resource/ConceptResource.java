package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.discovery.model.Concept;

public class ConceptResource extends AbstractSolrDocumentResource<Concept> {

    public ConceptResource(Concept content, Iterable<Link> links) {
        super(content, links);
    }

}

package edu.tamu.scholars.middleware.discovery.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.discovery.controller.ConceptController;
import edu.tamu.scholars.middleware.discovery.model.Concept;
import edu.tamu.scholars.middleware.discovery.resource.ConceptResource;

@Component
public class ConceptResourceAssembler extends AbstractSolrDocumentResourceAssembler<Concept, ConceptResource> {

    public ConceptResourceAssembler() {
        super(ConceptController.class, ConceptResource.class);
    }

    @Override
    protected ConceptResource createResource(Concept concept, Iterable<Link> links) {
        return new ConceptResource(concept, links);
    }

}

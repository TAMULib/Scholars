package edu.tamu.scholars.middleware.discovery.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.discovery.controller.DocumentController;
import edu.tamu.scholars.middleware.discovery.model.Document;
import edu.tamu.scholars.middleware.discovery.resource.DocumentResource;

@Component
public class DocumentResourceAssembler extends AbstractSolrDocumentResourceAssembler<Document, DocumentResource> {

    public DocumentResourceAssembler() {
        super(DocumentController.class, DocumentResource.class);
    }

    @Override
    protected DocumentResource createResource(Document document, Iterable<Link> links) {
        return new DocumentResource(document, links);
    }

}

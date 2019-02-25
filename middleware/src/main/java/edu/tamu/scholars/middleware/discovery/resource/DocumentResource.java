package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.discovery.model.Document;

public class DocumentResource extends AbstractSolrDocumentResource<Document> {

    public DocumentResource(Document document, Iterable<Link> links) {
        super(document, links);
    }

}

package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;

public abstract class AbstractSolrDocumentResource<D extends AbstractSolrDocument> extends Resource<D> {

    public AbstractSolrDocumentResource(D content, Iterable<Link> links) {
        super(content, links);
    }

}

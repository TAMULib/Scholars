package edu.tamu.scholars.middleware.discovery.assembler;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.resource.AbstractSolrDocumentResource;

public abstract class AbstractSolrDocumentResourceAssembler<D extends AbstractSolrDocument, R extends AbstractSolrDocumentResource<D>> extends ResourceAssemblerSupport<D, R> {

    @Autowired
    private RepositoryEntityLinks repositoryEntityLinks;

    public AbstractSolrDocumentResourceAssembler(Class<?> controllerClass, Class<R> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public R toResource(D document) {
        Link documentLink = repositoryEntityLinks.linkToSingleResource(document.getClass(), document.getId());
        Link selfLink = new Link(documentLink.getHref(), Link.REL_SELF);
        return createResource(document, Arrays.asList(selfLink, documentLink));
    }

    protected abstract R createResource(D document, Iterable<Link> links);

}

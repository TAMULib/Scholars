package edu.tamu.scholars.middleware.discovery.processor;

import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

@Component
public class SorDocumentResourcesSearchProcessor implements ResourceProcessor<RepositorySearchesResource> {

    @Override
    public RepositorySearchesResource process(RepositorySearchesResource resource) {
        final String search = resource.getId().getHref();
        final Link customLink = new Link(search + "/facet{?query,fields,page,size,sort}").withRel("facet");
        resource.add(customLink);
        return resource;
    }

}

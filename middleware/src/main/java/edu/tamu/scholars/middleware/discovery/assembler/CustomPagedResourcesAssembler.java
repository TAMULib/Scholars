package edu.tamu.scholars.middleware.discovery.assembler;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

// NOTE: open issue, https://jira.spring.io/browse/DATAREST-309
@Component
public class CustomPagedResourcesAssembler<T> extends PagedResourcesAssembler<T> {

    public CustomPagedResourcesAssembler(@Nullable HateoasPageableHandlerMethodArgumentResolver resolver, @Nullable UriComponents baseUri) {
        super(resolver, baseUri);
    }

    @Override
    protected <R extends ResourceSupport, S> PagedResources<R> createPagedResource(List<R> resources, PagedResources.PageMetadata metadata, Page<S> page) {
        PagedResources<R> pagedResource = super.createPagedResource(resources, metadata, page);
        if (page instanceof FacetPage) {
            return new FacetPagedResource<R, S>(pagedResource, (FacetPage<S>) page);
        }
        return pagedResource;
    }

    static class FacetPagedResource<R extends ResourceSupport, S> extends PagedResources<R> {

        private Collection<Page<FacetFieldEntry>> facets;

        FacetPagedResource(PagedResources<R> pagedResources, FacetPage<S> facetPage) {
            super(pagedResources.getContent(), pagedResources.getMetadata(), pagedResources.getLinks());
            this.facets = facetPage.getFacetResultPages();
        }

        public Collection<Page<FacetFieldEntry>> getFacets() {
            return facets;
        }
    }

}
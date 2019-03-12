package edu.tamu.scholars.middleware.discovery.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import edu.tamu.scholars.middleware.discovery.model.SdrFacet;
import edu.tamu.scholars.middleware.discovery.model.SdrFacetEntry;

@Component
public class SolrDocumentFacetPagedResourcesAssembler<T> extends PagedResourcesAssembler<T> {

    public SolrDocumentFacetPagedResourcesAssembler(@Nullable HateoasPageableHandlerMethodArgumentResolver resolver, @Nullable UriComponents baseUri) {
        super(resolver, baseUri);
    }

    @Override
    protected <R extends ResourceSupport, S> PagedResources<R> createPagedResource(List<R> resources, PagedResources.PageMetadata metadata, Page<S> page) {
        PagedResources<R> pagedResource = super.createPagedResource(resources, metadata, page);
        if (page instanceof FacetPage) {
            return new SolrDocumentFacetPagedResource<R, S>(pagedResource, (FacetPage<S>) page);
        }
        return pagedResource;
    }

    class SolrDocumentFacetPagedResource<R extends ResourceSupport, S> extends PagedResources<R> {

        private List<SdrFacet> facets;

        SolrDocumentFacetPagedResource(PagedResources<R> pagedResources, FacetPage<S> facetPage) {
            super(pagedResources.getContent(), pagedResources.getMetadata(), pagedResources.getLinks());

            List<SdrFacet> facets = new ArrayList<SdrFacet>();

            facetPage.getFacetResultPages().forEach(facetFieldEntryPage -> {

                Optional<String> field = Optional.empty();

                List<SdrFacetEntry> entries = new ArrayList<SdrFacetEntry>();

                for (FacetFieldEntry facetFieldEntry : facetFieldEntryPage.getContent()) {
                    if (!field.isPresent()) {
                        field = Optional.of(facetFieldEntry.getField().getName());
                    }
                    entries.add(new SdrFacetEntry(facetFieldEntry.getValue(), facetFieldEntry.getValueCount()));
                }

                if (field.isPresent()) {
                    facets.add(new SdrFacet(field.get(), entries, facetFieldEntryPage.getPageable()));
                }

            });

            setFacets(facets);
        }

        public List<SdrFacet> getFacets() {
            return facets;
        }

        public void setFacets(List<SdrFacet> facets) {
            this.facets = facets;
        }

    }

}
package edu.tamu.scholars.middleware.discovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.SolrDocumentRepo;

public abstract class AbstractSolrDocumentController<D extends AbstractSolrDocument, R extends SolrDocumentRepo<D>> implements ResourceProcessor<PagedResources<Resource<D>>> {

    @Autowired
    private R repo;

    @Autowired
    protected EntityLinks entityLinks;

    @GetMapping("/search")
    // @formatter:off
    public ResponseEntity<FacetPage<D>> search(
        @RequestParam(value = "query", required = false) String query,
        @RequestParam(value = "fields", required = false) String[] fields,
        @PageableDefault Pageable pageable
    ) {
    // @formatter:on
        return ResponseEntity.ok(repo.search(query, fields, pageable));
    }

    @Override
    public PagedResources<Resource<D>> process(PagedResources<Resource<D>> resources) {
        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).search(null, null, null)).withRel("search"));
        return resources;
    }

}

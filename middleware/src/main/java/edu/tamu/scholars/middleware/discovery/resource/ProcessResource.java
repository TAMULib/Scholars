package edu.tamu.scholars.middleware.discovery.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.discovery.model.Process;

public class ProcessResource extends AbstractSolrDocumentResource<Process> {

    public ProcessResource(Process process, Iterable<Link> links) {
        super(process, links);
    }

}

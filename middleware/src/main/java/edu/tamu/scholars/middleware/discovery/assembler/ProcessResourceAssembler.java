package edu.tamu.scholars.middleware.discovery.assembler;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import edu.tamu.scholars.middleware.discovery.controller.ProcessController;
import edu.tamu.scholars.middleware.discovery.model.Process;
import edu.tamu.scholars.middleware.discovery.resource.ProcessResource;

@Component
public class ProcessResourceAssembler extends AbstractSolrDocumentResourceAssembler<Process, ProcessResource> {

    public ProcessResourceAssembler() {
        super(ProcessController.class, ProcessResource.class);
    }

    @Override
    protected ProcessResource createResource(Process process, Iterable<Link> links) {
        return new ProcessResource(process, links);
    }

}

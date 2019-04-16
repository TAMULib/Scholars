package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import edu.tamu.scholars.middleware.discovery.model.Process;

@JsonComponent
public class ProcessSerializer extends AbstractSolrDocumentSerializer<Process> {

}

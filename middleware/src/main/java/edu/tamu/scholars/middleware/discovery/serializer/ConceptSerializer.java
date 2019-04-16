package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import edu.tamu.scholars.middleware.discovery.model.Concept;

@JsonComponent
public class ConceptSerializer extends AbstractSolrDocumentSerializer<Concept> {

}

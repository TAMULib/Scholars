package edu.tamu.scholars.middleware.discovery.serializer;

import org.springframework.boot.jackson.JsonComponent;

import edu.tamu.scholars.middleware.discovery.model.Relationship;

@JsonComponent
public class RelationshipSerializer extends AbstractSolrDocumentSerializer<Relationship> {

}

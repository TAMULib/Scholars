package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.harvest.annotation.Source;

@JsonInclude(NON_EMPTY)
//@formatter:off
@Source(
  key = "organization.class",
  sparql = {
      @Source.Sparql(
          template = "organization/organization",
          properties = {
              @Source.Property(name = "type", key = "organization.type.value", schema = "organization.type.schema", parse = true),
              @Source.Property(name = "type", key = "organization.type.value", schema = "organization.type.alternativeSchema", parse = true),
              @Source.Property(name = "name", key = "organization.name")
          }
      )
  }
)
//@formatter:on
@SolrDocument(collection = "organizations")
public class Organization extends AbstractSolrDocument {

    @Indexed
    private String type;

    @Indexed
    private String name;

    public Organization() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

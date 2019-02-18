package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "concepts")
@CollectionSource(key = "concept.class")
public class Concept extends AbstractSolrDocument {

    @Indexed
    @PropertySource(template = "concept/name", key = "concept.name")
    private String name;

    @Indexed
    // @PropertySource(template = "concept/image", key = "concept.image")
    private String image;

    @Indexed
    // @PropertySource(template = "concept/websiteUrl", key = "concept.website.url")
    private List<String> websiteUrl;

    @Indexed
    // @PropertySource(template = "concept/websiteLabel", key = "concept.website.label")
    private List<String> websiteLabel;

    // @Indexed
    // private String overview;

    // TODO UI has label id as facultyResearchAreas - would rename be required?
    // @Indexed
    // @PropertySource(template = "concept/facultyResearchArea", key = "concept.facultyResearchArea")
    // private String facultyResearchArea;

    // @Indexed
    // private String researchAreaOrganizationURI;

    // @Indexed
    // private List<String> individualResearchArea;

    @Indexed
    // @PropertySource(template = "context/researchAreaOf", key = "context.researchAreaOf", id = "researchAreaOfId")
    private List<String> researchAreaOf;

    @Indexed
    private List<String> researchAreaOfPerson;

    @Indexed
    private List<String> researchAreaOfId;

    @Indexed
    private List<String> researchAreaOfTitle;

    @Indexed
    private List<String> researchAreaOfOrganization;

    @Indexed
    // @PropertySource(template = "context/broaderConcept", key = "context.broaderConcept", id = "broaderConceptId")
    private List<String> broaderConcept;

    @Indexed
    private List<String> broaderConceptId;

    @Indexed
    // @PropertySource(template = "context/narrowerConcept", key = "context.narrowerConcept", id = "narrowerConceptId")
    private List<String> narrowerConcept;

    @Indexed
    private List<String> narrowerConceptId;

    @Indexed
    // @PropertySource(template = "context/relatedConcept", key = "context.relatedConcept", id = "relatedConceptId")
    private List<String> relatedConcept;

    @Indexed
    private List<String> relatedConceptId;

    @Indexed
    private List<String> sameAs;

    @Indexed
    // @PropertySource(template = "concept/identity", key = "concept.identity")
    private String identity;

    @Indexed
    private List<String> sameAsId;

    public Concept() {

    }

}

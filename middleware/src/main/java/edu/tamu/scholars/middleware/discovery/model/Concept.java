package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.NestedMultiValuedProperty;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject.Reference;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "concepts")
@CollectionSource(predicate = "http://www.w3.org/2004/02/skos/core#Concept")
public class Concept extends AbstractSolrDocument {

    @Indexed(type = "sorting_string", copyTo = "_text_")
    @PropertySource(template = "concept/name", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String name;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "concept/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed(type = "whole_string")
    @PropertySource(template = "concept/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed(type = "whole_string")
    @PropertySource(template = "concept/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "websiteUrl", key = "url") })
    @PropertySource(template = "concept/website", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> website;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/associatedDepartment", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> associatedDepartment;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "researchAreaOfTitle", key = "title"), @Reference(value = "researchAreaOfOrganization", key = "organization") })
    @PropertySource(template = "concept/researchAreaOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> researchAreaOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/researchAreaOfTitle", predicate = "http://vivoweb.org/ontology/core#hrJobTitle")
    private List<String> researchAreaOfTitle;

    @NestedMultiValuedProperty
    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/researchAreaOfOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> researchAreaOfOrganization;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "broaderConceptType", key = "type") })
    @PropertySource(template = "concept/broaderConcept", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> broaderConcept;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/broaderConceptType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> broaderConceptType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "narrowerConceptType", key = "type") })
    @PropertySource(template = "concept/narrowerConcept", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> narrowerConcept;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/narrowerConceptType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> narrowerConceptType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "relatedConceptType", key = "type") })
    @PropertySource(template = "concept/relatedConcept", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> relatedConcept;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/relatedConceptType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> relatedConceptType;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "concept/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> sameAs;

    @Indexed(type = "pdate")
    @PropertySource(template = "concept/modTime", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#modTime")
    private String modTime;

    public Concept() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getWebsite() {
        return website;
    }

    public void setWebsite(List<String> website) {
        this.website = website;
    }

    public List<String> getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(List<String> websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public List<String> getAssociatedDepartment() {
        return associatedDepartment;
    }

    public void setAssociatedDepartment(List<String> associatedDepartment) {
        this.associatedDepartment = associatedDepartment;
    }

    public List<String> getResearchAreaOf() {
        return researchAreaOf;
    }

    public void setResearchAreaOf(List<String> researchAreaOf) {
        this.researchAreaOf = researchAreaOf;
    }

    public List<String> getResearchAreaOfTitle() {
        return researchAreaOfTitle;
    }

    public void setResearchAreaOfTitle(List<String> researchAreaOfTitle) {
        this.researchAreaOfTitle = researchAreaOfTitle;
    }

    public List<String> getResearchAreaOfOrganization() {
        return researchAreaOfOrganization;
    }

    public void setResearchAreaOfOrganization(List<String> researchAreaOfOrganization) {
        this.researchAreaOfOrganization = researchAreaOfOrganization;
    }

    public List<String> getBroaderConcept() {
        return broaderConcept;
    }

    public void setBroaderConcept(List<String> broaderConcept) {
        this.broaderConcept = broaderConcept;
    }

    public List<String> getBroaderConceptType() {
        return broaderConceptType;
    }

    public void setBroaderConceptType(List<String> broaderConceptType) {
        this.broaderConceptType = broaderConceptType;
    }

    public List<String> getNarrowerConcept() {
        return narrowerConcept;
    }

    public void setNarrowerConcept(List<String> narrowerConcept) {
        this.narrowerConcept = narrowerConcept;
    }

    public List<String> getNarrowerConceptType() {
        return narrowerConceptType;
    }

    public void setNarrowerConceptType(List<String> narrowerConceptType) {
        this.narrowerConceptType = narrowerConceptType;
    }

    public List<String> getRelatedConcept() {
        return relatedConcept;
    }

    public void setRelatedConcept(List<String> relatedConcept) {
        this.relatedConcept = relatedConcept;
    }

    public List<String> getRelatedConceptType() {
        return relatedConceptType;
    }

    public void setRelatedConceptType(List<String> relatedConceptType) {
        this.relatedConceptType = relatedConceptType;
    }

    public List<String> getSameAs() {
        return sameAs;
    }

    public void setSameAs(List<String> sameAs) {
        this.sameAs = sameAs;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

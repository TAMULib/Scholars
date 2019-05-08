package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.NestedMultiValuedProperty;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject.Reference;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "documents")
@CollectionSource(predicate = "http://purl.org/ontology/bibo/Document")
public class Document extends AbstractSolrDocument {

    @Indexed(type = "sorting_string", copyTo = "_text_")
    @PropertySource(template = "document/title", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String title;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "document/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "document/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "document/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "websiteUrl", key = "url") })
    @PropertySource(template = "document/website", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> websites;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @Field("abstract")
    @JsonProperty("abstract")
    @Indexed(type = "whole_string", value = "abstract", copyTo = "_text_")
    @PropertySource(template = "document/abstract", predicate = "http://purl.org/ontology/bibo/abstract")
    private String abstractText;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/abbreviation", predicate = "http://vivoweb.org/ontology/core#abbreviation")
    private String abbreviation;

    @NestedObject({ @Reference(value = "publicationVenueType", key = "type") })
    @Indexed(type = "nested_string", copyTo = "_text_")
    @PropertySource(template = "document/publicationVenue", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private String publicationVenue;

    @Indexed(type = "nested_string", copyTo = "_text_")
    @PropertySource(template = "document/publicationVenueType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private String publicationVenueType;

    @NestedObject
    @Indexed(type = "nested_string")
    @PropertySource(template = "document/publicationVenueFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private String publicationVenueFor;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "etdChairedByEmail", key = "email"), @Reference(value = "etdChairedByOrganization", key = "organization") })
    @PropertySource(template = "document/etdChairedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> etdChairedBy;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/etdChairedByEmail", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private List<String> etdChairedByEmail;

    @NestedMultiValuedProperty
    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/etdChairedByOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> etdChairedByOrganization;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "authorType", key = "type"), @Reference(value = "authorRank", key = "rank"), @Reference(value = "authorOrganization", key = "organizations") })
    @PropertySource(template = "document/author", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> authors;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/authorType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> authorType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/authorRank", predicate = "http://vivoweb.org/ontology/core#rank")
    private List<String> authorRank;

    @NestedMultiValuedProperty
    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/authorOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> authorOrganization;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "document/authorList", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#fullAuthorList")
    private List<String> authorList;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "editorType", key = "type"), @Reference(value = "editorRank", key = "rank") })
    @PropertySource(template = "document/editor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> editors;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/editorType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> editorType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/editorRank", predicate = "http://vivoweb.org/ontology/core#rank")
    private List<String> editorRank;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "document/editorList", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#fullEditorList")
    private List<String> editorList;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/bookTitle", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#bookTitleForChapter")
    private String bookTitle;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "translatorType", key = "type") })
    @PropertySource(template = "document/translator", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> translators;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/translatorType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> translatorType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/status", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String status;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/publicationDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private String publicationDate;

    @Indexed(type = "nested_string")
    @NestedObject({ @Reference(value = "publisherType", key = "type") })
    @PropertySource(template = "document/publisher", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private String publisher;

    @Indexed(type = "nested_string")
    @PropertySource(template = "document/publisherType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private String publisherType;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/dateFiled", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private String dateFiled;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/dateIssued", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private String dateIssued;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/hasSubjectArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> subjectAreas;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "document/hasRestriction", predicate = "http://purl.obolibrary.org/obo/ERO_0000045")
    private List<String> restrictions;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "documentPartType", key = "type") })
    @PropertySource(template = "document/documentPart", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> documentParts;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/documentPartType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> documentPartType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/chapter", predicate = "http://purl.org/ontology/bibo/chapter")
    private String chapter;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "featureType", key = "type") })
    @PropertySource(template = "document/feature", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> features;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/featureType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> featureType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/edition", predicate = "http://purl.org/ontology/bibo/edition")
    private String edition;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "geographicFocusType", key = "type") })
    @PropertySource(template = "document/geographicFocus", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> geographicFocus;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/geographicFocusType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> geographicFocusType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "documentationForProjectOrResourceType", key = "type") })
    @PropertySource(template = "document/documentationForProjectOrResource", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> documentationForProjectOrResource;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/documentationForProjectOrResourceType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> documentationForProjectOrResourceType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "outputOfProcessOrEventType", key = "type") })
    @PropertySource(template = "document/outputOfProcessOrEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> outputOfProcessOrEvent;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/outputOfProcessOrEventType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> outputOfProcessOrEventType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "presentedAtType", key = "type") })
    @PropertySource(template = "document/presentedAt", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> presentedAt;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/presentedAtType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> presentedAtType;

    @Indexed(type = "delimited_strings", copyTo = "_text_")
    @PropertySource(template = "document/keyword", predicate = "http://vivoweb.org/ontology/core#freetextKeyword")
    private List<String> keywords;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/eanucc13", predicate = "http://purl.org/ontology/bibo/eanucc13")
    private String eanucc13;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/nihmsid", predicate = "http://vivoweb.org/ontology/core#nihmsid")
    private String nihmsid;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/pmcid", predicate = "http://vivoweb.org/ontology/core#pmcid")
    private String pmcid;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/identifier", predicate = "http://purl.org/ontology/bibo/identifier")
    private String identifier;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/patentNumber", predicate = "http://vivoweb.org/ontology/core#patentNumber")
    private String patentNumber;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/doi", predicate = "http://purl.org/ontology/bibo/doi")
    private String doi;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/oclcnum", predicate = "http://purl.org/ontology/bibo/oclcnum")
    private String oclcnum;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/isbn10", predicate = "http://purl.org/ontology/bibo/isbn10")
    private String isbn10;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/isbn13", predicate = "http://purl.org/ontology/bibo/isbn13")
    private String isbn13;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/pmid", predicate = "http://purl.org/ontology/bibo/pmid")
    private String pmid;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/lccn", predicate = "http://purl.org/ontology/bibo/lccn")
    private String lccn;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/issn", predicate = "http://purl.org/ontology/bibo/issn")
    private String issn;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/eissn", predicate = "http://purl.org/ontology/bibo/eissn")
    private String eissn;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "document/uri", predicate = "http://purl.org/ontology/bibo/uri")
    private List<String> uri;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "citedByType", key = "type") })
    @PropertySource(template = "document/citedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> citedBy;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/citedByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> citedByType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "citationType", key = "type") })
    @PropertySource(template = "document/citation", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> citations;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/citationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> citationType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "citesAsDataSourceType", key = "type") })
    @PropertySource(template = "document/citesAsDataSource", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> citesAsDataSource;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/citesAsDataSourceType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> citesAsDataSourceType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "translationType", key = "type") })
    @PropertySource(template = "document/hasTranslation", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> translations;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/hasTranslationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> translationType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "translationOfType", key = "type") })
    @PropertySource(template = "document/translationOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> translationOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/translationOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> translationOfType;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/globalCitationFrequency", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> globalCitationFrequency;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/iclCode", predicate = "http://vivoweb.org/ontology/core#iclCode")
    private String iclCode;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/numberOfPages", predicate = "http://purl.org/ontology/bibo/numPages")
    private String numberOfPages;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/pageStart", predicate = "http://purl.org/ontology/bibo/pageStart")
    private String pageStart;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/pageEnd", predicate = "http://purl.org/ontology/bibo/pageEnd")
    private String pageEnd;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/volume", predicate = "http://purl.org/ontology/bibo/volume")
    private String volume;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/issue", predicate = "http://purl.org/ontology/bibo/issue")
    private String issue;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/placeOfPublication", predicate = "http://vivoweb.org/ontology/core#placeOfPublication")
    private String placeOfPublication;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "assigneeType", key = "type") })
    @PropertySource(template = "document/assignee", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> assignees;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/assigneeType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> assigneeType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "reproducedInType", key = "type") })
    @PropertySource(template = "document/reproducedIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> reproducedIn;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/reproducedInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> reproducedInType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "reproducesType", key = "type") })
    @PropertySource(template = "document/reproduces", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> reproduces;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/reproducesType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> reproducesType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "isAboutType", key = "type") })
    @PropertySource(template = "document/isAbout", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> isAbout;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/isAboutType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> isAboutType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "specifiedOutputOfType", key = "type") })
    @PropertySource(template = "document/specifiedOutputOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> specifiedOutputOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/specifiedOutputOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "document/isTemplate", predicate = "http://purl.obolibrary.org/obo/ARG_0000001")
    private String isTemplate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "mentionType", key = "type") })
    @PropertySource(template = "document/mention", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> mentions;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/mentionType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> mentionType;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/participatesIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> participatesIn;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "supportedByType", key = "type") })
    @PropertySource(template = "document/supportedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> supportedBy;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/supportedByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> supportedByType;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/receipt", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> receipts;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "document/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> sameAs;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/modTime", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#modTime")
    private String modTime;

    public Document() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<String> getWebsites() {
        return websites;
    }

    public void setWebsites(List<String> websites) {
        this.websites = websites;
    }

    public List<String> getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(List<String> websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getPublicationVenue() {
        return publicationVenue;
    }

    public void setPublicationVenue(String publicationVenue) {
        this.publicationVenue = publicationVenue;
    }

    public String getPublicationVenueType() {
        return publicationVenueType;
    }

    public void setPublicationVenueType(String publicationVenueType) {
        this.publicationVenueType = publicationVenueType;
    }

    public String getPublicationVenueFor() {
        return publicationVenueFor;
    }

    public void setPublicationVenueFor(String publicationVenueFor) {
        this.publicationVenueFor = publicationVenueFor;
    }

    public List<String> getEtdChairedBy() {
        return etdChairedBy;
    }

    public void setEtdChairedBy(List<String> etdChairedBy) {
        this.etdChairedBy = etdChairedBy;
    }

    public List<String> getEtdChairedByEmail() {
        return etdChairedByEmail;
    }

    public void setEtdChairedByEmail(List<String> etdChairedByEmail) {
        this.etdChairedByEmail = etdChairedByEmail;
    }

    public List<String> getEtdChairedByOrganization() {
        return etdChairedByOrganization;
    }

    public void setEtdChairedByOrganization(List<String> etdChairedByOrganization) {
        this.etdChairedByOrganization = etdChairedByOrganization;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getAuthorType() {
        return authorType;
    }

    public void setAuthorType(List<String> authorType) {
        this.authorType = authorType;
    }

    public List<String> getAuthorRank() {
        return authorRank;
    }

    public void setAuthorRank(List<String> authorRank) {
        this.authorRank = authorRank;
    }

    public List<String> getAuthorOrganization() {
        return authorOrganization;
    }

    public void setAuthorOrganization(List<String> authorOrganization) {
        this.authorOrganization = authorOrganization;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public List<String> getEditors() {
        return editors;
    }

    public void setEditors(List<String> editors) {
        this.editors = editors;
    }

    public List<String> getEditorType() {
        return editorType;
    }

    public void setEditorType(List<String> editorType) {
        this.editorType = editorType;
    }

    public List<String> getEditorRank() {
        return editorRank;
    }

    public void setEditorRank(List<String> editorRank) {
        this.editorRank = editorRank;
    }

    public List<String> getEditorList() {
        return editorList;
    }

    public void setEditorList(List<String> editorList) {
        this.editorList = editorList;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public List<String> getTranslators() {
        return translators;
    }

    public void setTranslators(List<String> translators) {
        this.translators = translators;
    }

    public List<String> getTranslatorType() {
        return translatorType;
    }

    public void setTranslatorType(List<String> translatorType) {
        this.translatorType = translatorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherType() {
        return publisherType;
    }

    public void setPublisherType(String publisherType) {
        this.publisherType = publisherType;
    }

    public String getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(String dateFiled) {
        this.dateFiled = dateFiled;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public List<String> getSubjectAreas() {
        return subjectAreas;
    }

    public void setSubjectAreas(List<String> subjectAreas) {
        this.subjectAreas = subjectAreas;
    }

    public List<String> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<String> restrictions) {
        this.restrictions = restrictions;
    }

    public List<String> getDocumentParts() {
        return documentParts;
    }

    public void setDocumentParts(List<String> documentParts) {
        this.documentParts = documentParts;
    }

    public List<String> getDocumentPartType() {
        return documentPartType;
    }

    public void setDocumentPartType(List<String> documentPartType) {
        this.documentPartType = documentPartType;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<String> getFeatureType() {
        return featureType;
    }

    public void setFeatureType(List<String> featureType) {
        this.featureType = featureType;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<String> getGeographicFocus() {
        return geographicFocus;
    }

    public void setGeographicFocus(List<String> geographicFocus) {
        this.geographicFocus = geographicFocus;
    }

    public List<String> getGeographicFocusType() {
        return geographicFocusType;
    }

    public void setGeographicFocusType(List<String> geographicFocusType) {
        this.geographicFocusType = geographicFocusType;
    }

    public List<String> getDocumentationForProjectOrResource() {
        return documentationForProjectOrResource;
    }

    public void setDocumentationForProjectOrResource(List<String> documentationForProjectOrResource) {
        this.documentationForProjectOrResource = documentationForProjectOrResource;
    }

    public List<String> getDocumentationForProjectOrResourceType() {
        return documentationForProjectOrResourceType;
    }

    public void setDocumentationForProjectOrResourceType(List<String> documentationForProjectOrResourceType) {
        this.documentationForProjectOrResourceType = documentationForProjectOrResourceType;
    }

    public List<String> getOutputOfProcessOrEvent() {
        return outputOfProcessOrEvent;
    }

    public void setOutputOfProcessOrEvent(List<String> outputOfProcessOrEvent) {
        this.outputOfProcessOrEvent = outputOfProcessOrEvent;
    }

    public List<String> getOutputOfProcessOrEventType() {
        return outputOfProcessOrEventType;
    }

    public void setOutputOfProcessOrEventType(List<String> outputOfProcessOrEventType) {
        this.outputOfProcessOrEventType = outputOfProcessOrEventType;
    }

    public List<String> getPresentedAt() {
        return presentedAt;
    }

    public void setPresentedAt(List<String> presentedAt) {
        this.presentedAt = presentedAt;
    }

    public List<String> getPresentedAtType() {
        return presentedAtType;
    }

    public void setPresentedAtType(List<String> presentedAtType) {
        this.presentedAtType = presentedAtType;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getEanucc13() {
        return eanucc13;
    }

    public void setEanucc13(String eanucc13) {
        this.eanucc13 = eanucc13;
    }

    public String getNihmsid() {
        return nihmsid;
    }

    public void setNihmsid(String nihmsid) {
        this.nihmsid = nihmsid;
    }

    public String getPmcid() {
        return pmcid;
    }

    public void setPmcid(String pmcid) {
        this.pmcid = pmcid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getOclcnum() {
        return oclcnum;
    }

    public void setOclcnum(String oclcnum) {
        this.oclcnum = oclcnum;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getLccn() {
        return lccn;
    }

    public void setLccn(String lccn) {
        this.lccn = lccn;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getEissn() {
        return eissn;
    }

    public void setEissn(String eissn) {
        this.eissn = eissn;
    }

    public List<String> getUri() {
        return uri;
    }

    public void setUri(List<String> uri) {
        this.uri = uri;
    }

    public List<String> getCitedBy() {
        return citedBy;
    }

    public void setCitedBy(List<String> citedBy) {
        this.citedBy = citedBy;
    }

    public List<String> getCitedByType() {
        return citedByType;
    }

    public void setCitedByType(List<String> citedByType) {
        this.citedByType = citedByType;
    }

    public List<String> getCitations() {
        return citations;
    }

    public void setCitations(List<String> citations) {
        this.citations = citations;
    }

    public List<String> getCitationType() {
        return citationType;
    }

    public void setCitationType(List<String> citationType) {
        this.citationType = citationType;
    }

    public List<String> getCitesAsDataSource() {
        return citesAsDataSource;
    }

    public void setCitesAsDataSource(List<String> citesAsDataSource) {
        this.citesAsDataSource = citesAsDataSource;
    }

    public List<String> getCitesAsDataSourceType() {
        return citesAsDataSourceType;
    }

    public void setCitesAsDataSourceType(List<String> citesAsDataSourceType) {
        this.citesAsDataSourceType = citesAsDataSourceType;
    }

    public List<String> getTranslations() {
        return translations;
    }

    public void setTranslations(List<String> translations) {
        this.translations = translations;
    }

    public List<String> getTranslationType() {
        return translationType;
    }

    public void setTranslationType(List<String> translationType) {
        this.translationType = translationType;
    }

    public List<String> getTranslationOf() {
        return translationOf;
    }

    public void setTranslationOf(List<String> translationOf) {
        this.translationOf = translationOf;
    }

    public List<String> getTranslationOfType() {
        return translationOfType;
    }

    public void setTranslationOfType(List<String> translationOfType) {
        this.translationOfType = translationOfType;
    }

    public List<String> getGlobalCitationFrequency() {
        return globalCitationFrequency;
    }

    public void setGlobalCitationFrequency(List<String> globalCitationFrequency) {
        this.globalCitationFrequency = globalCitationFrequency;
    }

    public String getIclCode() {
        return iclCode;
    }

    public void setIclCode(String iclCode) {
        this.iclCode = iclCode;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPageStart() {
        return pageStart;
    }

    public void setPageStart(String pageStart) {
        this.pageStart = pageStart;
    }

    public String getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(String pageEnd) {
        this.pageEnd = pageEnd;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getPlaceOfPublication() {
        return placeOfPublication;
    }

    public void setPlaceOfPublication(String placeOfPublication) {
        this.placeOfPublication = placeOfPublication;
    }

    public List<String> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<String> assignees) {
        this.assignees = assignees;
    }

    public List<String> getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(List<String> assigneeType) {
        this.assigneeType = assigneeType;
    }

    public List<String> getReproducedIn() {
        return reproducedIn;
    }

    public void setReproducedIn(List<String> reproducedIn) {
        this.reproducedIn = reproducedIn;
    }

    public List<String> getReproducedInType() {
        return reproducedInType;
    }

    public void setReproducedInType(List<String> reproducedInType) {
        this.reproducedInType = reproducedInType;
    }

    public List<String> getReproduces() {
        return reproduces;
    }

    public void setReproduces(List<String> reproduces) {
        this.reproduces = reproduces;
    }

    public List<String> getReproducesType() {
        return reproducesType;
    }

    public void setReproducesType(List<String> reproducesType) {
        this.reproducesType = reproducesType;
    }

    public List<String> getIsAbout() {
        return isAbout;
    }

    public void setIsAbout(List<String> isAbout) {
        this.isAbout = isAbout;
    }

    public List<String> getIsAboutType() {
        return isAboutType;
    }

    public void setIsAboutType(List<String> isAboutType) {
        this.isAboutType = isAboutType;
    }

    public List<String> getSpecifiedOutputOf() {
        return specifiedOutputOf;
    }

    public void setSpecifiedOutputOf(List<String> specifiedOutputOf) {
        this.specifiedOutputOf = specifiedOutputOf;
    }

    public List<String> getSpecifiedOutputOfType() {
        return specifiedOutputOfType;
    }

    public void setSpecifiedOutputOfType(List<String> specifiedOutputOfType) {
        this.specifiedOutputOfType = specifiedOutputOfType;
    }

    public String getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
    }

    public List<String> getMentions() {
        return mentions;
    }

    public void setMentions(List<String> mentions) {
        this.mentions = mentions;
    }

    public List<String> getMentionType() {
        return mentionType;
    }

    public void setMentionType(List<String> mentionType) {
        this.mentionType = mentionType;
    }

    public List<String> getParticipatesIn() {
        return participatesIn;
    }

    public void setParticipatesIn(List<String> participatesIn) {
        this.participatesIn = participatesIn;
    }

    public List<String> getSupportedBy() {
        return supportedBy;
    }

    public void setSupportedBy(List<String> supportedBy) {
        this.supportedBy = supportedBy;
    }

    public List<String> getSupportedByType() {
        return supportedByType;
    }

    public void setSupportedByType(List<String> supportedByType) {
        this.supportedByType = supportedByType;
    }

    public List<String> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<String> receipts) {
        this.receipts = receipts;
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

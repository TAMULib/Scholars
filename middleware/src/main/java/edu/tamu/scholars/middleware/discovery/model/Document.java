package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "documents")
@CollectionSource(key = "document.class")
public class Document extends AbstractSolrDocument {

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/title", key = "document.title")
    private String title;

    @Indexed
    @PropertySource(template = "document/type", key = "document.type", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "document/image", key = "document.image")
    private String image;

    @Indexed
    @PropertySource(template = "document/thumbnail", key = "document.thumbnail")
    private String thumbnail;

    @Indexed
    @PropertySource(template = "document/websiteUrl", key = "document.website.url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "document/websiteLabel", key = "document.website.label")
    private List<String> websiteLabel;

    @Field("abstract")
    @Indexed(value = "abstract", copyTo = "_text_")
    @JsonProperty("abstract")
    @PropertySource(template = "document/abstract", key = "document.abstract")
    private String abstractText;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/abbreviation", key = "document.abbreviation")
    private String abbreviation;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/publicationVenue", key = "document.publicationVenue", id = "publicationVenueId", unique = true)
    private List<String> publicationVenue;

    @Indexed
    private List<String> publicationVenueId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/publicationVenueFor", key = "document.publicationVenueFor", id = "publicationVenueForId", unique = true)
    private List<String> publicationVenueFor;

    @Indexed
    private List<String> publicationVenueForId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/etdChairedBy", key = "document.etdChairedBy.name", id = "etdChairedById")
    private List<String> etdChairedBy;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/etdChairedByOrganization", key = "document.etdChairedBy.organization")
    private List<String> etdChairedByOrganization;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/etdChairedByEmail", key = "document.etdChairedBy.email")
    private List<String> etdChairedByEmail;

    @Indexed
    private List<String> etdChairedById;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/author", key = "document.author.name", id = "authorId")
    private List<String> author;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/authorOrganization", key = "document.author.organization")
    private List<String> authorOrganization;

    @Indexed
    @PropertySource(template = "document/authorType", key = "document.author.type", parse = true)
    private List<String> authorType;

    @Indexed
    @PropertySource(template = "document/authorRank", key = "document.author.rank")
    private List<String> authorRank;

    @Indexed
    private List<String> authorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/authorList", key = "document.authorList")
    private List<String> authorList;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/editor", key = "document.editor.name", id = "editorId")
    private List<String> editor;

    @Indexed
    @PropertySource(template = "document/editorType", key = "document.editor.type", parse = true)
    private List<String> editorType;

    @Indexed
    @PropertySource(template = "document/editorRank", key = "document.editor.rank")
    private List<String> editorRank;

    @Indexed
    private List<String> editorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/editorList", key = "document.editorList")
    private List<String> editorList;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/bookTitle", key = "document.bookTitle")
    private String bookTitle;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/translator", key = "document.translator.name", id = "translatorId")
    private List<String> translator;

    @Indexed
    @PropertySource(template = "document/translatorType", key = "document.translator.type", parse = true)
    private List<String> translatorType;

    @Indexed
    private List<String> translatorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/status", key = "document.status", id = "statusId")
    private List<String> status;

    @Indexed
    private List<String> statusId;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/publicationDate", key = "document.publicationDate")
    private List<String> publicationDate;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/publisher", key = "document.publisher.name", id = "publisherId", unique = true)
    private List<String> publisher;

    @Indexed
    @PropertySource(template = "document/publisherType", key = "document.publisher.type", parse = true)
    private List<String> publisherType;

    @Indexed
    private List<String> publisherId;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/dateFiled", key = "document.dateFiled")
    private List<String> dateFiled;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/dateIssued", key = "document.dateIssued")
    private List<String> dateIssued;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/subjectArea", key = "document.subjectArea", id = "subjectAreaId")
    private List<String> subjectArea;

    @Indexed
    private List<String> subjectAreaId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/restriction", key = "document.restriction")
    private List<String> restriction;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/documentPart", key = "document.documentPart.name", id = "documentPartId")
    private List<String> documentPart;

    @Indexed
    @PropertySource(template = "document/documentPartType", key = "document.documentPart.type", parse = true)
    private List<String> documentPartType;

    @Indexed
    private List<String> documentPartId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/chapter", key = "document.chapter")
    private String chapter;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/feature", key = "document.feature.name", id = "featureId")
    private List<String> feature;

    @Indexed
    @PropertySource(template = "document/featureType", key = "document.feature.type", parse = true)
    private List<String> featureType;

    @Indexed
    private List<String> featureId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/edition", key = "document.edition")
    private String edition;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/geographicFocus", key = "document.geographicFocus.name", id = "geographicFocusId")
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "document/geographicFocusType", key = "document.geographicFocus.type", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/documentationForProjectOrResource", key = "document.documentationForProjectOrResource.name", id = "documentationForProjectOrResourceId")
    private List<String> documentationForProjectOrResource;

    @Indexed
    @PropertySource(template = "document/documentationForProjectOrResourceType", key = "document.documentationForProjectOrResource.type", parse = true)
    private List<String> documentationForProjectOrResourceType;

    @Indexed
    private List<String> documentationForProjectOrResourceId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/outputOfProcessOrEvent", key = "document.outputOfProcessOrEvent.name", id = "outputOfProcessOrEventId")
    private List<String> outputOfProcessOrEvent;

    @Indexed
    @PropertySource(template = "document/outputOfProcessOrEventType", key = "document.outputOfProcessOrEvent.type", parse = true)
    private List<String> outputOfProcessOrEventType;

    @Indexed
    private List<String> outputOfProcessOrEventId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/presentedAt", key = "document.presentedAt.name", id = "presentedAtId")
    private List<String> presentedAt;

    @Indexed
    @PropertySource(template = "document/presentedAtType", key = "document.presentedAt.type", parse = true)
    private List<String> presentedAtType;

    @Indexed
    private List<String> presentedAtId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/keyword", key = "document.keyword")
    private List<String> keyword;

    @Indexed
    @PropertySource(template = "document/eanucc13", key = "document.eanucc13")
    private String eanucc13;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/nihmsid", key = "document.nihmsid")
    private String nihmsid;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/pmcid", key = "document.pmcid")
    private String pmcid;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/identifier", key = "document.identifier")
    private String identifier;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/patentNumber", key = "document.patentNumber")
    private List<String> patentNumber;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/sameAs", key = "document.sameAs.label", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "document/sameAsType", key = "document.sameAs.type", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/doi", key = "document.doi")
    private String doi;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/oclcnum", key = "document.oclcnum")
    private String oclcnum;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/isbn10", key = "document.isbn10")
    private String isbn10;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/isbn13", key = "document.isbn13")
    private String isbn13;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/pmid", key = "document.pmid")
    private String pmid;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/lccn", key = "document.lccn")
    private String lccn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/issn", key = "document.issn")
    private String issn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/eissn", key = "document.eissn")
    private String eissn;

    @Indexed
    @PropertySource(template = "document/uri", key = "document.uri")
    private List<String> uri;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/citedBy", key = "document.citedBy.title", id = "citedById")
    private List<String> citedBy;

    @Indexed
    @PropertySource(template = "document/citedByType", key = "document.citedBy.type", parse = true)
    private List<String> citedByType;

    @Indexed
    private List<String> citedById;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/citation", key = "document.citation.text", id = "citationId")
    private List<String> citation;

    @Indexed
    @PropertySource(template = "document/citationType", key = "document.citation.type", parse = true)
    private List<String> citationType;

    @Indexed
    private List<String> citationId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/citesAsDataSource", key = "document.citesAsDataSource.label", id = "citesAsDataSourceId")
    private List<String> citesAsDataSource;

    @Indexed
    @PropertySource(template = "document/citesAsDataSourceType", key = "document.citesAsDataSource.type", parse = true)
    private List<String> citesAsDataSourceType;

    @Indexed
    private List<String> citesAsDataSourceId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/translation", key = "document.translation.title", id = "translationId")
    private List<String> translation;

    @Indexed
    @PropertySource(template = "document/translationType", key = "document.translation.type", parse = true)
    private List<String> translationType;

    @Indexed
    private List<String> translationId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/translationOf", key = "document.translationOf.title", id = "translationOfId")
    private List<String> translationOf;

    @Indexed
    @PropertySource(template = "document/translationOfType", key = "document.translationOf.type", parse = true)
    private List<String> translationOfType;

    @Indexed
    private List<String> translationOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/globalCitationFrequency", key = "document.globalCitationFrequency", id = "globalCitationFrequencyId")
    private List<String> globalCitationFrequency;

    @Indexed
    private List<String> globalCitationFrequencyId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/iclCode", key = "document.iclCode")
    private String iclCode;

    @Indexed
    @PropertySource(template = "document/numberOfPages", key = "document.numberOfPages")
    private String numberOfPages;

    @Indexed
    @PropertySource(template = "document/pageStart", key = "document.pageStart")
    private String pageStart;

    @Indexed
    @PropertySource(template = "document/pageEnd", key = "document.pageEnd")
    private String pageEnd;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/volume", key = "document.volume")
    private String volume;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/issue", key = "document.issue")
    private String issue;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/placeOfPublication", key = "document.placeOfPublication")
    private String placeOfPublication;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/assignee", key = "document.assignee.name", id = "assigneeId")
    private List<String> assignee;

    @Indexed
    @PropertySource(template = "document/assigneeType", key = "document.assignee.type", parse = true)
    private List<String> assigneeType;

    @Indexed
    private List<String> assigneeId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/reproducedIn", key = "document.reproducedIn", id = "reproducedInId")
    private List<String> reproducedIn;

    @Indexed
    private List<String> reproducedInId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/reproduces", key = "document.reproduces.title", id = "reproducesId")
    private List<String> reproduces;

    @Indexed
    @PropertySource(template = "document/reproducesType", key = "document.reproduces.type", parse = true)
    private List<String> reproducesType;

    @Indexed
    private List<String> reproducesId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/isAbout", key = "document.isAbout.label", id = "isAboutId")
    private List<String> isAbout;

    @Indexed
    @PropertySource(template = "document/isAboutType", key = "document.isAbout.type", parse = true)
    private List<String> isAboutType;

    @Indexed
    private List<String> isAboutId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/specifiedOutputOf", key = "document.specifiedOutputOf.name", id = "specifiedOutputOfId")
    private List<String> specifiedOutputOf;

    @Indexed
    @PropertySource(template = "document/specifiedOutputOfType", key = "document.specifiedOutputOf.type", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed
    private List<String> specifiedOutputOfId;

    @Indexed
    @PropertySource(template = "document/isTemplate", key = "document.isTemplate")
    private String isTemplate;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/mention", key = "document.mention.name", id = "mentionId")
    private List<String> mention;

    @Indexed
    @PropertySource(template = "document/mentionType", key = "document.mention.type", parse = true)
    private List<String> mentionType;

    @Indexed
    private List<String> mentionId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/participatesIn", key = "document.participatesIn", id = "participatesInId")
    private List<String> participatesIn;

    @Indexed
    private List<String> participatesInId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/supportedBy", key = "document.supportedBy.name", id = "supportedById")
    private List<String> supportedBy;

    @Indexed
    @PropertySource(template = "document/supportedByType", key = "document.supportedBy.type", parse = true)
    private List<String> supportedByType;

    @Indexed
    private List<String> supportedById;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/modTime", key = "document.modTime")
    private String modTime;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "document/receipt", key = "document.receipt", id = "receiptId")
    private List<String> receipt;

    @Indexed
    private List<String> receiptId;

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

    public List<String> getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(List<String> websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public List<String> getWebsiteLabel() {
        return websiteLabel;
    }

    public void setWebsiteLabel(List<String> websiteLabel) {
        this.websiteLabel = websiteLabel;
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

    public List<String> getPublicationVenue() {
        return publicationVenue;
    }

    public void setPublicationVenue(List<String> publicationVenue) {
        this.publicationVenue = publicationVenue;
    }

    public List<String> getPublicationVenueId() {
        return publicationVenueId;
    }

    public void setPublicationVenueId(List<String> publicationVenueId) {
        this.publicationVenueId = publicationVenueId;
    }

    public List<String> getPublicationVenueFor() {
        return publicationVenueFor;
    }

    public void setPublicationVenueFor(List<String> publicationVenueFor) {
        this.publicationVenueFor = publicationVenueFor;
    }

    public List<String> getPublicationVenueForId() {
        return publicationVenueForId;
    }

    public void setPublicationVenueForId(List<String> publicationVenueForId) {
        this.publicationVenueForId = publicationVenueForId;
    }

    public List<String> getEtdChairedBy() {
        return etdChairedBy;
    }

    public void setEtdChairedBy(List<String> etdChairedBy) {
        this.etdChairedBy = etdChairedBy;
    }

    public List<String> getEtdChairedByOrganization() {
        return etdChairedByOrganization;
    }

    public void setEtdChairedByOrganization(List<String> etdChairedByOrganization) {
        this.etdChairedByOrganization = etdChairedByOrganization;
    }

    public List<String> getEtdChairedByEmail() {
        return etdChairedByEmail;
    }

    public void setEtdChairedByEmail(List<String> etdChairedByEmail) {
        this.etdChairedByEmail = etdChairedByEmail;
    }

    public List<String> getEtdChairedById() {
        return etdChairedById;
    }

    public void setEtdChairedById(List<String> etdChairedById) {
        this.etdChairedById = etdChairedById;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getAuthorOrganization() {
        return authorOrganization;
    }

    public void setAuthorOrganization(List<String> authorOrganization) {
        this.authorOrganization = authorOrganization;
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

    public List<String> getAuthorId() {
        return authorId;
    }

    public void setAuthorId(List<String> authorId) {
        this.authorId = authorId;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public List<String> getEditor() {
        return editor;
    }

    public void setEditor(List<String> editor) {
        this.editor = editor;
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

    public List<String> getEditorId() {
        return editorId;
    }

    public void setEditorId(List<String> editorId) {
        this.editorId = editorId;
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

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public List<String> getTranslatorType() {
        return translatorType;
    }

    public void setTranslatorType(List<String> translatorType) {
        this.translatorType = translatorType;
    }

    public List<String> getTranslatorId() {
        return translatorId;
    }

    public void setTranslatorId(List<String> translatorId) {
        this.translatorId = translatorId;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getStatusId() {
        return statusId;
    }

    public void setStatusId(List<String> statusId) {
        this.statusId = statusId;
    }

    public List<String> getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(List<String> publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<String> getPublisher() {
        return publisher;
    }

    public void setPublisher(List<String> publisher) {
        this.publisher = publisher;
    }

    public List<String> getPublisherType() {
        return publisherType;
    }

    public void setPublisherType(List<String> publisherType) {
        this.publisherType = publisherType;
    }

    public List<String> getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(List<String> publisherId) {
        this.publisherId = publisherId;
    }

    public List<String> getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(List<String> dateFiled) {
        this.dateFiled = dateFiled;
    }

    public List<String> getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(List<String> dateIssued) {
        this.dateIssued = dateIssued;
    }

    public List<String> getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(List<String> subjectArea) {
        this.subjectArea = subjectArea;
    }

    public List<String> getSubjectAreaId() {
        return subjectAreaId;
    }

    public void setSubjectAreaId(List<String> subjectAreaId) {
        this.subjectAreaId = subjectAreaId;
    }

    public List<String> getRestriction() {
        return restriction;
    }

    public void setRestriction(List<String> restriction) {
        this.restriction = restriction;
    }

    public List<String> getDocumentPart() {
        return documentPart;
    }

    public void setDocumentPart(List<String> documentPart) {
        this.documentPart = documentPart;
    }

    public List<String> getDocumentPartType() {
        return documentPartType;
    }

    public void setDocumentPartType(List<String> documentPartType) {
        this.documentPartType = documentPartType;
    }

    public List<String> getDocumentPartId() {
        return documentPartId;
    }

    public void setDocumentPartId(List<String> documentPartId) {
        this.documentPartId = documentPartId;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public List<String> getFeature() {
        return feature;
    }

    public void setFeature(List<String> feature) {
        this.feature = feature;
    }

    public List<String> getFeatureType() {
        return featureType;
    }

    public void setFeatureType(List<String> featureType) {
        this.featureType = featureType;
    }

    public List<String> getFeatureId() {
        return featureId;
    }

    public void setFeatureId(List<String> featureId) {
        this.featureId = featureId;
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

    public List<String> getGeographicFocusId() {
        return geographicFocusId;
    }

    public void setGeographicFocusId(List<String> geographicFocusId) {
        this.geographicFocusId = geographicFocusId;
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

    public List<String> getDocumentationForProjectOrResourceId() {
        return documentationForProjectOrResourceId;
    }

    public void setDocumentationForProjectOrResourceId(List<String> documentationForProjectOrResourceId) {
        this.documentationForProjectOrResourceId = documentationForProjectOrResourceId;
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

    public List<String> getOutputOfProcessOrEventId() {
        return outputOfProcessOrEventId;
    }

    public void setOutputOfProcessOrEventId(List<String> outputOfProcessOrEventId) {
        this.outputOfProcessOrEventId = outputOfProcessOrEventId;
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

    public List<String> getPresentedAtId() {
        return presentedAtId;
    }

    public void setPresentedAtId(List<String> presentedAtId) {
        this.presentedAtId = presentedAtId;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
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

    public List<String> getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(List<String> patentNumber) {
        this.patentNumber = patentNumber;
    }

    public List<String> getSameAs() {
        return sameAs;
    }

    public void setSameAs(List<String> sameAs) {
        this.sameAs = sameAs;
    }

    public List<String> getSameAsType() {
        return sameAsType;
    }

    public void setSameAsType(List<String> sameAsType) {
        this.sameAsType = sameAsType;
    }

    public List<String> getSameAsId() {
        return sameAsId;
    }

    public void setSameAsId(List<String> sameAsId) {
        this.sameAsId = sameAsId;
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

    public List<String> getCitedById() {
        return citedById;
    }

    public void setCitedById(List<String> citedById) {
        this.citedById = citedById;
    }

    public List<String> getCitation() {
        return citation;
    }

    public void setCitation(List<String> citation) {
        this.citation = citation;
    }

    public List<String> getCitationType() {
        return citationType;
    }

    public void setCitationType(List<String> citationType) {
        this.citationType = citationType;
    }

    public List<String> getCitationId() {
        return citationId;
    }

    public void setCitationId(List<String> citationId) {
        this.citationId = citationId;
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

    public List<String> getCitesAsDataSourceId() {
        return citesAsDataSourceId;
    }

    public void setCitesAsDataSourceId(List<String> citesAsDataSourceId) {
        this.citesAsDataSourceId = citesAsDataSourceId;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<String> getTranslationType() {
        return translationType;
    }

    public void setTranslationType(List<String> translationType) {
        this.translationType = translationType;
    }

    public List<String> getTranslationId() {
        return translationId;
    }

    public void setTranslationId(List<String> translationId) {
        this.translationId = translationId;
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

    public List<String> getTranslationOfId() {
        return translationOfId;
    }

    public void setTranslationOfId(List<String> translationOfId) {
        this.translationOfId = translationOfId;
    }

    public List<String> getGlobalCitationFrequency() {
        return globalCitationFrequency;
    }

    public void setGlobalCitationFrequency(List<String> globalCitationFrequency) {
        this.globalCitationFrequency = globalCitationFrequency;
    }

    public List<String> getGlobalCitationFrequencyId() {
        return globalCitationFrequencyId;
    }

    public void setGlobalCitationFrequencyId(List<String> globalCitationFrequencyId) {
        this.globalCitationFrequencyId = globalCitationFrequencyId;
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

    public List<String> getAssignee() {
        return assignee;
    }

    public void setAssignee(List<String> assignee) {
        this.assignee = assignee;
    }

    public List<String> getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(List<String> assigneeType) {
        this.assigneeType = assigneeType;
    }

    public List<String> getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(List<String> assigneeId) {
        this.assigneeId = assigneeId;
    }

    public List<String> getReproducedIn() {
        return reproducedIn;
    }

    public void setReproducedIn(List<String> reproducedIn) {
        this.reproducedIn = reproducedIn;
    }

    public List<String> getReproducedInId() {
        return reproducedInId;
    }

    public void setReproducedInId(List<String> reproducedInId) {
        this.reproducedInId = reproducedInId;
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

    public List<String> getReproducesId() {
        return reproducesId;
    }

    public void setReproducesId(List<String> reproducesId) {
        this.reproducesId = reproducesId;
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

    public List<String> getIsAboutId() {
        return isAboutId;
    }

    public void setIsAboutId(List<String> isAboutId) {
        this.isAboutId = isAboutId;
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

    public List<String> getSpecifiedOutputOfId() {
        return specifiedOutputOfId;
    }

    public void setSpecifiedOutputOfId(List<String> specifiedOutputOfId) {
        this.specifiedOutputOfId = specifiedOutputOfId;
    }

    public String getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
    }

    public List<String> getMention() {
        return mention;
    }

    public void setMention(List<String> mention) {
        this.mention = mention;
    }

    public List<String> getMentionType() {
        return mentionType;
    }

    public void setMentionType(List<String> mentionType) {
        this.mentionType = mentionType;
    }

    public List<String> getMentionId() {
        return mentionId;
    }

    public void setMentionId(List<String> mentionId) {
        this.mentionId = mentionId;
    }

    public List<String> getParticipatesIn() {
        return participatesIn;
    }

    public void setParticipatesIn(List<String> participatesIn) {
        this.participatesIn = participatesIn;
    }

    public List<String> getParticipatesInId() {
        return participatesInId;
    }

    public void setParticipatesInId(List<String> participatesInId) {
        this.participatesInId = participatesInId;
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

    public List<String> getSupportedById() {
        return supportedById;
    }

    public void setSupportedById(List<String> supportedById) {
        this.supportedById = supportedById;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

    public List<String> getReceipt() {
        return receipt;
    }

    public void setReceipt(List<String> receipt) {
        this.receipt = receipt;
    }

    public List<String> getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(List<String> receiptId) {
        this.receiptId = receiptId;
    }

}

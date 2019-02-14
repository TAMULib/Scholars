package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;
import edu.tamu.scholars.middleware.harvest.annotation.Property;
import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "documents")
// @formatter:off
@CollectionSource(
    template = "document/documents",
    key = "document.class",
    properties = {
        @Property(name = "title", key = "document.title"),
        @Property(name = "type", key = "document.type", parse = true),
        @Property(name = "abstractText", key = "document.abstract"),
        @Property(name = "abbreviation", key = "document.abbreviation"),
        @Property(name = "authorList", key = "document.authorList"),
        @Property(name = "editorList", key = "document.editorList"),
        @Property(name = "bookTitle", key = "document.bookTitleForChapter"),
        @Property(name = "restriction", key = "document.restriction"),
        @Property(name = "chapter", key = "document.chapter"),
        @Property(name = "edition", key = "document.edition"),
        @Property(name = "keyword", key = "document.keyword"),
        @Property(name = "eanucc13", key = "document.eanucc13"),
        @Property(name = "nihmsid", key = "document.nihmsid"),
        @Property(name = "pmcid", key = "document.pmcid"),
        @Property(name = "identifier", key = "document.identifier"),
        @Property(name = "patentNumber", key = "document.patentNumber"),
        @Property(name = "doi", key = "document.doi"),
        @Property(name = "oclcnum", key = "document.oclcnum"),
        @Property(name = "isbn10", key = "document.isbn10"),
        @Property(name = "isbn13", key = "document.isbn13"),
        @Property(name = "pmid", key = "document.pmid"),
        @Property(name = "lccn", key = "document.lccn"),
        @Property(name = "issn", key = "document.issn"),
        @Property(name = "eissn", key = "document.eissn"),
        @Property(name = "uri", key = "document.uri"),
        @Property(name = "iclCode", key = "document.iclCode"),
        @Property(name = "numberOfPages", key = "document.numPages"),
        @Property(name = "pageStart", key = "document.pageStart"),
        @Property(name = "pageEnd", key = "document.pageEnd"),
        @Property(name = "volume", key = "document.volume"),
        @Property(name = "issue", key = "document.issue"),
        @Property(name = "placeOfPublication", key = "document.placeOfPublication"),
        @Property(name = "isTemplate", key = "document.ARG_0000001"),
        @Property(name = "modTime", key = "document.modTime"),
    }
)
// @formatter:on
public class Document extends AbstractSolrDocument {

    @Indexed
    private String title;

    @Indexed
    private List<String> type;

    @Indexed
    @PropertySource(template = "document/imageDirectDownloadUrl", key = "document.file.directDownloadUrl")
    private String imageDirectDownloadUrl;

    @Indexed
    @PropertySource(template = "document/imageFilename", key = "document.file.filename")
    private String imageFilename;

    @Indexed
    @PropertySource(template = "document/imageMimeType", key = "document.file.mimeType")
    private String imageMimeType;

    @Indexed
    @PropertySource(template = "document/thumbnailDirectDownloadUrl", key = "document.file.directDownloadUrl")
    private String thumbnailDirectDownloadUrl;

    @Indexed
    @PropertySource(template = "document/thumbnailFilename", key = "document.file.filename")
    private String thumbnailFilename;

    @Indexed
    @PropertySource(template = "document/thumbnailMimeType", key = "document.file.mimeType")
    private String thumbnailMimeType;

    @Indexed
    @PropertySource(template = "document/website", key = "document.website.url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "document/website", key = "document.website.label")
    private List<String> websiteLabel;

    @Field("abstract")
    @Indexed("abstract")
    @JsonProperty("abstract")
    private String abstractText;

    @Indexed
    private String abbreviation;

    @Indexed
    @PropertySource(template = "document/publicationVenue", key = "document.publicationVenue.label", id = "publicationVenueId")
    private List<String> publicationVenue;

    @Indexed
    private List<String> publicationVenueId;

    @Indexed
    @PropertySource(template = "document/publicationVenueFor", key = "document.publicationVenue.label", id = "publicationVenueForId")
    private List<String> publicationVenueFor;

    @Indexed
    private List<String> publicationVenueForId;

    @Indexed
    @PropertySource(template = "document/etdChairedBy", key = "document.person.label", id = "etdChairedById")
    private List<String> etdChairedBy;

    @Indexed
    @PropertySource(template = "document/etdChairedByEmail", key = "document.person.email")
    private List<String> etdChairedByEmail;

    @Indexed
    private List<String> etdChairedById;

    @Indexed
    @PropertySource(template = "document/author", key = "document.person.label", id = "authorId")
    private List<String> author;

    @Indexed
    @PropertySource(template = "document/authorType", key = "document.thing.type", parse = true)
    private List<String> authorType;

    @Indexed
    @PropertySource(template = "document/authorRank", key = "document.person.rank")
    private List<String> authorRank;

    @Indexed
    private List<String> authorId;

    @Indexed
    private List<String> authorList;

    @Indexed
    private List<String> editorList;

    @Indexed
    @PropertySource(template = "document/editor", key = "document.person.label", id = "editorId")
    private List<String> editor;

    @Indexed
    @PropertySource(template = "document/editorType", key = "document.thing.type", parse = true)
    private List<String> editorType;

    @Indexed
    @PropertySource(template = "document/editorRank", key = "document.person.rank")
    private List<String> editorRank;

    @Indexed
    private List<String> editorId;

    @Indexed
    private String bookTitle;

    @Indexed
    @PropertySource(template = "document/translator", key = "document.thing.label", id = "translatorId")
    private List<String> translator;

    @Indexed
    @PropertySource(template = "document/translatorType", key = "document.thing.type", parse = true)
    private List<String> translatorType;

    @Indexed
    private List<String> translatorId;

    @Indexed
    @PropertySource(template = "document/status", key = "document.thing.label", id = "statusId")
    private List<String> status;

    @Indexed
    private List<String> statusId;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/publicationDate", key = "document.date.dateTime", id = "publicationDateId")
    private List<String> publicationDate;

    @Indexed
    @PropertySource(template = "document/publicationDatePrecision", key = "document.date.dateTimePrecision", parse = true)
    private List<String> publicationDatePrecision;

    @Indexed
    private List<String> publicationDateId;

    @Indexed
    @PropertySource(template = "document/publisher", key = "document.thing.label", id = "publisherId")
    private List<String> publisher;

    @Indexed
    @PropertySource(template = "document/publisherType", key = "document.thing.type", parse = true)
    private List<String> publisherType;

    @Indexed
    private List<String> publisherId;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/dateFiled", key = "document.dateFiled")
    private List<String> dateFiled;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/dateIssued", key = "document.dateIssued")
    private List<String> dateIssued;

    @Indexed
    @PropertySource(template = "document/subjectArea", key = "document.thing.label", id = "subjectAreaId")
    private List<String> subjectArea;

    @Indexed
    private List<String> subjectAreaId;

    @Indexed
    private List<String> restriction;

    @Indexed
    @PropertySource(template = "document/documentPart", key = "document.thing.label", id = "documentPartId")
    private List<String> documentPart;

    @Indexed
    @PropertySource(template = "document/documentPartType", key = "document.thing.type", parse = true)
    private List<String> documentPartType;

    @Indexed
    private List<String> documentPartId;

    @Indexed
    private String chapter;

    @Indexed
    @PropertySource(template = "document/feature", key = "document.thing.label", id = "featureId")
    private List<String> feature;

    @Indexed
    @PropertySource(template = "document/featureType", key = "document.thing.type", parse = true)
    private List<String> featureType;

    @Indexed
    private List<String> featureId;

    @Indexed
    private String edition;

    @Indexed
    @PropertySource(template = "document/geographicFocus", key = "document.thing.label", id = "geographicFocusId")
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "document/geographicFocusType", key = "document.thing.type", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed
    @PropertySource(template = "document/documentationForProjectOrResource", key = "document.thing.label", id = "documentationForProjectOrResourceId")
    private List<String> documentationForProjectOrResource;

    @Indexed
    @PropertySource(template = "document/documentationForProjectOrResourceType", key = "document.thing.type", parse = true)
    private List<String> documentationForProjectOrResourceType;

    @Indexed
    private List<String> documentationForProjectOrResourceId;

    @Indexed
    @PropertySource(template = "document/outputOfProcessOrEvent", key = "document.thing.label", id = "outputOfProcessOrEventId")
    private List<String> outputOfProcessOrEvent;

    @Indexed
    @PropertySource(template = "document/outputOfProcessOrEventType", key = "document.thing.type", parse = true)
    private List<String> outputOfProcessOrEventType;

    @Indexed
    private List<String> outputOfProcessOrEventId;

    @Indexed
    @PropertySource(template = "document/presentedAt", key = "document.thing.label", id = "presentedAtId")
    private List<String> presentedAt;

    @Indexed
    @PropertySource(template = "document/presentedAtType", key = "document.thing.type", parse = true)
    private List<String> presentedAtType;

    @Indexed
    private List<String> presentedAtId;

    @Indexed
    private List<String> keyword;

    @Indexed
    private String eanucc13;

    @Indexed
    private String nihmsid;

    @Indexed
    private String pmcid;

    @Indexed
    private String identifier;

    @Indexed
    private List<String> patentNumber;

    @Indexed
    @PropertySource(template = "document/sameAs", key = "document.thing.label", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "document/sameAsType", key = "document.thing.type", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed
    private String doi;

    @Indexed
    private String oclcnum;

    @Indexed
    private String isbn10;

    @Indexed
    private String isbn13;

    @Indexed
    private String pmid;

    @Indexed
    private String lccn;

    @Indexed
    private String issn;

    @Indexed
    private String eissn;

    @Indexed
    private List<String> uri;

    @Indexed
    @PropertySource(template = "document/citedBy", key = "document.thing.label", id = "citedById")
    private List<String> citedBy;

    @Indexed
    @PropertySource(template = "document/citedByType", key = "document.thing.type", parse = true)
    private List<String> citedByType;

    @Indexed
    private List<String> citedById;

    @Indexed
    @PropertySource(template = "document/citation", key = "document.thing.label", id = "citationId")
    private List<String> citation;

    @Indexed
    @PropertySource(template = "document/citationType", key = "document.thing.type", parse = true)
    private List<String> citationType;

    @Indexed
    private List<String> citationId;

    @Indexed
    @PropertySource(template = "document/citesAsDataSource", key = "document.thing.label", id = "citesAsDataSourceId")
    private List<String> citesAsDataSource;

    @Indexed
    @PropertySource(template = "document/citesAsDataSourceType", key = "document.thing.type", parse = true)
    private List<String> citesAsDataSourceType;

    @Indexed
    private List<String> citesAsDataSourceId;

    @Indexed
    @PropertySource(template = "document/translation", key = "document.thing.label", id = "translationId")
    private List<String> translation;

    @Indexed
    @PropertySource(template = "document/translationType", key = "document.thing.type", parse = true)
    private List<String> translationType;

    @Indexed
    private List<String> translationId;

    @Indexed
    @PropertySource(template = "document/translationOf", key = "document.thing.label", id = "translationOfId")
    private List<String> translationOf;

    @Indexed
    @PropertySource(template = "document/translationOfType", key = "document.thing.type", parse = true)
    private List<String> translationOfType;

    @Indexed
    private List<String> translationOfId;

    @Indexed
    @PropertySource(template = "document/globalCitationFrequency", key = "document.thing.label", id = "globalCitationFrequencyId")
    private List<String> globalCitationFrequency;

    @Indexed
    private List<String> globalCitationFrequencyId;

    @Indexed
    private String iclCode;

    @Indexed
    private String numberOfPages;

    @Indexed
    private String pageStart;

    @Indexed
    private String pageEnd;

    @Indexed
    private String volume;

    @Indexed
    private String issue;

    @Indexed
    private String placeOfPublication;

    @Indexed
    @PropertySource(template = "document/assignee", key = "document.thing.label", id = "assigneeId")
    private List<String> assignee;

    @Indexed
    @PropertySource(template = "document/assigneeType", key = "document.thing.type", parse = true)
    private List<String> assigneeType;

    @Indexed
    private List<String> assigneeId;

    @Indexed
    @PropertySource(template = "document/reproducedIn", key = "document.thing.label", id = "reproducedInId")
    private List<String> reproducedIn;

    @Indexed
    private List<String> reproducedInId;

    @Indexed
    @PropertySource(template = "document/reproduces", key = "document.thing.label", id = "reproducesId")
    private List<String> reproduces;

    @Indexed
    @PropertySource(template = "document/reproducesType", key = "document.thing.type", parse = true)
    private List<String> reproducesType;

    @Indexed
    private List<String> reproducesId;

    @Indexed
    @PropertySource(template = "document/isAbout", key = "document.thing.label", id = "isAboutId")
    private List<String> isAbout;

    @Indexed
    @PropertySource(template = "document/isAboutType", key = "document.thing.type", parse = true)
    private List<String> isAboutType;

    @Indexed
    private List<String> isAboutId;

    @Indexed
    @PropertySource(template = "document/specifiedOutputOf", key = "document.thing.label", id = "specifiedOutputOfId")
    private List<String> specifiedOutputOf;

    @Indexed
    @PropertySource(template = "document/specifiedOutputOfType", key = "document.thing.type", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed
    private List<String> specifiedOutputOfId;

    @Indexed
    private List<String> isTemplate;

    @Indexed
    @PropertySource(template = "document/mention", key = "document.thing.label", id = "mentionId")
    private List<String> mention;

    @Indexed
    @PropertySource(template = "document/mentionType", key = "document.thing.type", parse = true)
    private List<String> mentionType;

    @Indexed
    private List<String> mentionId;

    @Indexed
    @PropertySource(template = "document/participatesIn", key = "document.thing.label", id = "participatesInId")
    private List<String> participatesIn;

    @Indexed
    private List<String> participatesInId;

    @Indexed
    @PropertySource(template = "document/supportedBy", key = "document.thing.label", id = "supportedById")
    private List<String> supportedBy;

    @Indexed
    @PropertySource(template = "document/supportedByType", key = "document.thing.type", parse = true)
    private List<String> supportedByType;

    @Indexed
    private List<String> supportedById;

    @Indexed(type = "pdate")
    private String modTime;

    @Indexed
    @PropertySource(template = "document/receipt", key = "document.thing.label", id = "receiptId")
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

    public String getImageDirectDownloadUrl() {
        return imageDirectDownloadUrl;
    }

    public void setImageDirectDownloadUrl(String imageDirectDownloadUrl) {
        this.imageDirectDownloadUrl = imageDirectDownloadUrl;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    public String getThumbnailDirectDownloadUrl() {
        return thumbnailDirectDownloadUrl;
    }

    public void setThumbnailDirectDownloadUrl(String thumbnailDirectDownloadUrl) {
        this.thumbnailDirectDownloadUrl = thumbnailDirectDownloadUrl;
    }

    public String getThumbnailFilename() {
        return thumbnailFilename;
    }

    public void setThumbnailFilename(String thumbnailFilename) {
        this.thumbnailFilename = thumbnailFilename;
    }

    public String getThumbnailMimeType() {
        return thumbnailMimeType;
    }

    public void setThumbnailMimeType(String thumbnailMimeType) {
        this.thumbnailMimeType = thumbnailMimeType;
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

    public List<String> getEditorList() {
        return editorList;
    }

    public void setEditorList(List<String> editorList) {
        this.editorList = editorList;
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

    public List<String> getPublicationDatePrecision() {
        return publicationDatePrecision;
    }

    public void setPublicationDatePrecision(List<String> publicationDatePrecision) {
        this.publicationDatePrecision = publicationDatePrecision;
    }

    public List<String> getPublicationDateId() {
        return publicationDateId;
    }

    public void setPublicationDateId(List<String> publicationDateId) {
        this.publicationDateId = publicationDateId;
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

    public List<String> getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(List<String> isTemplate) {
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

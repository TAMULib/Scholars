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
@CollectionSource(
    template = "document/documents",
    key = "document.class",
    properties = {
        @Property(name = "name", key = "document.label"),
        @Property(name = "type", key = "document.type", parse = true),
        @Property(name = "abstractText", key = "document.abstract"),
        @Property(name = "abbreviation", key = "document.abbreviation"),
        @Property(name = "authorList", key = "document.authorList"),
        @Property(name = "editorList", key = "document.editorList"),
        @Property(name = "bookTitle", key = "document.bookTitleForChapter"),
        @Property(name = "restriction", key = "document.restriction"),
        @Property(name = "chapter", key = "document.chapter"),
        @Property(name = "edition", key = "document.edition"),
        @Property(name = "freetextKeyword", key = "document.freetextKeyword"),
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
public class Document extends AbstractSolrDocument {

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
    private String name;

    @Indexed
    private List<String> type;

    @Indexed
    @PropertySource(template = "document/website", key = "document.website.url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "document/website", key = "document.website.label")
    private List<String> websiteLabel;

    @Field("abstract")
    @Indexed("abstract")
    @JsonProperty("abstract")
    private List<String> abstractText;

    @Indexed
    private List<String> abbreviation;

    @Indexed
    @PropertySource(template = "document/publicationVenueLabel", key = "document.publicationVenue.label", id = "publicationVenueId")
    private List<String> publicationVenueLabel;

    @Indexed
    private List<String> publicationVenueId;

    @Indexed
    @PropertySource(template = "document/publicationVenueForLabel", key = "document.publicationVenue.label", id = "publicationVenueForId")
    private List<String> publicationVenueForLabel;

    @Indexed
    private List<String> publicationVenueForId;

    @Indexed
    @PropertySource(template = "document/etdChairedByLabel", key = "document.person.label", id = "etdChairedById")
    private List<String> etdChairedByLabel;

    @Indexed
    @PropertySource(template = "document/etdChairedByEmail", key = "document.person.email")
    private List<String> etdChairedByEmail;

    @Indexed
    private List<String> etdChairedById;

    @Indexed
    @PropertySource(template = "document/authorLabel", key = "document.person.label", id = "authorId")
    private List<String> authorLabel;

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
    @PropertySource(template = "document/editorLabel", key = "document.person.label", id = "editorId")
    private List<String> editorLabel;

    @Indexed
    @PropertySource(template = "document/editorType", key = "document.thing.type", parse = true)
    private List<String> editorType;

    @Indexed
    @PropertySource(template = "document/editorRank", key = "document.person.rank")
    private List<String> editorRank;

    @Indexed
    private List<String> editorId;

    @Indexed
    private List<String> bookTitle;

    @Indexed
    @PropertySource(template = "document/translatorLabel", key = "document.thing.label", id = "translatorId")
    private List<String> translatorLabel;

    @Indexed
    @PropertySource(template = "document/translatorType", key = "document.thing.type", parse = true)
    private List<String> translatorType;

    @Indexed
    private List<String> translatorId;

    @Indexed
    @PropertySource(template = "document/status", key = "document.thing.label", id = "statusId")
    private List<String> statusLabel;

    @Indexed
    private List<String> statusId;

    @Indexed(type = "pdate")
    @PropertySource(template = "document/publicationDateDateTime", key = "document.date.dateTime", id = "publicationDateId")
    private List<String> publicationDate;

    @Indexed
    @PropertySource(template = "document/publicationDateDateTimePrecision", key = "document.date.dateTimePrecision", parse = true)
    private List<String> publicationDatePrecision;

    @Indexed
    private List<String> publicationDateId;

    @Indexed
    @PropertySource(template = "document/publisherLabel", key = "document.thing.label", id = "publisherId")
    private List<String> publisherLabel;

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
    @PropertySource(template = "document/subjectAreaLabel", key = "document.thing.label", id = "subjectAreaId")
    private List<String> subjectAreaLabel;

    @Indexed
    private List<String> subjectAreaId;

    @Indexed
    private List<String> restriction;

    @Indexed
    @PropertySource(template = "document/documentPartLabel", key = "document.thing.label", id = "documentPartId")
    private List<String> documentPartLabel;

    @Indexed
    @PropertySource(template = "document/documentPartType", key = "document.thing.type", parse = true)
    private List<String> documentPartType;

    @Indexed
    private List<String> documentPartId;

    @Indexed
    private List<String> chapter;

    @Indexed
    @PropertySource(template = "document/featureLabel", key = "document.thing.label", id = "featureId")
    private List<String> featureLabel;

    @Indexed
    @PropertySource(template = "document/featureType", key = "document.thing.type", parse = true)
    private List<String> featureType;

    @Indexed
    private List<String> featureId;

    @Indexed
    private List<String> edition;

    @Indexed
    @PropertySource(template = "document/geographicFocusLabel", key = "document.thing.label", id = "geographicFocusId")
    private List<String> geographicFocusLabel;

    @Indexed
    @PropertySource(template = "document/geographicFocusType", key = "document.thing.type", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed
    @PropertySource(template = "document/documentationForProjectOrResourceLabel", key = "document.thing.label", id = "documentationForProjectOrResourceId")
    private List<String> documentationForProjectOrResourceLabel;

    @Indexed
    @PropertySource(template = "document/documentationForProjectOrResourceType", key = "document.thing.type", parse = true)
    private List<String> documentationForProjectOrResourceType;

    @Indexed
    private List<String> documentationForProjectOrResourceId;

    @Indexed
    @PropertySource(template = "document/outputOfProcessOrEventLabel", key = "document.thing.label", id = "outputOfProcessOrEventId")
    private List<String> outputOfProcessOrEventLabel;

    @Indexed
    @PropertySource(template = "document/outputOfProcessOrEventType", key = "document.thing.type", parse = true)
    private List<String> outputOfProcessOrEventType;

    @Indexed
    private List<String> outputOfProcessOrEventId;

    @Indexed
    @PropertySource(template = "document/presentedAtLabel", key = "document.thing.label", id = "presentedAtId")
    private List<String> presentedAtLabel;

    @Indexed
    @PropertySource(template = "document/presentedAtType", key = "document.thing.type", parse = true)
    private List<String> presentedAtType;

    @Indexed
    private List<String> presentedAtId;

    @Indexed
    private List<String> freetextKeyword;

    @Indexed
    private List<String> eanucc13;

    @Indexed
    private List<String> nihmsid;

    @Indexed
    private List<String> pmcid;

    @Indexed
    private List<String> identifier;

    @Indexed
    private List<String> patentNumber;

    @Indexed
    @PropertySource(template = "document/sameAsLabel", key = "document.thing.label", id = "sameAsId")
    private List<String> sameAsLabel;

    @Indexed
    @PropertySource(template = "document/sameAsType", key = "document.thing.type", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed
    private List<String> doi;

    @Indexed
    private List<String> oclcnum;

    @Indexed
    private List<String> isbn10;

    @Indexed
    private List<String> isbn13;

    @Indexed
    private List<String> pmid;

    @Indexed
    private List<String> lccn;

    @Indexed
    private List<String> issn;

    @Indexed
    private List<String> eissn;

    @Indexed
    private List<String> uri;

    @Indexed
    @PropertySource(template = "document/citedByLabel", key = "document.thing.label", id = "citedById")
    private List<String> citedByLabel;

    @Indexed
    @PropertySource(template = "document/citedByType", key = "document.thing.type", parse = true)
    private List<String> citedByType;

    @Indexed
    private List<String> citedById;

    @Indexed
    @PropertySource(template = "document/citationLabel", key = "document.thing.label", id = "citationId")
    private List<String> citationLabel;

    @Indexed
    @PropertySource(template = "document/citationType", key = "document.thing.type", parse = true)
    private List<String> citationType;

    @Indexed
    private List<String> citationId;

    @Indexed
    @PropertySource(template = "document/citesAsDataSourceLabel", key = "document.thing.label", id = "citesAsDataSourceId")
    private List<String> citesAsDataSourceLabel;

    @Indexed
    @PropertySource(template = "document/citesAsDataSourceType", key = "document.thing.type", parse = true)
    private List<String> citesAsDataSourceType;

    @Indexed
    private List<String> citesAsDataSourceId;

    @Indexed
    @PropertySource(template = "document/translationLabel", key = "document.thing.label", id = "translationId")
    private List<String> translationLabel;

    @Indexed
    @PropertySource(template = "document/translationType", key = "document.thing.type", parse = true)
    private List<String> translationType;

    @Indexed
    private List<String> translationId;

    @Indexed
    @PropertySource(template = "document/translationOfLabel", key = "document.thing.label", id = "translationOfId")
    private List<String> translationOfLabel;

    @Indexed
    @PropertySource(template = "document/translationOfType", key = "document.thing.type", parse = true)
    private List<String> translationOfType;

    @Indexed
    private List<String> translationOfId;

    @Indexed
    @PropertySource(template = "document/globalCitationFrequencyLabel", key = "document.thing.label", id = "globalCitationFrequencyId")
    private List<String> globalCitationFrequencyLabel;

    @Indexed
    private List<String> globalCitationFrequencyId;

    @Indexed
    private List<String> iclCode;

    @Indexed
    private List<String> numberOfPages;

    @Indexed
    private List<String> pageStart;

    @Indexed
    private List<String> pageEnd;

    @Indexed
    private List<String> volume;

    @Indexed
    private List<String> issue;

    @Indexed
    private List<String> placeOfPublication;

    @Indexed
    @PropertySource(template = "document/assigneeLabel", key = "document.thing.label", id = "assigneeId")
    private List<String> assigneeLabel;

    @Indexed
    @PropertySource(template = "document/assigneeType", key = "document.thing.type", parse = true)
    private List<String> assigneeType;

    @Indexed
    private List<String> assigneeId;

    @Indexed
    @PropertySource(template = "document/reproducedInLabel", key = "document.thing.label", id = "reproducedInId")
    private List<String> reproducedInLabel;

    @Indexed
    private List<String> reproducedInId;

    @Indexed
    @PropertySource(template = "document/reproducesLabel", key = "document.thing.label", id = "reproducesId")
    private List<String> reproducesLabel;

    @Indexed
    @PropertySource(template = "document/reproducesType", key = "document.thing.type", parse = true)
    private List<String> reproducesType;

    @Indexed
    private List<String> reproducesId;

    @Indexed
    @PropertySource(template = "document/isAboutLabel", key = "document.thing.label", id = "isAboutId")
    private List<String> isAboutLabel;

    @Indexed
    @PropertySource(template = "document/isAboutType", key = "document.thing.type", parse = true)
    private List<String> isAboutType;

    @Indexed
    private List<String> isAboutId;

    @Indexed
    @PropertySource(template = "document/specifiedOutputOfLabel", key = "document.thing.label", id = "specifiedOutputOfId")
    private List<String> specifiedOutputOfLabel;

    @Indexed
    @PropertySource(template = "document/specifiedOutputOfType", key = "document.thing.type", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed
    private List<String> specifiedOutputOfId;

    @Indexed
    private List<String> isTemplate;

    @Indexed
    @PropertySource(template = "document/mentionLabel", key = "document.thing.label", id = "mentionId")
    private List<String> mentionLabel;

    @Indexed
    @PropertySource(template = "document/mentionType", key = "document.thing.type", parse = true)
    private List<String> mentionType;

    @Indexed
    private List<String> mentionId;

    @Indexed
    @PropertySource(template = "document/participatesInLabel", key = "document.thing.label", id = "participatesInId")
    private List<String> participatesInLabel;

    @Indexed
    private List<String> participatesInId;

    @Indexed
    @PropertySource(template = "document/supportedByLabel", key = "document.thing.label", id = "supportedById")
    private List<String> supportedByLabel;

    @Indexed
    @PropertySource(template = "document/supportedByType", key = "document.thing.type", parse = true)
    private List<String> supportedByType;

    @Indexed
    private List<String> supportedById;

    @Indexed(type = "pdate")
    private String modTime;

    @Indexed
    @PropertySource(template = "document/receiptLabel", key = "document.thing.label", id = "receiptId")
    private List<String> receiptLabel;

    @Indexed
    private List<String> receiptId;
}

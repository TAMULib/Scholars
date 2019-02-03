package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.tamu.scholars.middleware.harvest.annotation.Source;

@JsonInclude(NON_EMPTY)
//@formatter:off
@Source(
    key = "publication.class",
    sparql = {
        @Source.Sparql(
            template = "publication/publication",
            properties = {
                @Source.Property(name = "type", key = "publication.type.value", schema = "publication.type.schema", parse = true),
                @Source.Property(name = "identifier", key = "publication.identifier"),
                @Source.Property(name = "title", key = "publication.title.value", schema = "publication.title.schema", parse = false),
                @Source.Property(name = "issue", key = "publication.issue"),
                @Source.Property(name = "volume", key = "publication.volume"),
                @Source.Property(name = "doi", key = "publication.doi"),
                @Source.Property(name = "isbn10", key = "publication.isbn10"),
                @Source.Property(name = "isbn13", key = "publication.isbn13"),
                @Source.Property(name = "issn", key = "publication.issn"),
                @Source.Property(name = "pcmid", key = "publication.pcmid"),
                @Source.Property(name = "abstractText", key = "publication.abstract"),
                @Source.Property(name = "bookTitleForChapter", key = "publication.bookTitleForChapter"),
                @Source.Property(name = "freetextKeyword", key = "publication.freetextKeyword"),
                @Source.Property(name = "fullAuthorList", key = "publication.fullAuthorList"),
                @Source.Property(name = "fullEditorList", key = "publication.fullEditorList"),
                @Source.Property(name = "pageEnd", key = "publication.pageEnd"),
                @Source.Property(name = "pageStart", key = "publication.pageStart"),
                @Source.Property(name = "uri", key = "publication.uri")
            }
        ),
        @Source.Sparql(
            template = "publication/date",
            properties = {
                @Source.Property(name = "date", key = "publication.date")
            }
        ),
        @Source.Sparql(
            template = "publication/authors",
            properties = {
                @Source.Property(name = "authors", key = "publication.authors", id = "authorIds")
            }
        ),
        @Source.Sparql(
            template = "publication/journal",
            properties = {
                @Source.Property(name = "journal", key = "publication.journal.title", id = "journalId"),
                @Source.Property(name = "journalIssn", key = "publication.journal.issn")
            }),
        @Source.Sparql(
            template = "publication/publisher",
            properties = {
                @Source.Property(name = "publisher", key = "publication.publisher", id = "publisherId")
            }
        ),
        @Source.Sparql(
            template = "publication/publisherOfJournal", properties = {
                @Source.Property(name = "publisher", key = "publication.publisher", id = "publisherId")
            }
        ),
        @Source.Sparql(
            template = "publication/etdChairs",
            properties = {
                @Source.Property(name = "etdChairs", key = "publication.etdChairs", id = "etdChairIds")
            }
        ),
        @Source.Sparql(
            template = "publication/grants",
            properties = {
                @Source.Property(name = "grants", key = "publication.grants.title", id = "grantIds"),
                @Source.Property(name = "grantDates", key = "publication.grants.date")
            }
        ),
        @Source.Sparql(
            template = "publication/researchAreas",
            properties = {
                @Source.Property(name = "researchAreas", key = "publication.researchAreas", id = "researchAreaIds")
            }
        ),
        @Source.Sparql(
            template = "publication/subjectAreas",
            properties = {
                @Source.Property(name = "subjectAreas", key = "publication.subjectAreas", id = "subjectAreaIds")
            }
        )
    }
)
//@formatter:on
@SolrDocument(collection = "publications")
public class Publication extends AbstractSolrDocument {

    @Indexed
    private String type;

    @Indexed
    private String identifier;

    @Indexed
    private String title;

    @Indexed
    private String volume;

    @Indexed
    private String doi;

    @Indexed
    private String isbn10;

    @Indexed
    private String isbn13;

    @Indexed
    private String issn;

    @Indexed
    private String issue;

    @Indexed
    private String pcmid;

    @Field("abstract")
    @Indexed("abstract")
    @JsonProperty("abstract")
    private String abstractText;

    @Indexed
    private String bookTitleForChapter;

    @Indexed
    private String freetextKeyword;

    @Indexed
    private String fullAuthorList;

    @Indexed
    private String fullEditorList;

    @Indexed
    private String pageEnd;

    @Indexed
    private String pageStart;

    @Indexed
    private String uri;

    @Indexed(type = "pdate")
    private String date;

    @Indexed
    private String journal;

    @Indexed
    private String journalIssn;

    @Indexed
    private String journalId;

    @Indexed
    private String publisher;

    @Indexed
    private String publisherId;

    @Indexed
    private List < String > authors;

    @Indexed
    private List < String > authorIds;

    @Indexed
    private List < String > etdChairs;

    @Indexed
    private List < String > etdChairIds;

    @Indexed
    private List < String > grants;

    @Indexed
    private List < String > grantDates;

    @Indexed
    private List < String > grantIds;

    @Indexed
    private List < String > subjectAreas;

    @Indexed
    private List < String > subjectAreasIds;

    @Indexed
    private List < String > researchAreas;

    @Indexed
    private List < String > researchAreasIds;

    public Publication() {
        this.authors = new ArrayList < String > ();
        this.authorIds = new ArrayList < String > ();
        this.etdChairs = new ArrayList < String > ();
        this.etdChairIds = new ArrayList < String > ();
        this.grants = new ArrayList < String > ();
        this.grantDates = new ArrayList < String > ();
        this.grantIds = new ArrayList < String > ();
        this.subjectAreas = new ArrayList < String > ();
        this.subjectAreasIds = new ArrayList < String > ();
        this.researchAreas = new ArrayList < String > ();
        this.researchAreasIds = new ArrayList < String > ();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
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

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getPcmid() {
        return pcmid;
    }

    public void setPcmid(String pcmid) {
        this.pcmid = pcmid;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getBookTitleForChapter() {
        return bookTitleForChapter;
    }

    public void setBookTitleForChapter(String bookTitleForChapter) {
        this.bookTitleForChapter = bookTitleForChapter;
    }

    public String getFreetextKeyword() {
        return freetextKeyword;
    }

    public void setFreetextKeyword(String freetextKeyword) {
        this.freetextKeyword = freetextKeyword;
    }

    public String getFullAuthorList() {
        return fullAuthorList;
    }

    public void setFullAuthorList(String fullAuthorList) {
        this.fullAuthorList = fullAuthorList;
    }

    public String getFullEditorList() {
        return fullEditorList;
    }

    public void setFullEditorList(String fullEditorList) {
        this.fullEditorList = fullEditorList;
    }

    public String getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(String pageEnd) {
        this.pageEnd = pageEnd;
    }

    public String getPageStart() {
        return pageStart;
    }

    public void setPageStart(String pageStart) {
        this.pageStart = pageStart;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getJournalIssn() {
        return journalIssn;
    }

    public void setJournalIssn(String journalIssn) {
        this.journalIssn = journalIssn;
    }

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public List < String > getAuthors() {
        return authors;
    }

    public void setAuthors(List < String > authors) {
        this.authors = authors;
    }

    public List < String > getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List < String > authorIds) {
        this.authorIds = authorIds;
    }

    public List < String > getEtdChairs() {
        return etdChairs;
    }

    public void setEtdChairs(List < String > etdChairs) {
        this.etdChairs = etdChairs;
    }

    public List < String > getEtdChairIds() {
        return etdChairIds;
    }

    public void setEtdChairIds(List < String > etdChairIds) {
        this.etdChairIds = etdChairIds;
    }

    public List < String > getGrants() {
        return grants;
    }

    public void setGrants(List < String > grants) {
        this.grants = grants;
    }

    public List < String > getGrantDates() {
        return grantDates;
    }

    public void setGrantDates(List < String > grantDates) {
        this.grantDates = grantDates;
    }

    public List < String > getGrantIds() {
        return grantIds;
    }

    public void setGrantIds(List < String > grantIds) {
        this.grantIds = grantIds;
    }

    public List < String > getSubjectAreas() {
        return subjectAreas;
    }

    public void setSubjectAreas(List < String > subjectAreas) {
        this.subjectAreas = subjectAreas;
    }

    public List < String > getSubjectAreasIds() {
        return subjectAreasIds;
    }

    public void setSubjectAreasIds(List < String > subjectAreasIds) {
        this.subjectAreasIds = subjectAreasIds;
    }

    public List < String > getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(List < String > researchAreas) {
        this.researchAreas = researchAreas;
    }

    public List < String > getResearchAreasIds() {
        return researchAreasIds;
    }

    public void setResearchAreasIds(List < String > researchAreasIds) {
        this.researchAreasIds = researchAreasIds;
    }

}
package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;

@JsonInclude(NON_EMPTY)
@CollectionSource(template = "document/documents", key = "document.class")
//@formatter:off
//@PropertySource(
//    key = "document.class",
//    sparql = {
//        @PropertySource.Sparql(
//            template = "document/document",
//            properties = {
//                @PropertySource.Property(name = "type", key = "document.type", parse = true),
//                @PropertySource.Property(name = "identifier", key = "document.identifier"),
//                @PropertySource.Property(name = "title", key = "document.title"),
//                @PropertySource.Property(name = "issue", key = "document.issue"),
//                @PropertySource.Property(name = "volume", key = "document.volume"),
//                @PropertySource.Property(name = "doi", key = "document.doi"),
//                @PropertySource.Property(name = "isbn10", key = "document.isbn10"),
//                @PropertySource.Property(name = "isbn13", key = "document.isbn13"),
//                @PropertySource.Property(name = "issn", key = "document.issn"),
//                @PropertySource.Property(name = "pcmid", key = "document.pcmid"),
//                @PropertySource.Property(name = "abstractText", key = "document.abstract"),
//                @PropertySource.Property(name = "bookTitleForChapter", key = "document.bookTitleForChapter"),
//                @PropertySource.Property(name = "freetextKeyword", key = "document.freetextKeyword"),
//                @PropertySource.Property(name = "fullAuthorList", key = "document.fullAuthorList"),
//                @PropertySource.Property(name = "fullEditorList", key = "document.fullEditorList"),
//                @PropertySource.Property(name = "pageEnd", key = "document.pageEnd"),
//                @PropertySource.Property(name = "pageStart", key = "document.pageStart"),
//                @PropertySource.Property(name = "uri", key = "document.uri")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/date",
//            properties = {
//                @PropertySource.Property(name = "date", key = "document.date")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/authors",
//            properties = {
//                @PropertySource.Property(name = "authors", key = "document.authors", id = "authorIds")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/journal",
//            properties = {
//                @PropertySource.Property(name = "journal", key = "document.journal.title", id = "journalId"),
//                @PropertySource.Property(name = "journalIssn", key = "document.journal.issn")
//            }),
//        @PropertySource.Sparql(
//            template = "document/publisher",
//            properties = {
//                @PropertySource.Property(name = "publisher", key = "document.publisher", id = "publisherId")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/publisherOfJournal", properties = {
//                @PropertySource.Property(name = "publisher", key = "document.publisher", id = "publisherId")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/etdChairs",
//            properties = {
//                @PropertySource.Property(name = "etdChairs", key = "document.etdChairs", id = "etdChairIds")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/links",
//            properties = {
//                @PropertySource.Property(name = "linkUrls", key = "person.links.url"),
//                @PropertySource.Property(name = "linkLabels", key = "person.links.label")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/grants",
//            properties = {
//                @PropertySource.Property(name = "grants", key = "document.grants.title", id = "grantIds"),
//                @PropertySource.Property(name = "grantDates", key = "document.grants.date")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/researchAreas",
//            properties = {
//                @PropertySource.Property(name = "researchAreas", key = "document.researchAreas", id = "researchAreaIds")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "document/subjectAreas",
//            properties = {
//                @PropertySource.Property(name = "subjectAreas", key = "document.subjectAreas", id = "subjectAreaIds")
//            }
//        )
//    }
//)
//@formatter:on
@SolrDocument(collection = "documents")
public class Document extends AbstractSolrDocument {

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
    private List<String> authors;

    @Indexed
    private List<String> authorIds;

    @Indexed
    private List<String> etdChairs;

    @Indexed
    private List<String> etdChairIds;

    @Indexed
    private List<String> linkUrls;

    @Indexed
    private List<String> linkLabels;

    @Indexed
    private List<String> grants;

    @Indexed
    private List<String> grantDates;

    @Indexed
    private List<String> grantIds;

    @Indexed
    private List<String> subjectAreas;

    @Indexed
    private List<String> subjectAreasIds;

    @Indexed
    private List<String> researchAreas;

    @Indexed
    private List<String> researchAreasIds;

    public Document() {
        this.authors = new ArrayList<String>();
        this.authorIds = new ArrayList<String>();
        this.etdChairs = new ArrayList<String>();
        this.etdChairIds = new ArrayList<String>();
        this.linkUrls = new ArrayList<String>();
        this.linkLabels = new ArrayList<String>();
        this.grants = new ArrayList<String>();
        this.grantDates = new ArrayList<String>();
        this.grantIds = new ArrayList<String>();
        this.subjectAreas = new ArrayList<String>();
        this.subjectAreasIds = new ArrayList<String>();
        this.researchAreas = new ArrayList<String>();
        this.researchAreasIds = new ArrayList<String>();
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<String> authorIds) {
        this.authorIds = authorIds;
    }

    public List<String> getEtdChairs() {
        return etdChairs;
    }

    public void setEtdChairs(List<String> etdChairs) {
        this.etdChairs = etdChairs;
    }

    public List<String> getEtdChairIds() {
        return etdChairIds;
    }

    public void setEtdChairIds(List<String> etdChairIds) {
        this.etdChairIds = etdChairIds;
    }

    public List<String> getLinkUrls() {
        return linkUrls;
    }

    public void setLinkUrls(List<String> linkUrls) {
        this.linkUrls = linkUrls;
    }

    public List<String> getLinkLabels() {
        return linkLabels;
    }

    public void setLinkLabels(List<String> linkLabels) {
        this.linkLabels = linkLabels;
    }

    public List<String> getGrants() {
        return grants;
    }

    public void setGrants(List<String> grants) {
        this.grants = grants;
    }

    public List<String> getGrantDates() {
        return grantDates;
    }

    public void setGrantDates(List<String> grantDates) {
        this.grantDates = grantDates;
    }

    public List<String> getGrantIds() {
        return grantIds;
    }

    public void setGrantIds(List<String> grantIds) {
        this.grantIds = grantIds;
    }

    public List<String> getSubjectAreas() {
        return subjectAreas;
    }

    public void setSubjectAreas(List<String> subjectAreas) {
        this.subjectAreas = subjectAreas;
    }

    public List<String> getSubjectAreasIds() {
        return subjectAreasIds;
    }

    public void setSubjectAreasIds(List<String> subjectAreasIds) {
        this.subjectAreasIds = subjectAreasIds;
    }

    public List<String> getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(List<String> researchAreas) {
        this.researchAreas = researchAreas;
    }

    public List<String> getResearchAreasIds() {
        return researchAreasIds;
    }

    public void setResearchAreasIds(List<String> researchAreasIds) {
        this.researchAreasIds = researchAreasIds;
    }

}
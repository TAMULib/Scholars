package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.harvest.annotation.Source;

@JsonInclude(NON_EMPTY)
//@formatter:off
@Source(
    key = "publication.class",
    sparql = {
		@Source.Sparql(
            template = "publication/publication",
            properties = {
                @Source.Property(name = "abstractText", key = "publication.abstract"),
                @Source.Property(name = "bookTitleForChapter", key = "publication.bookTitleForChapter"),
                @Source.Property(name = "doi", key = "publication.doi"),
                @Source.Property(name = "freetextKeyword", key = "publication.freetextKeyword"),
                @Source.Property(name = "fullAuthorList", key = "publication.fullAuthorList"),
                @Source.Property(name = "fullEditorList", key = "publication.fullEditorList"),
                @Source.Property(name = "identifier", key = "publication.identifier"),
                @Source.Property(name = "isbn10", key = "publication.isbn10"),
                @Source.Property(name = "isbn13", key = "publication.isbn13"),
                @Source.Property(name = "issn", key = "publication.issn"),
                @Source.Property(name = "issue", key = "publication.issue"),
                @Source.Property(name = "pcmid", key = "publication.pcmid"),
                @Source.Property(name = "title", key = "publication.title.value", schema = "publication.title.schema", parse = false),
                @Source.Property(name = "type", key = "publication.type.value", schema = "publication.type.schema", parse = true),
                @Source.Property(name = "pageEnd", key = "publication.pageEnd"),
                @Source.Property(name = "pageStart", key = "publication.pageStart"),
                @Source.Property(name = "uri", key = "publication.uri"),
                @Source.Property(name = "volume", key = "publication.volume"),
            }
        ),
        @Source.Sparql(
            template = "publication/authors",
            properties = {
                @Source.Property(name = "authors", key = "publication.authors.title", id = "authorIds")
            }
        ),
        @Source.Sparql(
            template = "publication/contacts",
            properties = {
                @Source.Property(name = "contacts", key = "publication.contacts.title", id = "contactIds"),
                @Source.Property(name = "contactEmails", key = "publication.contacts.email"),
                @Source.Property(name = "contactPhones", key = "publication.contacts.phone")
            }
        ),
        @Source.Sparql(
            template = "publication/etdChairs",
            properties = {
                @Source.Property(name = "etdChairs", key = "publication.etdChairs.title", id = "etdChairIds"),
                @Source.Property(name = "etdChairEmails", key = "publication.etdChairs.email"),
                @Source.Property(name = "etdChairPhones", key = "publication.etdChairs.phone")
            }
        ),
        @Source.Sparql(
            template = "publication/publishers",
            properties = {
                @Source.Property(name = "publishers", key = "publication.publishers.title", id = "publisherIds"),
                @Source.Property(name = "publisherEmails", key = "publication.publishers.email"),
                @Source.Property(name = "publisherPhones", key = "publication.publishers.phone")
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
        ),
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

    @Indexed
    private String title;

    @Field(value = "abstract")
    @Indexed(name = "abstract")
    private String abstractText;

    @Indexed
    private String pageEnd;

    @Indexed
    private String pageStart;

    @Indexed
    private String volume;

    @Indexed
    private String bookTitleForChapter;

    @Indexed
    private String freetextKeyword;

    @Indexed
    private String fullAuthorList;

    @Indexed
    private String fullEditorList;

    @Indexed
    private String uri;

    @Indexed(type = "pdate")
    private String datePublished;

    @Indexed
    private List<String> authors;

    @Indexed
    private List<String> authorIds;

    @Indexed
    private List<String> publishers;

    @Indexed
    private List<String> publisherEmails;

    @Indexed
    private List<String> publisherPhones;

    @Indexed
    private List<String> publisherIds;

    @Indexed
    private List<String> etdChairs;

    @Indexed
    private List<String> etdChairEmails;

    @Indexed
    private List<String> etdChairPhones;

    @Indexed
    private List<String> etdChairIds;
    
    @Indexed
    private List<String> contacts;

    @Indexed
    private List<String> contactEmails;

    @Indexed
    private List<String> contactPhones;

    @Indexed
    private List<String> contactIds;

    @Indexed
    private List<String> grant;

    @Indexed
    private List<String> grantIds;

    @Indexed
    private List<String> journal;

    @Indexed
    private List<String> journalIds;

    @Indexed
    private List<String> journalIssn;

    @Indexed
    private List<String> subjectAreas;

    @Indexed
    private List<String> subjectAreasIds;

    @Indexed
    private List<String> researchAreas;

    @Indexed
    private List<String> researchAreasIds;

    public Publication() {
        this.authors = new ArrayList<String>();
        this.authorIds = new ArrayList<String>();
        this.publishers = new ArrayList<String>();
        this.publisherEmails = new ArrayList<String>();
        this.publisherPhones = new ArrayList<String>();
        this.publisherIds = new ArrayList<String>();
        this.etdChairs = new ArrayList<String>();
        this.etdChairEmails = new ArrayList<String>();
        this.etdChairPhones = new ArrayList<String>();
        this.etdChairIds = new ArrayList<String>();
        this.contacts = new ArrayList<String>();
        this.contactEmails = new ArrayList<String>();
        this.contactPhones = new ArrayList<String>();
        this.contactIds = new ArrayList<String>();
        this.grant = new ArrayList<String>();
        this.grantIds = new ArrayList<String>();
        this.journal = new ArrayList<String>();
        this.journalIds = new ArrayList<String>();
        this.journalIssn = new ArrayList<String>();
        this.subjectAreas = new ArrayList<String>();
        this.subjectAreasIds = new ArrayList<String>();
        this.researchAreas = new ArrayList<String>();
        this.researchAreasIds = new ArrayList<String>();
    }

}

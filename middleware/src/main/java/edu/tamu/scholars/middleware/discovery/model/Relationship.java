package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "relationships")
@CollectionSource(key = "relationship.class")
public class Relationship extends AbstractSolrDocument {

    @Indexed
    @PropertySource(template = "relationship/title", key = "relationship.title")
    private String title;

    @Indexed
    @PropertySource(template = "relationship/type", key = "relationship.type", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "relationship/image", key = "relationship.image")
    private String image;

    @Indexed
    @PropertySource(template = "relationship/thumbnail", key = "relationship.thumbnail")
    private String thumbnail;

    @Indexed
    @PropertySource(template = "relationship/description", key = "relationship.description")
    private String description;

    @Indexed
    @PropertySource(template = "relationship/receiptOf", key = "relationship.receiptOf.label", id = "receiptOfId")
    private List<String> receiptOf;

    @Indexed
    @PropertySource(template = "relationship/receiptOfType", key = "relationship.receiptOf.type", parse = true)
    private List<String> receiptOfType;

    @Indexed
    private List<String> receiptOfId;

    @Indexed
    @PropertySource(template = "relationship/awardOrHonorFor", key = "relationship.awardOrHonorFor.label", id = "awardOrHonorForId")
    private List<String> awardOrHonorFor;

    @Indexed
    @PropertySource(template = "relationship/awardOrHonorForType", key = "relationship.awardOrHonorFor.type", parse = true)
    private List<String> awardOrHonorForType;

    @Indexed
    private List<String> awardOrHonorForId;

    @Indexed
    @PropertySource(template = "relationship/awardConferredBy", key = "relationship.awardConferredBy.label", id = "awardConferredById")
    private List<String> awardConferredBy;

    @Indexed
    @PropertySource(template = "relationship/awardConferredByType", key = "relationship.awardConferredBy.type", parse = true)
    private List<String> awardConferredByType;

    @Indexed
    private List<String> awardConferredById;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalStart", key = "relationship.dateTimeInterval.start")
    private List<String> dateTimeIntervalStart;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalEnd", key = "relationship.dateTimeInterval.end")
    private List<String> dateTimeIntervalEnd;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/yearAwarded", key = "relationship.yearAwarded")
    private List<String> yearAwarded;

    @Indexed
    @PropertySource(template = "relationship/sameAs", key = "relationship.sameAs.label", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "relationship/sameAs", key = "relationship.sameAs.type")
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed
    @PropertySource(template = "relationship/inheresIn", key = "relationship.inheresIn.label", id = "inheresInId")
    private List<String> inheresIn;

    @Indexed
    @PropertySource(template = "relationship/inheresIn", key = "relationship.inheresIn.type")
    private List<String> inheresInType;

    @Indexed
    private List<String> inheresInId;

    @Indexed
    @PropertySource(template = "relationship/isSpecifiedOutputOf", key = "relationship.isSpecifiedOutputOf.label", id = "isSpecifiedOutputOfId")
    private List<String> specifiedOutputOf;

    @Indexed
    @PropertySource(template = "relationship/isSpecifiedOutputOfType", key = "relationship.isSpecifiedOutputOf.type", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed
    private List<String> isSpecifiedOutputOfId;

    @Indexed
    @PropertySource(template = "relationship/outputOf", key = "relationship.outputOf.label", id = "outputOfId")
    private List<String> outputOf;

    @Indexed
    @PropertySource(template = "relationship/outputOfType", key = "relationship.outputOf.type", parse = true)
    private List<String> outputOfType;

    @Indexed
    private List<String> outputOfId;

    @Indexed
    @PropertySource(template = "relationship/participatesIn", key = "relationship.participatesIn.label", id = "participatesInId")
    private List<String> participatesIn;

    @Indexed
    @PropertySource(template = "relationship/participatesInType", key = "relationship.participatesIn.type", parse = true)
    private List<String> participatesInType;

    @Indexed
    private List<String> participatesInId;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/modTime", key = "relationship.modTime")
    private String modTime;

    public Relationship() {

    }

}

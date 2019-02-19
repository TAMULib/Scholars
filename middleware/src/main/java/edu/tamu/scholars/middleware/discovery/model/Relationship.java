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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getReceiptOf() {
        return receiptOf;
    }

    public void setReceiptOf(List<String> receiptOf) {
        this.receiptOf = receiptOf;
    }

    public List<String> getReceiptOfType() {
        return receiptOfType;
    }

    public void setReceiptOfType(List<String> receiptOfType) {
        this.receiptOfType = receiptOfType;
    }

    public List<String> getReceiptOfId() {
        return receiptOfId;
    }

    public void setReceiptOfId(List<String> receiptOfId) {
        this.receiptOfId = receiptOfId;
    }

    public List<String> getAwardOrHonorFor() {
        return awardOrHonorFor;
    }

    public void setAwardOrHonorFor(List<String> awardOrHonorFor) {
        this.awardOrHonorFor = awardOrHonorFor;
    }

    public List<String> getAwardOrHonorForType() {
        return awardOrHonorForType;
    }

    public void setAwardOrHonorForType(List<String> awardOrHonorForType) {
        this.awardOrHonorForType = awardOrHonorForType;
    }

    public List<String> getAwardOrHonorForId() {
        return awardOrHonorForId;
    }

    public void setAwardOrHonorForId(List<String> awardOrHonorForId) {
        this.awardOrHonorForId = awardOrHonorForId;
    }

    public List<String> getAwardConferredBy() {
        return awardConferredBy;
    }

    public void setAwardConferredBy(List<String> awardConferredBy) {
        this.awardConferredBy = awardConferredBy;
    }

    public List<String> getAwardConferredByType() {
        return awardConferredByType;
    }

    public void setAwardConferredByType(List<String> awardConferredByType) {
        this.awardConferredByType = awardConferredByType;
    }

    public List<String> getAwardConferredById() {
        return awardConferredById;
    }

    public void setAwardConferredById(List<String> awardConferredById) {
        this.awardConferredById = awardConferredById;
    }

    public List<String> getDateTimeIntervalStart() {
        return dateTimeIntervalStart;
    }

    public void setDateTimeIntervalStart(List<String> dateTimeIntervalStart) {
        this.dateTimeIntervalStart = dateTimeIntervalStart;
    }

    public List<String> getDateTimeIntervalEnd() {
        return dateTimeIntervalEnd;
    }

    public void setDateTimeIntervalEnd(List<String> dateTimeIntervalEnd) {
        this.dateTimeIntervalEnd = dateTimeIntervalEnd;
    }

    public List<String> getYearAwarded() {
        return yearAwarded;
    }

    public void setYearAwarded(List<String> yearAwarded) {
        this.yearAwarded = yearAwarded;
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

    public List<String> getInheresIn() {
        return inheresIn;
    }

    public void setInheresIn(List<String> inheresIn) {
        this.inheresIn = inheresIn;
    }

    public List<String> getInheresInType() {
        return inheresInType;
    }

    public void setInheresInType(List<String> inheresInType) {
        this.inheresInType = inheresInType;
    }

    public List<String> getInheresInId() {
        return inheresInId;
    }

    public void setInheresInId(List<String> inheresInId) {
        this.inheresInId = inheresInId;
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

    public List<String> getIsSpecifiedOutputOfId() {
        return isSpecifiedOutputOfId;
    }

    public void setIsSpecifiedOutputOfId(List<String> isSpecifiedOutputOfId) {
        this.isSpecifiedOutputOfId = isSpecifiedOutputOfId;
    }

    public List<String> getOutputOf() {
        return outputOf;
    }

    public void setOutputOf(List<String> outputOf) {
        this.outputOf = outputOf;
    }

    public List<String> getOutputOfType() {
        return outputOfType;
    }

    public void setOutputOfType(List<String> outputOfType) {
        this.outputOfType = outputOfType;
    }

    public List<String> getOutputOfId() {
        return outputOfId;
    }

    public void setOutputOfId(List<String> outputOfId) {
        this.outputOfId = outputOfId;
    }

    public List<String> getParticipatesIn() {
        return participatesIn;
    }

    public void setParticipatesIn(List<String> participatesIn) {
        this.participatesIn = participatesIn;
    }

    public List<String> getParticipatesInType() {
        return participatesInType;
    }

    public void setParticipatesInType(List<String> participatesInType) {
        this.participatesInType = participatesInType;
    }

    public List<String> getParticipatesInId() {
        return participatesInId;
    }

    public void setParticipatesInId(List<String> participatesInId) {
        this.participatesInId = participatesInId;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

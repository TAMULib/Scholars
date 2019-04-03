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
@SolrDocument(collection = "relationships")
@CollectionSource(key = "relationship.class")
public class Relationship extends AbstractSolrDocument {

    @Indexed(copyTo = "_text_")
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

    @Field("abstract")
    @Indexed(value = "abstract", copyTo = "_text_")
    @JsonProperty("abstract")
    @PropertySource(template = "relationship/abstract", key = "relationship.abstract")
    private String abstractText;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/description", key = "relationship.description")
    private String description;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/receiptOf", key = "relationship.receiptOf.name", id = "receiptOfId")
    private List<String> receiptOf;

    @Indexed
    @PropertySource(template = "relationship/receiptOfType", key = "relationship.receiptOf.type", parse = true)
    private List<String> receiptOfType;

    @Indexed
    private List<String> receiptOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardOrHonorFor", key = "relationship.awardOrHonorFor.name", id = "awardOrHonorForId")
    private List<String> awardOrHonorFor;

    @Indexed
    @PropertySource(template = "relationship/awardOrHonorForType", key = "relationship.awardOrHonorFor.type", parse = true)
    private List<String> awardOrHonorForType;

    @Indexed
    private List<String> awardOrHonorForId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardConferredBy", key = "relationship.awardConferredBy.name", id = "awardConferredById")
    private List<String> awardConferredBy;

    @Indexed
    @PropertySource(template = "relationship/awardConferredByType", key = "relationship.awardConferredBy.type", parse = true)
    private List<String> awardConferredByType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardConferredByPreferredLabel", key = "relationship.awardConferredBy.preferredLabel")
    private List<String> awardConferredByPreferredLabel;

    @Indexed
    private List<String> awardConferredById;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardedBy", key = "relationship.awardedBy.name", id = "awardedById")
    private List<String> awardedBy;

    @Indexed
    @PropertySource(template = "relationship/awardedByType", key = "relationship.awardedBy.type", parse = true)
    private List<String> awardedByType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardedByPreferredLabel", key = "relationship.awardedBy.preferredLabel")
    private List<String> awardedByPreferredLabel;

    @Indexed
    private List<String> awardedById;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/grantSubcontractedThrough", key = "relationship.grantSubcontractedThrough.name", id = "grantSubcontractedThroughId")
    private List<String> grantSubcontractedThrough;

    @Indexed
    @PropertySource(template = "relationship/grantSubcontractedThroughType", key = "relationship.grantSubcontractedThrough.type", parse = true)
    private List<String> grantSubcontractedThroughType;

    @Indexed
    private List<String> grantSubcontractedThroughId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/administeredBy", key = "relationship.administeredBy.name", id = "administeredById")
    private List<String> administeredBy;

    @Indexed
    @PropertySource(template = "relationship/administeredByType", key = "relationship.administeredBy.type", parse = true)
    private List<String> administeredByType;

    @Indexed
    private List<String> administeredById;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/geographicFocus", key = "relationship.geographicFocus.name", id = "geographicFocusId")
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "relationship/geographicFocusType", key = "relationship.geographicFocus.type", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/subGrant", key = "relationship.subGrant.name", id = "subGrantId")
    private List<String> subGrant;

    @Indexed
    @PropertySource(template = "relationship/subGrantType", key = "relationship.subGrant.type", parse = true)
    private List<String> subGrantType;

    @Indexed
    private List<String> subGrantId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/subGrantOf", key = "relationship.subGrantOf.name", id = "subGrantOfId")
    private List<String> subGrantOf;

    @Indexed
    @PropertySource(template = "relationship/subGrantOfType", key = "relationship.subGrantOf.type", parse = true)
    private List<String> subGrantOfType;

    @Indexed
    private List<String> subGrantOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/providesFundingFor", key = "relationship.providesFundingFor.name", id = "providesFundingForId")
    private List<String> providesFundingFor;

    @Indexed
    @PropertySource(template = "relationship/providesFundingForType", key = "relationship.providesFundingFor.type", parse = true)
    private List<String> providesFundingForType;

    @Indexed
    private List<String> providesFundingForId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/totalAwardAmount", key = "relationship.totalAwardAmount")
    private String totalAwardAmount;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/directCosts", key = "relationship.directCosts")
    private String directCosts;

    @Indexed
    @PropertySource(template = "relationship/sponsorAwardId", key = "relationship.sponsorAwardId")
    private String sponsorAwardId;

    @Indexed
    @PropertySource(template = "relationship/localAwardId", key = "relationship.localAwardId")
    private String localAwardId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/contributor", key = "relationship.contributor.name", id = "contributorId")
    private List<String> contributor;

    @Indexed
    @PropertySource(template = "relationship/contributorType", key = "relationship.contributor.type", parse = true)
    private List<String> contributorType;

    @Indexed
    private List<String> contributorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/supportedPublicationOrOtherWork", key = "relationship.supportedPublicationOrOtherWork.name", id = "supportedPublicationOrOtherWorkId")
    private List<String> supportedPublicationOrOtherWork;

    @Indexed
    @PropertySource(template = "relationship/supportedPublicationOrOtherWorkType", key = "relationship.supportedPublicationOrOtherWork.type", parse = true)
    private List<String> supportedPublicationOrOtherWorkType;

    @Indexed
    private List<String> supportedPublicationOrOtherWorkId;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalStart", key = "relationship.dateTimeInterval.start")
    private List<String> dateTimeIntervalStart;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalEnd", key = "relationship.dateTimeInterval.end")
    private List<String> dateTimeIntervalEnd;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/subjectArea", key = "relationship.subjectArea.name", id = "subjectAreaId")
    private List<String> subjectArea;

    @Indexed
    @PropertySource(template = "relationship/subjectAreaType", key = "relationship.subjectArea.type", parse = true)
    private List<String> subjectAreaType;

    @Indexed
    private List<String> subjectAreaId;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/yearAwarded", key = "relationship.yearAwarded")
    private List<String> yearAwarded;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/sameAs", key = "relationship.sameAs.name", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "relationship/sameAs", key = "relationship.sameAs.type", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/inheresIn", key = "relationship.inheresIn.name", id = "inheresInId")
    private List<String> inheresIn;

    @Indexed
    @PropertySource(template = "relationship/inheresIn", key = "relationship.inheresIn.type", parse = true)
    private List<String> inheresInType;

    @Indexed
    private List<String> inheresInId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/isSpecifiedOutputOf", key = "relationship.isSpecifiedOutputOf.name", id = "isSpecifiedOutputOfId")
    private List<String> specifiedOutputOf;

    @Indexed
    @PropertySource(template = "relationship/isSpecifiedOutputOfType", key = "relationship.isSpecifiedOutputOf.type", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed
    private List<String> isSpecifiedOutputOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/outputOf", key = "relationship.outputOf.name", id = "outputOfId")
    private List<String> outputOf;

    @Indexed
    @PropertySource(template = "relationship/outputOfType", key = "relationship.outputOf.type", parse = true)
    private List<String> outputOfType;

    @Indexed
    private List<String> outputOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/participatesIn", key = "relationship.participatesIn.name", id = "participatesInId")
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

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
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

    public List<String> getAwardConferredByPreferredLabel() {
        return awardConferredByPreferredLabel;
    }

    public void setAwardConferredByPreferredLabel(List<String> awardConferredByPreferredLabel) {
        this.awardConferredByPreferredLabel = awardConferredByPreferredLabel;
    }

    public List<String> getAwardConferredById() {
        return awardConferredById;
    }

    public void setAwardConferredById(List<String> awardConferredById) {
        this.awardConferredById = awardConferredById;
    }

    public List<String> getAwardedBy() {
        return awardedBy;
    }

    public void setAwardedBy(List<String> awardedBy) {
        this.awardedBy = awardedBy;
    }

    public List<String> getAwardedByType() {
        return awardedByType;
    }

    public void setAwardedByType(List<String> awardedByType) {
        this.awardedByType = awardedByType;
    }

    public List<String> getAwardedByPreferredLabel() {
        return awardedByPreferredLabel;
    }

    public void setAwardedByPreferredLabel(List<String> awardedByPreferredLabel) {
        this.awardedByPreferredLabel = awardedByPreferredLabel;
    }

    public List<String> getAwardedById() {
        return awardedById;
    }

    public void setAwardedById(List<String> awardedById) {
        this.awardedById = awardedById;
    }

    public List<String> getGrantSubcontractedThrough() {
        return grantSubcontractedThrough;
    }

    public void setGrantSubcontractedThrough(List<String> grantSubcontractedThrough) {
        this.grantSubcontractedThrough = grantSubcontractedThrough;
    }

    public List<String> getGrantSubcontractedThroughType() {
        return grantSubcontractedThroughType;
    }

    public void setGrantSubcontractedThroughType(List<String> grantSubcontractedThroughType) {
        this.grantSubcontractedThroughType = grantSubcontractedThroughType;
    }

    public List<String> getGrantSubcontractedThroughId() {
        return grantSubcontractedThroughId;
    }

    public void setGrantSubcontractedThroughId(List<String> grantSubcontractedThroughId) {
        this.grantSubcontractedThroughId = grantSubcontractedThroughId;
    }

    public List<String> getAdministeredBy() {
        return administeredBy;
    }

    public void setAdministeredBy(List<String> administeredBy) {
        this.administeredBy = administeredBy;
    }

    public List<String> getAdministeredByType() {
        return administeredByType;
    }

    public void setAdministeredByType(List<String> administeredByType) {
        this.administeredByType = administeredByType;
    }

    public List<String> getAdministeredById() {
        return administeredById;
    }

    public void setAdministeredById(List<String> administeredById) {
        this.administeredById = administeredById;
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

    public List<String> getSubGrant() {
        return subGrant;
    }

    public void setSubGrant(List<String> subGrant) {
        this.subGrant = subGrant;
    }

    public List<String> getSubGrantType() {
        return subGrantType;
    }

    public void setSubGrantType(List<String> subGrantType) {
        this.subGrantType = subGrantType;
    }

    public List<String> getSubGrantId() {
        return subGrantId;
    }

    public void setSubGrantId(List<String> subGrantId) {
        this.subGrantId = subGrantId;
    }

    public List<String> getSubGrantOf() {
        return subGrantOf;
    }

    public void setSubGrantOf(List<String> subGrantOf) {
        this.subGrantOf = subGrantOf;
    }

    public List<String> getSubGrantOfType() {
        return subGrantOfType;
    }

    public void setSubGrantOfType(List<String> subGrantOfType) {
        this.subGrantOfType = subGrantOfType;
    }

    public List<String> getSubGrantOfId() {
        return subGrantOfId;
    }

    public void setSubGrantOfId(List<String> subGrantOfId) {
        this.subGrantOfId = subGrantOfId;
    }

    public List<String> getProvidesFundingFor() {
        return providesFundingFor;
    }

    public void setProvidesFundingFor(List<String> providesFundingFor) {
        this.providesFundingFor = providesFundingFor;
    }

    public List<String> getProvidesFundingForType() {
        return providesFundingForType;
    }

    public void setProvidesFundingForType(List<String> providesFundingForType) {
        this.providesFundingForType = providesFundingForType;
    }

    public List<String> getProvidesFundingForId() {
        return providesFundingForId;
    }

    public void setProvidesFundingForId(List<String> providesFundingForId) {
        this.providesFundingForId = providesFundingForId;
    }

    public String getTotalAwardAmount() {
        return totalAwardAmount;
    }

    public void setTotalAwardAmount(String totalAwardAmount) {
        this.totalAwardAmount = totalAwardAmount;
    }

    public String getDirectCosts() {
        return directCosts;
    }

    public void setDirectCosts(String directCosts) {
        this.directCosts = directCosts;
    }

    public String getSponsorAwardId() {
        return sponsorAwardId;
    }

    public void setSponsorAwardId(String sponsorAwardId) {
        this.sponsorAwardId = sponsorAwardId;
    }

    public String getLocalAwardId() {
        return localAwardId;
    }

    public void setLocalAwardId(String localAwardId) {
        this.localAwardId = localAwardId;
    }

    public List<String> getContributor() {
        return contributor;
    }

    public void setContributor(List<String> contributor) {
        this.contributor = contributor;
    }

    public List<String> getContributorType() {
        return contributorType;
    }

    public void setContributorType(List<String> contributorType) {
        this.contributorType = contributorType;
    }

    public List<String> getContributorId() {
        return contributorId;
    }

    public void setContributorId(List<String> contributorId) {
        this.contributorId = contributorId;
    }

    public List<String> getSupportedPublicationOrOtherWork() {
        return supportedPublicationOrOtherWork;
    }

    public void setSupportedPublicationOrOtherWork(List<String> supportedPublicationOrOtherWork) {
        this.supportedPublicationOrOtherWork = supportedPublicationOrOtherWork;
    }

    public List<String> getSupportedPublicationOrOtherWorkType() {
        return supportedPublicationOrOtherWorkType;
    }

    public void setSupportedPublicationOrOtherWorkType(List<String> supportedPublicationOrOtherWorkType) {
        this.supportedPublicationOrOtherWorkType = supportedPublicationOrOtherWorkType;
    }

    public List<String> getSupportedPublicationOrOtherWorkId() {
        return supportedPublicationOrOtherWorkId;
    }

    public void setSupportedPublicationOrOtherWorkId(List<String> supportedPublicationOrOtherWorkId) {
        this.supportedPublicationOrOtherWorkId = supportedPublicationOrOtherWorkId;
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

    public List<String> getSubjectArea() {
        return subjectArea;
    }

    public void setSubjectArea(List<String> subjectArea) {
        this.subjectArea = subjectArea;
    }

    public List<String> getSubjectAreaType() {
        return subjectAreaType;
    }

    public void setSubjectAreaType(List<String> subjectAreaType) {
        this.subjectAreaType = subjectAreaType;
    }

    public List<String> getSubjectAreaId() {
        return subjectAreaId;
    }

    public void setSubjectAreaId(List<String> subjectAreaId) {
        this.subjectAreaId = subjectAreaId;
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

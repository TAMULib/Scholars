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
@CollectionSource(predicate = "http://vivoweb.org/ontology/core#Relationship")
public class Relationship extends AbstractSolrDocument {

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/title", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String title;

    @Indexed
    @PropertySource(template = "relationship/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "relationship/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed
    @PropertySource(template = "relationship/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Field("abstract")
    @Indexed(value = "abstract", copyTo = "_text_")
    @JsonProperty("abstract")
    @PropertySource(template = "relationship/abstract", predicate = "http://purl.org/ontology/bibo/abstract")
    private String abstractText;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/description", predicate = "http://vivoweb.org/ontology/core#description")
    private String description;

    @Indexed
    @PropertySource(template = "relationship/receiptOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> receiptOf;

    @Indexed
    @PropertySource(template = "relationship/receiptOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> receiptOfType;

    @Indexed
    private List<String> receiptOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardOrHonorFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> awardOrHonorFor;

    @Indexed
    @PropertySource(template = "relationship/awardOrHonorForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> awardOrHonorForType;

    @Indexed
    private List<String> awardOrHonorForId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardConferredBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> awardConferredBy;

    @Indexed
    @PropertySource(template = "relationship/awardConferredByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> awardConferredByType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardConferredByPreferredLabel", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#awardConferredBy_label")
    private List<String> awardConferredByPreferredLabel;

    @Indexed
    private List<String> awardConferredById;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> awardedBy;

    @Indexed
    @PropertySource(template = "relationship/awardedByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> awardedByType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/awardedByPreferredLabel", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#awardedBy_label")
    private List<String> awardedByPreferredLabel;

    @Indexed
    private List<String> awardedById;

    @Indexed
    @PropertySource(template = "relationship/grantSubcontractedThrough", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> grantSubcontractedThrough;

    @Indexed
    @PropertySource(template = "relationship/grantSubcontractedThroughType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> grantSubcontractedThroughType;

    @Indexed
    private List<String> grantSubcontractedThroughId;

    @Indexed
    @PropertySource(template = "relationship/administeredBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> administeredBy;

    @Indexed
    @PropertySource(template = "relationship/administeredByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> administeredByType;

    @Indexed
    private List<String> administeredById;

    @Indexed
    @PropertySource(template = "relationship/geographicFocus", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "relationship/geographicFocusType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed
    @PropertySource(template = "relationship/subGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> subGrant;

    @Indexed
    @PropertySource(template = "relationship/subGrantType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> subGrantType;

    @Indexed
    private List<String> subGrantId;

    @Indexed
    @PropertySource(template = "relationship/subGrantOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> subGrantOf;

    @Indexed
    @PropertySource(template = "relationship/subGrantOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> subGrantOfType;

    @Indexed
    private List<String> subGrantOfId;

    @Indexed
    @PropertySource(template = "relationship/providesFundingFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> providesFundingFor;

    @Indexed
    @PropertySource(template = "relationship/providesFundingForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> providesFundingForType;

    @Indexed
    private List<String> providesFundingForId;

    @Indexed
    @PropertySource(template = "relationship/totalAwardAmount", predicate = "http://vivoweb.org/ontology/core#totalAwardAmount")
    private String totalAwardAmount;

    @Indexed
    @PropertySource(template = "relationship/directCosts", predicate = "http://vivoweb.org/ontology/core#directCosts")
    private String directCosts;

    @Indexed
    @PropertySource(template = "relationship/sponsorAwardId", predicate = "http://vivoweb.org/ontology/core#sponsorAwardId")
    private String sponsorAwardId;

    @Indexed
    @PropertySource(template = "relationship/localAwardId", predicate = "http://vivoweb.org/ontology/core#localAwardId")
    private String localAwardId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/contributor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> contributor;

    @Indexed
    @PropertySource(template = "relationship/contributorType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> contributorType;

    @Indexed
    private List<String> contributorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/principalInvestigator", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> principalInvestigator;

    @Indexed
    private List<String> principalInvestigatorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/coPrincipalInvestigator", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> coPrincipalInvestigator;

    @Indexed
    private List<String> coPrincipalInvestigatorId;

    @Indexed
    @PropertySource(template = "relationship/supportedPublicationOrOtherWork", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> supportedPublicationOrOtherWork;

    @Indexed
    @PropertySource(template = "relationship/supportedPublicationOrOtherWorkType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> supportedPublicationOrOtherWorkType;

    @Indexed
    private List<String> supportedPublicationOrOtherWorkId;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalStart", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalStart;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalEnd", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalEnd;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "relationship/subjectArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> subjectArea;

    @Indexed
    @PropertySource(template = "relationship/subjectAreaType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> subjectAreaType;

    @Indexed
    private List<String> subjectAreaId;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/yearAwarded", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> yearAwarded;

    @Indexed
    @PropertySource(template = "relationship/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "relationship/sameAs", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed
    @PropertySource(template = "relationship/inheresIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> inheresIn;

    @Indexed
    @PropertySource(template = "relationship/inheresIn", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> inheresInType;

    @Indexed
    private List<String> inheresInId;

    @Indexed
    @PropertySource(template = "relationship/isSpecifiedOutputOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> specifiedOutputOf;

    @Indexed
    @PropertySource(template = "relationship/isSpecifiedOutputOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed
    private List<String> isSpecifiedOutputOfId;

    @Indexed
    @PropertySource(template = "relationship/outputOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> outputOf;

    @Indexed
    @PropertySource(template = "relationship/outputOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> outputOfType;

    @Indexed
    private List<String> outputOfId;

    @Indexed
    @PropertySource(template = "relationship/participatesIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> participatesIn;

    @Indexed
    @PropertySource(template = "relationship/participatesInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> participatesInType;

    @Indexed
    private List<String> participatesInId;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/modTime", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#modTime")
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

    public List<String> getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(List<String> principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public List<String> getPrincipalInvestigatorId() {
        return principalInvestigatorId;
    }

    public void setPrincipalInvestigatorId(List<String> principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
    }

    public List<String> getCoPrincipalInvestigator() {
        return coPrincipalInvestigator;
    }

    public void setCoPrincipalInvestigator(List<String> coPrincipalInvestigator) {
        this.coPrincipalInvestigator = coPrincipalInvestigator;
    }

    public List<String> getCoPrincipalInvestigatorId() {
        return coPrincipalInvestigatorId;
    }

    public void setCoPrincipalInvestigatorId(List<String> coPrincipalInvestigatorId) {
        this.coPrincipalInvestigatorId = coPrincipalInvestigatorId;
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

package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject.Reference;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "relationships")
@CollectionSource(predicate = "http://vivoweb.org/ontology/core#Relationship")
public class Relationship extends AbstractSolrDocument {

    @Indexed(type = "sorting_string", copyTo = "_text_")
    @PropertySource(template = "relationship/title", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String title;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "relationship/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed(type = "whole_string")
    @PropertySource(template = "relationship/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed(type = "whole_string")
    @PropertySource(template = "relationship/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Field("abstract")
    @Indexed(type = "whole_string", value = "abstract", copyTo = "_text_")
    @JsonProperty("abstract")
    @PropertySource(template = "relationship/abstract", predicate = "http://purl.org/ontology/bibo/abstract")
    private String abstractText;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "relationship/description", predicate = "http://vivoweb.org/ontology/core#description")
    private String description;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "receiptOfType", key = "type") })
    @PropertySource(template = "relationship/receiptOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> receiptOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/receiptOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> receiptOfType;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "awardOrHonorForType", key = "type") })
    @PropertySource(template = "relationship/awardOrHonorFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> awardOrHonorFor;

    @Indexed
    @PropertySource(template = "relationship/awardOrHonorForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> awardOrHonorForType;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "awardConferredByType", key = "type") })
    @PropertySource(template = "relationship/awardConferredBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> awardConferredBy;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/awardConferredByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> awardConferredByType;

    @Indexed(type = "whole_strings", copyTo = "_text_")
    @PropertySource(template = "relationship/awardConferredByPreferredLabel", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#awardConferredBy_label")
    private List<String> awardConferredByPreferredLabel;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "awardedByType", key = "type") })
    @PropertySource(template = "relationship/awardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> awardedBy;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/awardedByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> awardedByType;

    @Indexed(type = "whole_strings", copyTo = "_text_")
    @PropertySource(template = "relationship/awardedByPreferredLabel", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#awardedBy_label")
    private List<String> awardedByPreferredLabel;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "grantSubcontractedThroughType", key = "type") })
    @PropertySource(template = "relationship/grantSubcontractedThrough", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> grantSubcontractedThrough;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/grantSubcontractedThroughType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> grantSubcontractedThroughType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "administeredByType", key = "type") })
    @PropertySource(template = "relationship/administeredBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> administeredBy;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/administeredByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> administeredByType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "geographicFocusType", key = "type") })
    @PropertySource(template = "relationship/geographicFocus", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> geographicFocus;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/geographicFocusType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> geographicFocusType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "subGrantType", key = "type") })
    @PropertySource(template = "relationship/subGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> subGrant;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/subGrantType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> subGrantType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "subGrantOfType", key = "type") })
    @PropertySource(template = "relationship/subGrantOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> subGrantOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/subGrantOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> subGrantOfType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "providesFundingForType", key = "type") })
    @PropertySource(template = "relationship/providesFundingFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> providesFundingFor;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/providesFundingForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> providesFundingForType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "relationship/totalAwardAmount", predicate = "http://vivoweb.org/ontology/core#totalAwardAmount")
    private String totalAwardAmount;

    @Indexed(type = "whole_string")
    @PropertySource(template = "relationship/directCosts", predicate = "http://vivoweb.org/ontology/core#directCosts")
    private String directCosts;

    @Indexed(type = "whole_string")
    @PropertySource(template = "relationship/sponsorAwardId", predicate = "http://vivoweb.org/ontology/core#sponsorAwardId")
    private String sponsorAwardId;

    @Indexed(type = "whole_string")
    @PropertySource(template = "relationship/localAwardId", predicate = "http://vivoweb.org/ontology/core#localAwardId")
    private String localAwardId;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "contributorType", key = "type") })
    @PropertySource(template = "relationship/contributor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> contributor;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/contributorType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> contributorType;

    @NestedObject
    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "relationship/principalInvestigator", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> principalInvestigator;

    @NestedObject
    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "relationship/coPrincipalInvestigator", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> coPrincipalInvestigator;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "supportedPublicationOrOtherWorkType", key = "type") })
    @PropertySource(template = "relationship/supportedPublicationOrOtherWork", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> supportedPublicationOrOtherWork;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/supportedPublicationOrOtherWorkType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> supportedPublicationOrOtherWorkType;

    @Indexed(type = "pdates")
    @PropertySource(template = "relationship/dateTimeIntervalStart", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalStart;

    @Indexed(type = "pdates")
    @PropertySource(template = "relationship/dateTimeIntervalEnd", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalEnd;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "subjectAreaType", key = "type") })
    @PropertySource(template = "relationship/subjectArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> subjectArea;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/subjectAreaType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> subjectAreaType;

    @Indexed(type = "pdates")
    @PropertySource(template = "relationship/yearAwarded", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> yearAwarded;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> sameAs;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "inheresInType", key = "type") })
    @PropertySource(template = "relationship/inheresIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> inheresIn;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/inheresInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> inheresInType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "specifiedOutputOfType", key = "type") })
    @PropertySource(template = "relationship/isSpecifiedOutputOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> specifiedOutputOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/isSpecifiedOutputOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> specifiedOutputOfType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "outputOfType", key = "type") })
    @PropertySource(template = "relationship/outputOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> outputOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/outputOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> outputOfType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "participatesInType", key = "type") })
    @PropertySource(template = "relationship/participatesIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> participatesIn;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "relationship/participatesInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> participatesInType;

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

    public List<String> getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(List<String> principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public List<String> getCoPrincipalInvestigator() {
        return coPrincipalInvestigator;
    }

    public void setCoPrincipalInvestigator(List<String> coPrincipalInvestigator) {
        this.coPrincipalInvestigator = coPrincipalInvestigator;
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

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

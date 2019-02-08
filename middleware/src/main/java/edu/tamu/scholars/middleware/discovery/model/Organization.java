package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;
import edu.tamu.scholars.middleware.harvest.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "organizations")
@CollectionSource(key = "organization.class")
public class Organization extends AbstractSolrDocument {

    @Indexed
    @PropertySource(template = "organization/name", key = "organization.name")
    private String name;

    @Indexed
    @PropertySource(template = "organization/type", key = "organization.type", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "organization/image", key = "organization.image")
    private String image;

    @Indexed
    @PropertySource(template = "organization/websiteUrl", key = "organization.website.url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "organization/websiteLabel", key = "organization.website.label")
    private List<String> websiteLabel;

    @Indexed
    @PropertySource(template = "organization/overview", key = "organization.overview")
    private String overview;

    @Indexed
    @PropertySource(template = "organization/offersDegree", key = "organization.offersDegree", id = "offersDegreeId")
    private List<String> offersDegree;

    @Indexed
    private List<String> offersDegreeId;

    @Indexed
    @PropertySource(template = "organization/abbreviation", key = "organization.abbreviation")
    private String abbreviation;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/date", key = "organization.date")
    private List<String> date;

    @Indexed
    private List<String> sponsorsAwardOrHonor;

    @Indexed
    private List<String> sponsorsAwardOrHonorId;

    @Indexed
    private List<String> awardOrHonorGiven;

    @Indexed
    private List<String> awardOrHonorGivenId;

    @Indexed
    private List<String> awardOrHonorReceived;

    @Indexed
    private List<String> awardOrHonorReceivedId;

    @Indexed
    private List<String> keyword;

    @Indexed
    private List<String> organizationForTraining;

    @Indexed
    private List<String> organizationForTrainingId;

    @Indexed
    @PropertySource(template = "organization/people", key = "organization.people.name", id = "peopleId")
    private List<String> people;

    @Indexed
    @PropertySource(template = "organization/peopleType", key = "organization.people.type", parse = true)
    private List<String> peopleType;

    @Indexed
    @PropertySource(template = "organization/peopleTitle", key = "organization.people.title")
    private List<String> peopleTitle;

    @Indexed
    private List<String> peopleId;

    @Indexed
    private List<String> hasSubOrganization;

    @Indexed
    private List<String> hasSubOrganizationId;

    @Indexed
    private List<String> organizationWithin;

    @Indexed
    private List<String> organizationWithinId;

    @Indexed
    private List<String> leadOrganizationOf;

    @Indexed
    private List<String> leadOrganizationOfId;

    @Indexed
    private List<String> hasCollaboratingOrganizatgionOrGroup;

    @Indexed
    private List<String> hasCollaboratingOrganizatgionOrGroupId;

    @Indexed
    private List<String> hasAffiliatedOrganization;

    @Indexed
    private List<String> hasAffiliatedOrganizationId;

    @Indexed
    private List<String> memberOf;

    @Indexed
    private List<String> memberOfId;

    @Indexed
    private List<String> clinicalActivity;

    @Indexed
    private List<String> clinicalActivityId;

    @Indexed
    private List<String> convenerOfEvent;

    @Indexed
    private List<String> convenerOfEventId;

    @Indexed
    private List<String> attendedEvent;

    @Indexed
    private List<String> attendedEventId;

    @Indexed
    private List<String> selectedPublication;

    @Indexed
    private List<String> selectedPublicationId;

    @Indexed
    private List<String> publisherOf;

    @Indexed
    private List<String> publisherOfId;

    @Indexed
    private List<String> presentation;

    @Indexed
    private List<String> presentationId;

    @Indexed
    private List<String> featuredIn;

    @Indexed
    private List<String> featuredInId;

    @Indexed
    private List<String> assigneeForPatent;

    @Indexed
    private List<String> assigneeForPatentId;

    @Indexed
    private List<String> translatorOf;

    @Indexed
    private List<String> translatorOfId;

    @Indexed
    private List<String> awardsGrant;

    @Indexed
    private List<String> awardsGrantId;

    @Indexed
    private List<String> administersGrant;

    @Indexed
    private List<String> administersGrantId;

    @Indexed
    private List<String> subcontractsGrant;

    @Indexed
    private List<String> subcontractsGrantId;

    @Indexed
    private List<String> performsHumanStudy;

    @Indexed
    private List<String> performsHumanStudyId;

    @Indexed
    private List<String> contractOrProviderForService;

    @Indexed
    private List<String> contractOrProviderForServiceId;

    @Indexed
    private List<String> outreachAndCommunityServiceActivity;

    @Indexed
    private List<String> outreachAndCommunityServiceActivityId;

    @Indexed
    private List<String> hasEquipment;

    @Indexed
    private List<String> hasEquipmentId;

    @Indexed
    private List<String> offersCourse;

    @Indexed
    private List<String> offersCourseId;

    @Indexed
    private String orgId;

    @Indexed
    private List<String> sameAs;

    @Indexed
    private String phone;

    @Indexed
    private String fax;

    @Indexed
    private String emailAddress;

    @Indexed
    private String streetAddress;

    @Indexed
    private String locality;

    @Indexed
    private String region;

    @Indexed
    private String postalCode;

    @Indexed
    private String country;

    // NOTE: websiteUrl, fax, emailAddress, StreetAddress, locality, region, postalCode, and country belong to this vcard
    @Indexed
    private String vcardId;

    @Indexed(type = "location")
    @PropertySource(template = "organization/geographicLocation", key = "organization.geographicLocation", id = "geographicLocationId")
    private String geographicLocation;

    @Indexed
    private String geographicLocationId;

    @Indexed
    private List<String> locatedAtFacility;

    @Indexed
    private List<String> locatedAtFacilityId;

    @Indexed
    @PropertySource(template = "organization/predecessorOrganization", key = "organization.predecessorOrganization", id = "predecessorOrganizationId")
    private List<String> predecessorOrganization;

    @Indexed
    private List<String> predecessorOrganizationId;

    @Indexed
    @PropertySource(template = "organization/successorOrganization", key = "organization.successorOrganization", id = "successorOrganizationId")
    private List<String> successorOrganization;

    @Indexed
    private List<String> successorOrganizationId;

    @Indexed
    private List<String> governingAuthorityFor;

    @Indexed
    private List<String> governingAuthorityForId;

    // NOTE: unidirectional from Concept vivo:researchAreaOf
    @Indexed
    @PropertySource(template = "organization/hasResearchArea", key = "organization.hasResearchArea", id = "hasResearchAreaId")
    private List<String> hasResearchArea;

    @Indexed
    private List<String> hasResearchAreaId;

    public Organization() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getOffersDegree() {
        return offersDegree;
    }

    public void setOffersDegree(List<String> offersDegree) {
        this.offersDegree = offersDegree;
    }

    public List<String> getOffersDegreeId() {
        return offersDegreeId;
    }

    public void setOffersDegreeId(List<String> offersDegreeId) {
        this.offersDegreeId = offersDegreeId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getSponsorsAwardOrHonor() {
        return sponsorsAwardOrHonor;
    }

    public void setSponsorsAwardOrHonor(List<String> sponsorsAwardOrHonor) {
        this.sponsorsAwardOrHonor = sponsorsAwardOrHonor;
    }

    public List<String> getSponsorsAwardOrHonorId() {
        return sponsorsAwardOrHonorId;
    }

    public void setSponsorsAwardOrHonorId(List<String> sponsorsAwardOrHonorId) {
        this.sponsorsAwardOrHonorId = sponsorsAwardOrHonorId;
    }

    public List<String> getAwardOrHonorGiven() {
        return awardOrHonorGiven;
    }

    public void setAwardOrHonorGiven(List<String> awardOrHonorGiven) {
        this.awardOrHonorGiven = awardOrHonorGiven;
    }

    public List<String> getAwardOrHonorGivenId() {
        return awardOrHonorGivenId;
    }

    public void setAwardOrHonorGivenId(List<String> awardOrHonorGivenId) {
        this.awardOrHonorGivenId = awardOrHonorGivenId;
    }

    public List<String> getAwardOrHonorReceived() {
        return awardOrHonorReceived;
    }

    public void setAwardOrHonorReceived(List<String> awardOrHonorReceived) {
        this.awardOrHonorReceived = awardOrHonorReceived;
    }

    public List<String> getAwardOrHonorReceivedId() {
        return awardOrHonorReceivedId;
    }

    public void setAwardOrHonorReceivedId(List<String> awardOrHonorReceivedId) {
        this.awardOrHonorReceivedId = awardOrHonorReceivedId;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public List<String> getOrganizationForTraining() {
        return organizationForTraining;
    }

    public void setOrganizationForTraining(List<String> organizationForTraining) {
        this.organizationForTraining = organizationForTraining;
    }

    public List<String> getOrganizationForTrainingId() {
        return organizationForTrainingId;
    }

    public void setOrganizationForTrainingId(List<String> organizationForTrainingId) {
        this.organizationForTrainingId = organizationForTrainingId;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public List<String> getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(List<String> peopleType) {
        this.peopleType = peopleType;
    }

    public List<String> getPeopleTitle() {
        return peopleTitle;
    }

    public void setPeopleTitle(List<String> peopleTitle) {
        this.peopleTitle = peopleTitle;
    }

    public List<String> getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(List<String> peopleId) {
        this.peopleId = peopleId;
    }

    public List<String> getHasSubOrganization() {
        return hasSubOrganization;
    }

    public void setHasSubOrganization(List<String> hasSubOrganization) {
        this.hasSubOrganization = hasSubOrganization;
    }

    public List<String> getHasSubOrganizationId() {
        return hasSubOrganizationId;
    }

    public void setHasSubOrganizationId(List<String> hasSubOrganizationId) {
        this.hasSubOrganizationId = hasSubOrganizationId;
    }

    public List<String> getOrganizationWithin() {
        return organizationWithin;
    }

    public void setOrganizationWithin(List<String> organizationWithin) {
        this.organizationWithin = organizationWithin;
    }

    public List<String> getOrganizationWithinId() {
        return organizationWithinId;
    }

    public void setOrganizationWithinId(List<String> organizationWithinId) {
        this.organizationWithinId = organizationWithinId;
    }

    public List<String> getLeadOrganizationOf() {
        return leadOrganizationOf;
    }

    public void setLeadOrganizationOf(List<String> leadOrganizationOf) {
        this.leadOrganizationOf = leadOrganizationOf;
    }

    public List<String> getLeadOrganizationOfId() {
        return leadOrganizationOfId;
    }

    public void setLeadOrganizationOfId(List<String> leadOrganizationOfId) {
        this.leadOrganizationOfId = leadOrganizationOfId;
    }

    public List<String> getHasCollaboratingOrganizatgionOrGroup() {
        return hasCollaboratingOrganizatgionOrGroup;
    }

    public void setHasCollaboratingOrganizatgionOrGroup(List<String> hasCollaboratingOrganizatgionOrGroup) {
        this.hasCollaboratingOrganizatgionOrGroup = hasCollaboratingOrganizatgionOrGroup;
    }

    public List<String> getHasCollaboratingOrganizatgionOrGroupId() {
        return hasCollaboratingOrganizatgionOrGroupId;
    }

    public void setHasCollaboratingOrganizatgionOrGroupId(List<String> hasCollaboratingOrganizatgionOrGroupId) {
        this.hasCollaboratingOrganizatgionOrGroupId = hasCollaboratingOrganizatgionOrGroupId;
    }

    public List<String> getHasAffiliatedOrganization() {
        return hasAffiliatedOrganization;
    }

    public void setHasAffiliatedOrganization(List<String> hasAffiliatedOrganization) {
        this.hasAffiliatedOrganization = hasAffiliatedOrganization;
    }

    public List<String> getHasAffiliatedOrganizationId() {
        return hasAffiliatedOrganizationId;
    }

    public void setHasAffiliatedOrganizationId(List<String> hasAffiliatedOrganizationId) {
        this.hasAffiliatedOrganizationId = hasAffiliatedOrganizationId;
    }

    public List<String> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<String> memberOf) {
        this.memberOf = memberOf;
    }

    public List<String> getMemberOfId() {
        return memberOfId;
    }

    public void setMemberOfId(List<String> memberOfId) {
        this.memberOfId = memberOfId;
    }

    public List<String> getClinicalActivity() {
        return clinicalActivity;
    }

    public void setClinicalActivity(List<String> clinicalActivity) {
        this.clinicalActivity = clinicalActivity;
    }

    public List<String> getClinicalActivityId() {
        return clinicalActivityId;
    }

    public void setClinicalActivityId(List<String> clinicalActivityId) {
        this.clinicalActivityId = clinicalActivityId;
    }

    public List<String> getConvenerOfEvent() {
        return convenerOfEvent;
    }

    public void setConvenerOfEvent(List<String> convenerOfEvent) {
        this.convenerOfEvent = convenerOfEvent;
    }

    public List<String> getConvenerOfEventId() {
        return convenerOfEventId;
    }

    public void setConvenerOfEventId(List<String> convenerOfEventId) {
        this.convenerOfEventId = convenerOfEventId;
    }

    public List<String> getAttendedEvent() {
        return attendedEvent;
    }

    public void setAttendedEvent(List<String> attendedEvent) {
        this.attendedEvent = attendedEvent;
    }

    public List<String> getAttendedEventId() {
        return attendedEventId;
    }

    public void setAttendedEventId(List<String> attendedEventId) {
        this.attendedEventId = attendedEventId;
    }

    public List<String> getSelectedPublication() {
        return selectedPublication;
    }

    public void setSelectedPublication(List<String> selectedPublication) {
        this.selectedPublication = selectedPublication;
    }

    public List<String> getSelectedPublicationId() {
        return selectedPublicationId;
    }

    public void setSelectedPublicationId(List<String> selectedPublicationId) {
        this.selectedPublicationId = selectedPublicationId;
    }

    public List<String> getPublisherOf() {
        return publisherOf;
    }

    public void setPublisherOf(List<String> publisherOf) {
        this.publisherOf = publisherOf;
    }

    public List<String> getPublisherOfId() {
        return publisherOfId;
    }

    public void setPublisherOfId(List<String> publisherOfId) {
        this.publisherOfId = publisherOfId;
    }

    public List<String> getPresentation() {
        return presentation;
    }

    public void setPresentation(List<String> presentation) {
        this.presentation = presentation;
    }

    public List<String> getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(List<String> presentationId) {
        this.presentationId = presentationId;
    }

    public List<String> getFeaturedIn() {
        return featuredIn;
    }

    public void setFeaturedIn(List<String> featuredIn) {
        this.featuredIn = featuredIn;
    }

    public List<String> getFeaturedInId() {
        return featuredInId;
    }

    public void setFeaturedInId(List<String> featuredInId) {
        this.featuredInId = featuredInId;
    }

    public List<String> getAssigneeForPatent() {
        return assigneeForPatent;
    }

    public void setAssigneeForPatent(List<String> assigneeForPatent) {
        this.assigneeForPatent = assigneeForPatent;
    }

    public List<String> getAssigneeForPatentId() {
        return assigneeForPatentId;
    }

    public void setAssigneeForPatentId(List<String> assigneeForPatentId) {
        this.assigneeForPatentId = assigneeForPatentId;
    }

    public List<String> getTranslatorOf() {
        return translatorOf;
    }

    public void setTranslatorOf(List<String> translatorOf) {
        this.translatorOf = translatorOf;
    }

    public List<String> getTranslatorOfId() {
        return translatorOfId;
    }

    public void setTranslatorOfId(List<String> translatorOfId) {
        this.translatorOfId = translatorOfId;
    }

    public List<String> getAwardsGrant() {
        return awardsGrant;
    }

    public void setAwardsGrant(List<String> awardsGrant) {
        this.awardsGrant = awardsGrant;
    }

    public List<String> getAwardsGrantId() {
        return awardsGrantId;
    }

    public void setAwardsGrantId(List<String> awardsGrantId) {
        this.awardsGrantId = awardsGrantId;
    }

    public List<String> getAdministersGrant() {
        return administersGrant;
    }

    public void setAdministersGrant(List<String> administersGrant) {
        this.administersGrant = administersGrant;
    }

    public List<String> getAdministersGrantId() {
        return administersGrantId;
    }

    public void setAdministersGrantId(List<String> administersGrantId) {
        this.administersGrantId = administersGrantId;
    }

    public List<String> getSubcontractsGrant() {
        return subcontractsGrant;
    }

    public void setSubcontractsGrant(List<String> subcontractsGrant) {
        this.subcontractsGrant = subcontractsGrant;
    }

    public List<String> getSubcontractsGrantId() {
        return subcontractsGrantId;
    }

    public void setSubcontractsGrantId(List<String> subcontractsGrantId) {
        this.subcontractsGrantId = subcontractsGrantId;
    }

    public List<String> getPerformsHumanStudy() {
        return performsHumanStudy;
    }

    public void setPerformsHumanStudy(List<String> performsHumanStudy) {
        this.performsHumanStudy = performsHumanStudy;
    }

    public List<String> getPerformsHumanStudyId() {
        return performsHumanStudyId;
    }

    public void setPerformsHumanStudyId(List<String> performsHumanStudyId) {
        this.performsHumanStudyId = performsHumanStudyId;
    }

    public List<String> getContractOrProviderForService() {
        return contractOrProviderForService;
    }

    public void setContractOrProviderForService(List<String> contractOrProviderForService) {
        this.contractOrProviderForService = contractOrProviderForService;
    }

    public List<String> getContractOrProviderForServiceId() {
        return contractOrProviderForServiceId;
    }

    public void setContractOrProviderForServiceId(List<String> contractOrProviderForServiceId) {
        this.contractOrProviderForServiceId = contractOrProviderForServiceId;
    }

    public List<String> getOutreachAndCommunityServiceActivity() {
        return outreachAndCommunityServiceActivity;
    }

    public void setOutreachAndCommunityServiceActivity(List<String> outreachAndCommunityServiceActivity) {
        this.outreachAndCommunityServiceActivity = outreachAndCommunityServiceActivity;
    }

    public List<String> getOutreachAndCommunityServiceActivityId() {
        return outreachAndCommunityServiceActivityId;
    }

    public void setOutreachAndCommunityServiceActivityId(List<String> outreachAndCommunityServiceActivityId) {
        this.outreachAndCommunityServiceActivityId = outreachAndCommunityServiceActivityId;
    }

    public List<String> getHasEquipment() {
        return hasEquipment;
    }

    public void setHasEquipment(List<String> hasEquipment) {
        this.hasEquipment = hasEquipment;
    }

    public List<String> getHasEquipmentId() {
        return hasEquipmentId;
    }

    public void setHasEquipmentId(List<String> hasEquipmentId) {
        this.hasEquipmentId = hasEquipmentId;
    }

    public List<String> getOffersCourse() {
        return offersCourse;
    }

    public void setOffersCourse(List<String> offersCourse) {
        this.offersCourse = offersCourse;
    }

    public List<String> getOffersCourseId() {
        return offersCourseId;
    }

    public void setOffersCourseId(List<String> offersCourseId) {
        this.offersCourseId = offersCourseId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<String> getSameAs() {
        return sameAs;
    }

    public void setSameAs(List<String> sameAs) {
        this.sameAs = sameAs;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVcardId() {
        return vcardId;
    }

    public void setVcardId(String vcardId) {
        this.vcardId = vcardId;
    }

    public String getGeographicLocation() {
        return geographicLocation;
    }

    public void setGeographicLocation(String geographicLocation) {
        this.geographicLocation = geographicLocation;
    }

    public String getGeographicLocationId() {
        return geographicLocationId;
    }

    public void setGeographicLocationId(String geographicLocationId) {
        this.geographicLocationId = geographicLocationId;
    }

    public List<String> getLocatedAtFacility() {
        return locatedAtFacility;
    }

    public void setLocatedAtFacility(List<String> locatedAtFacility) {
        this.locatedAtFacility = locatedAtFacility;
    }

    public List<String> getLocatedAtFacilityId() {
        return locatedAtFacilityId;
    }

    public void setLocatedAtFacilityId(List<String> locatedAtFacilityId) {
        this.locatedAtFacilityId = locatedAtFacilityId;
    }

    public List<String> getPredecessorOrganization() {
        return predecessorOrganization;
    }

    public void setPredecessorOrganization(List<String> predecessorOrganization) {
        this.predecessorOrganization = predecessorOrganization;
    }

    public List<String> getPredecessorOrganizationId() {
        return predecessorOrganizationId;
    }

    public void setPredecessorOrganizationId(List<String> predecessorOrganizationId) {
        this.predecessorOrganizationId = predecessorOrganizationId;
    }

    public List<String> getSuccessorOrganization() {
        return successorOrganization;
    }

    public void setSuccessorOrganization(List<String> successorOrganization) {
        this.successorOrganization = successorOrganization;
    }

    public List<String> getSuccessorOrganizationId() {
        return successorOrganizationId;
    }

    public void setSuccessorOrganizationId(List<String> successorOrganizationId) {
        this.successorOrganizationId = successorOrganizationId;
    }

    public List<String> getGoverningAuthorityFor() {
        return governingAuthorityFor;
    }

    public void setGoverningAuthorityFor(List<String> governingAuthorityFor) {
        this.governingAuthorityFor = governingAuthorityFor;
    }

    public List<String> getGoverningAuthorityForId() {
        return governingAuthorityForId;
    }

    public void setGoverningAuthorityForId(List<String> governingAuthorityForId) {
        this.governingAuthorityForId = governingAuthorityForId;
    }

    public List<String> getHasResearchArea() {
        return hasResearchArea;
    }

    public void setHasResearchArea(List<String> hasResearchArea) {
        this.hasResearchArea = hasResearchArea;
    }

    public List<String> getHasResearchAreaId() {
        return hasResearchAreaId;
    }

    public void setHasResearchAreaId(List<String> hasResearchAreaId) {
        this.hasResearchAreaId = hasResearchAreaId;
    }

}

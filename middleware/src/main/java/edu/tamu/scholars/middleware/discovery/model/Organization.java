package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "organizations")
@CollectionSource(predicate = "http://xmlns.com/foaf/0.1/Organization")
public class Organization extends AbstractSolrDocument {

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/name", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String name;

    @Indexed
    @PropertySource(template = "organization/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "organization/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed
    @PropertySource(template = "organization/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Indexed
    @PropertySource(template = "organization/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "organization/websiteLabel", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> websiteLabel;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/overview", predicate = "http://vivoweb.org/ontology/core#overview")
    private String overview;

    @Indexed
    @PropertySource(template = "organization/offersDegree", predicate = "organization.offersDegree", nested = true)
    private List<String> offersDegree;

    @Indexed
    private List<String> offersDegreeId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/abbreviation", predicate = "http://vivoweb.org/ontology/core#abbreviation")
    private String abbreviation;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/date", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> date;

    @Indexed
    @PropertySource(template = "organization/sponsorsAwardOrHonor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> sponsorsAwardOrHonor;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/sponsorsAwardOrHonorDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> sponsorsAwardOrHonorDate;

    @Indexed
    private List<String> sponsorsAwardOrHonorId;

    @Indexed
    @PropertySource(template = "organization/awardOrHonorGiven", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> awardOrHonorGiven;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/awardOrHonorGivenDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardOrHonorGivenDate;

    @Indexed
    private List<String> awardOrHonorGivenId;

    @Indexed
    @PropertySource(template = "organization/awardOrHonorReceived", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> awardOrHonorReceived;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/awardOrHonorReceivedDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardOrHonorReceivedDate;

    @Indexed
    private List<String> awardOrHonorReceivedId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/keyword", predicate = "http://vivoweb.org/ontology/core#freetextKeyword")
    private List<String> keyword;

    @Indexed
    @PropertySource(template = "organization/organizationForTraining", predicate = "http://vivoweb.org/ontology/core#majorField", nested = true)
    private List<String> organizationForTraining;

    @Indexed
    private List<String> organizationForTrainingId;

    @Indexed
    @PropertySource(template = "organization/people", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> people;

    @Indexed
    @PropertySource(template = "organization/peopleType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> peopleType;

    @Indexed
    @PropertySource(template = "organization/peopleTitle", predicate = "http://vivoweb.org/ontology/core#hrJobTitle")
    private List<String> peopleTitle;

    @Indexed
    private List<String> peopleId;

    @Indexed
    @PropertySource(template = "organization/hasSubOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> hasSubOrganization;

    @Indexed
    private List<String> hasSubOrganizationId;

    @Indexed
    @PropertySource(template = "organization/organizationWithin", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> organizationWithin;

    @Indexed
    private List<String> organizationWithinId;

    @Indexed
    @PropertySource(template = "organization/leadOrganizationOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> leadOrganizationOf;

    @Indexed
    private List<String> leadOrganizationOfId;

    @Indexed
    @PropertySource(template = "organization/hasCollaboratingOrganizationOrGroup", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> hasCollaboratingOrganizationOrGroup;

    @Indexed
    private List<String> hasCollaboratingOrganizationOrGroupId;

    @Indexed
    @PropertySource(template = "organization/hasAffiliatedOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> hasAffiliatedOrganization;

    @Indexed
    private List<String> hasAffiliatedOrganizationId;

    @Indexed
    @PropertySource(template = "organization/memberOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> memberOf;

    @Indexed
    private List<String> memberOfId;

    @Indexed
    @PropertySource(template = "organization/clinicalActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> clinicalActivity;

    @Indexed
    private List<String> clinicalActivityId;

    @Indexed
    @PropertySource(template = "organization/convenerOfEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> convenerOfEvent;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/convenerOfEventDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> convenerOfEventDate;

    @Indexed
    private List<String> convenerOfEventId;

    @Indexed
    @PropertySource(template = "organization/attendedEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> attendedEvent;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/attendedEventDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> attendedEventDate;

    @Indexed
    private List<String> attendedEventId;

    @Indexed
    @PropertySource(template = "organization/selectedPublication", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> selectedPublication;

    @Indexed
    @PropertySource(template = "organization/selectedPublicationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> selectedPublicationType;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/selectedPublicationDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> selectedPublicationDate;

    @Indexed
    private List<String> selectedPublicationId;

    @Indexed
    @PropertySource(template = "organization/publisherOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true, unique = true)
    private List<String> publisherOf;

    @Indexed
    @PropertySource(template = "organization/publisherOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> publisherOfType;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/publisherOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> publisherOfDate;

    @Indexed
    private List<String> publisherOfId;

    @Indexed
    @PropertySource(template = "organization/presentation", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> presentation;

    @Indexed
    @PropertySource(template = "organization/presentationEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> presentationEvent;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/presentationDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> presentationDate;

    @Indexed
    private List<String> presentationId;

    @Indexed
    @PropertySource(template = "organization/featuredIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> featuredIn;

    @Indexed
    @PropertySource(template = "organization/featuredInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> featuredInType;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/featuredInDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> featuredInDate;

    @Indexed
    private List<String> featuredInId;

    @Indexed
    @PropertySource(template = "organization/assigneeForPatent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> assigneeForPatent;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/assigneeForPatentDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> assigneeForPatentDate;

    @Indexed
    private List<String> assigneeForPatentId;

    @Indexed
    @PropertySource(template = "organization/translatorOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> translatorOf;

    @Indexed
    @PropertySource(template = "organization/translatorOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> translatorOfType;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/translatorOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> translatorOfDate;

    @Indexed
    private List<String> translatorOfId;

    @Indexed
    @PropertySource(template = "organization/awardsGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> awardsGrant;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/awardsGrantDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardsGrantDate;

    @Indexed
    private List<String> awardsGrantId;

    @Indexed
    @PropertySource(template = "organization/administersGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> administersGrant;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/administersGrantDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> administersGrantDate;

    @Indexed
    private List<String> administersGrantId;

    @Indexed
    @PropertySource(template = "organization/subcontractsGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> subcontractsGrant;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/subcontractsGrantDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> subcontractsGrantDate;

    @Indexed
    private List<String> subcontractsGrantId;

    @Indexed
    @PropertySource(template = "organization/performsHumanStudy", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> performsHumanStudy;

    @Indexed
    private List<String> performsHumanStudyId;

    @Indexed
    @PropertySource(template = "organization/contractOrProviderForService", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> contractOrProviderForService;

    @Indexed
    private List<String> contractOrProviderForServiceId;

    @Indexed
    @PropertySource(template = "organization/outreachAndCommunityServiceActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> outreachAndCommunityServiceActivity;

    @Indexed
    private List<String> outreachAndCommunityServiceActivityId;

    @Indexed
    @PropertySource(template = "organization/hasEquipment", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> hasEquipment;

    @Indexed
    private List<String> hasEquipmentId;

    @Indexed
    @PropertySource(template = "organization/offersCourse", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> offersCourse;

    @Indexed
    private List<String> offersCourseId;

    @Indexed
    @PropertySource(template = "organization/orgId", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#OrgID")
    private String orgId;

    @Indexed
    @PropertySource(template = "organization/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "organization/sameAsType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed
    @PropertySource(template = "organization/phone", predicate = "http://www.w3.org/2006/vcard/ns#telephone")
    private String phone;

    @Indexed
    @PropertySource(template = "organization/fax", predicate = "http://www.w3.org/2006/vcard/ns#fax")
    private String fax;

    @Indexed
    @PropertySource(template = "organization/emailAddress", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private String emailAddress;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/streetAddress", predicate = "organization.streetAddress")
    private String streetAddress;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/locality", predicate = "organization.locality")
    private String locality;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/region", predicate = "organization.region")
    private String region;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/postalCode", predicate = "organization.postalCode")
    private String postalCode;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/country", predicate = "organization.country")
    private String country;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/geographicLocation", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private String geographicLocation;

    @Indexed
    private String geographicLocationId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/locatedAtFacility", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> locatedAtFacility;

    @Indexed
    private List<String> locatedAtFacilityId;

    @Indexed
    @PropertySource(template = "organization/predecessorOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> predecessorOrganization;

    @Indexed
    private List<String> predecessorOrganizationId;

    @Indexed
    @PropertySource(template = "organization/successorOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> successorOrganization;

    @Indexed
    private List<String> successorOrganizationId;

    @Indexed
    @PropertySource(template = "organization/governingAuthorityFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true)
    private List<String> governingAuthorityFor;

    @Indexed
    private List<String> governingAuthorityForId;

    // NOTE: unidirectional from Concept vivo:researchAreaOf
    @Indexed(copyTo = "_text_")
    @PropertySource(template = "organization/affiliatedResearchArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label", nested = true, unique = true)
    private List<String> affiliatedResearchArea;

    @Indexed
    private List<String> affiliatedResearchAreaId;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/modTime", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#modTime")
    private String modTime;

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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public List<String> getSponsorsAwardOrHonorDate() {
        return sponsorsAwardOrHonorDate;
    }

    public void setSponsorsAwardOrHonorDate(List<String> sponsorsAwardOrHonorDate) {
        this.sponsorsAwardOrHonorDate = sponsorsAwardOrHonorDate;
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

    public List<String> getAwardOrHonorGivenDate() {
        return awardOrHonorGivenDate;
    }

    public void setAwardOrHonorGivenDate(List<String> awardOrHonorGivenDate) {
        this.awardOrHonorGivenDate = awardOrHonorGivenDate;
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

    public List<String> getAwardOrHonorReceivedDate() {
        return awardOrHonorReceivedDate;
    }

    public void setAwardOrHonorReceivedDate(List<String> awardOrHonorReceivedDate) {
        this.awardOrHonorReceivedDate = awardOrHonorReceivedDate;
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

    public List<String> getHasCollaboratingOrganizationOrGroup() {
        return hasCollaboratingOrganizationOrGroup;
    }

    public void setHasCollaboratingOrganizationOrGroup(List<String> hasCollaboratingOrganizationOrGroup) {
        this.hasCollaboratingOrganizationOrGroup = hasCollaboratingOrganizationOrGroup;
    }

    public List<String> getHasCollaboratingOrganizationOrGroupId() {
        return hasCollaboratingOrganizationOrGroupId;
    }

    public void setHasCollaboratingOrganizationOrGroupId(List<String> hasCollaboratingOrganizationOrGroupId) {
        this.hasCollaboratingOrganizationOrGroupId = hasCollaboratingOrganizationOrGroupId;
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

    public List<String> getConvenerOfEventDate() {
        return convenerOfEventDate;
    }

    public void setConvenerOfEventDate(List<String> convenerOfEventDate) {
        this.convenerOfEventDate = convenerOfEventDate;
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

    public List<String> getAttendedEventDate() {
        return attendedEventDate;
    }

    public void setAttendedEventDate(List<String> attendedEventDate) {
        this.attendedEventDate = attendedEventDate;
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

    public List<String> getSelectedPublicationType() {
        return selectedPublicationType;
    }

    public void setSelectedPublicationType(List<String> selectedPublicationType) {
        this.selectedPublicationType = selectedPublicationType;
    }

    public List<String> getSelectedPublicationDate() {
        return selectedPublicationDate;
    }

    public void setSelectedPublicationDate(List<String> selectedPublicationDate) {
        this.selectedPublicationDate = selectedPublicationDate;
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

    public List<String> getPublisherOfType() {
        return publisherOfType;
    }

    public void setPublisherOfType(List<String> publisherOfType) {
        this.publisherOfType = publisherOfType;
    }

    public List<String> getPublisherOfDate() {
        return publisherOfDate;
    }

    public void setPublisherOfDate(List<String> publisherOfDate) {
        this.publisherOfDate = publisherOfDate;
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

    public List<String> getPresentationEvent() {
        return presentationEvent;
    }

    public void setPresentationEvent(List<String> presentationEvent) {
        this.presentationEvent = presentationEvent;
    }

    public List<String> getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(List<String> presentationDate) {
        this.presentationDate = presentationDate;
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

    public List<String> getFeaturedInType() {
        return featuredInType;
    }

    public void setFeaturedInType(List<String> featuredInType) {
        this.featuredInType = featuredInType;
    }

    public List<String> getFeaturedInDate() {
        return featuredInDate;
    }

    public void setFeaturedInDate(List<String> featuredInDate) {
        this.featuredInDate = featuredInDate;
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

    public List<String> getAssigneeForPatentDate() {
        return assigneeForPatentDate;
    }

    public void setAssigneeForPatentDate(List<String> assigneeForPatentDate) {
        this.assigneeForPatentDate = assigneeForPatentDate;
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

    public List<String> getTranslatorOfType() {
        return translatorOfType;
    }

    public void setTranslatorOfType(List<String> translatorOfType) {
        this.translatorOfType = translatorOfType;
    }

    public List<String> getTranslatorOfDate() {
        return translatorOfDate;
    }

    public void setTranslatorOfDate(List<String> translatorOfDate) {
        this.translatorOfDate = translatorOfDate;
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

    public List<String> getAwardsGrantDate() {
        return awardsGrantDate;
    }

    public void setAwardsGrantDate(List<String> awardsGrantDate) {
        this.awardsGrantDate = awardsGrantDate;
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

    public List<String> getAdministersGrantDate() {
        return administersGrantDate;
    }

    public void setAdministersGrantDate(List<String> administersGrantDate) {
        this.administersGrantDate = administersGrantDate;
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

    public List<String> getSubcontractsGrantDate() {
        return subcontractsGrantDate;
    }

    public void setSubcontractsGrantDate(List<String> subcontractsGrantDate) {
        this.subcontractsGrantDate = subcontractsGrantDate;
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

    public List<String> getAffiliatedResearchArea() {
        return affiliatedResearchArea;
    }

    public void setAffiliatedResearchArea(List<String> affiliatedResearchArea) {
        this.affiliatedResearchArea = affiliatedResearchArea;
    }

    public List<String> getAffiliatedResearchAreaId() {
        return affiliatedResearchAreaId;
    }

    public void setAffiliatedResearchAreaId(List<String> affiliatedResearchAreaId) {
        this.affiliatedResearchAreaId = affiliatedResearchAreaId;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject.Reference;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "organizations")
@CollectionSource(predicate = "http://xmlns.com/foaf/0.1/Organization")
public class Organization extends AbstractSolrDocument {

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/name", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String name;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "organization/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed(type = "whole_string")
    @PropertySource(template = "organization/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed(type = "whole_string")
    @PropertySource(template = "organization/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @NestedObject({ @Reference(value = "websiteUrl", key = "url") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/website", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> website;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/overview", predicate = "http://vivoweb.org/ontology/core#overview")
    private String overview;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/offersDegree", predicate = "organization.offersDegree")
    private List<String> offersDegree;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/abbreviation", predicate = "http://vivoweb.org/ontology/core#abbreviation")
    private String abbreviation;

    @Indexed(type = "pdate")
    @PropertySource(template = "organization/date", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> date;

    @NestedObject({ @Reference(value = "sponsorsAwardOrHonorDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/sponsorsAwardOrHonor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> sponsorsAwardOrHonor;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/sponsorsAwardOrHonorDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> sponsorsAwardOrHonorDate;

    @NestedObject({ @Reference(value = "awardOrHonorGivenDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/awardOrHonorGiven", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> awardOrHonorGiven;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/awardOrHonorGivenDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardOrHonorGivenDate;

    @NestedObject({ @Reference(value = "awardOrHonorReceivedDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/awardOrHonorReceived", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> awardOrHonorReceived;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/awardOrHonorReceivedDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardOrHonorReceivedDate;

    @Indexed(type = "delimited_strings", copyTo = "_text_")
    @PropertySource(template = "organization/keyword", predicate = "http://vivoweb.org/ontology/core#freetextKeyword")
    private List<String> keyword;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/organizationForTraining", predicate = "http://vivoweb.org/ontology/core#majorField")
    private List<String> organizationForTraining;

    @NestedObject({ @Reference(value = "peopleType", key = "type"), @Reference(value = "peopleTitle", key = "title") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/people", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> people;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/peopleType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> peopleType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/peopleTitle", predicate = "http://vivoweb.org/ontology/core#hrJobTitle")
    private List<String> peopleTitle;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/hasSubOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasSubOrganization;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/organizationWithin", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> organizationWithin;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/leadOrganizationOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> leadOrganizationOf;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/hasCollaboratingOrganizationOrGroup", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasCollaboratingOrganizationOrGroup;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/hasAffiliatedOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasAffiliatedOrganization;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/memberOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> memberOf;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/clinicalActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> clinicalActivity;

    @NestedObject({ @Reference(value = "convenerOfEventDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/convenerOfEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> convenerOfEvent;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/convenerOfEventDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> convenerOfEventDate;

    @NestedObject({ @Reference(value = "attendedEventDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/attendedEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> attendedEvent;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/attendedEventDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> attendedEventDate;

    @NestedObject({ @Reference(value = "selectedPublicationType", key = "type"), @Reference(value = "selectedPublicationDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/selectedPublication", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> selectedPublication;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/selectedPublicationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> selectedPublicationType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/selectedPublicationDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> selectedPublicationDate;

    @NestedObject({ @Reference(value = "publisherOfType", key = "type"), @Reference(value = "publisherOfDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/publisherOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private List<String> publisherOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/publisherOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> publisherOfType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/publisherOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> publisherOfDate;

    @NestedObject({ @Reference(value = "presentationEvent", key = "event"), @Reference(value = "presentationDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/presentation", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> presentation;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/presentationEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> presentationEvent;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/presentationDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> presentationDate;

    @NestedObject({ @Reference(value = "featuredInType", key = "type"), @Reference(value = "featuredInDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/featuredIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> featuredIn;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/featuredInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> featuredInType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/featuredInDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> featuredInDate;

    @NestedObject({ @Reference(value = "assigneeForPatentDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/assigneeForPatent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> assigneeForPatent;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/assigneeForPatentDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> assigneeForPatentDate;

    @NestedObject({ @Reference(value = "translatorOfType", key = "type"), @Reference(value = "translatorOfDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/translatorOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> translatorOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/translatorOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> translatorOfType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/translatorOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> translatorOfDate;

    @NestedObject({ @Reference(value = "awardsGrantDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/awardsGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> awardsGrant;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/awardsGrantDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardsGrantDate;

    @NestedObject({ @Reference(value = "administersGrantDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/administersGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> administersGrant;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/administersGrantDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> administersGrantDate;

    @NestedObject({ @Reference(value = "subcontractsGrantDate", key = "date") })
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/subcontractsGrant", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> subcontractsGrant;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "organization/subcontractsGrantDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> subcontractsGrantDate;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/performsHumanStudy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> performsHumanStudy;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/contractOrProviderForService", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> contractOrProviderForService;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/outreachAndCommunityServiceActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> outreachAndCommunityServiceActivity;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/hasEquipment", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasEquipment;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/offersCourse", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> offersCourse;

    @Indexed(type = "whole_string")
    @PropertySource(template = "organization/orgId", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#OrgID")
    private String orgId;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> sameAs;

    @Indexed(type = "whole_string")
    @PropertySource(template = "organization/phone", predicate = "http://www.w3.org/2006/vcard/ns#telephone")
    private String phone;

    @Indexed(type = "whole_string")
    @PropertySource(template = "organization/fax", predicate = "http://www.w3.org/2006/vcard/ns#fax")
    private String fax;

    @Indexed(type = "whole_string")
    @PropertySource(template = "organization/emailAddress", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private String emailAddress;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/streetAddress", predicate = "organization.streetAddress")
    private String streetAddress;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/locality", predicate = "organization.locality")
    private String locality;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/region", predicate = "organization.region")
    private String region;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/postalCode", predicate = "organization.postalCode")
    private String postalCode;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/country", predicate = "organization.country")
    private String country;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "organization/geographicLocation", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String geographicLocation;

    @NestedObject
    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "organization/locatedAtFacility", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> locatedAtFacility;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/predecessorOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> predecessorOrganization;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/successorOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> successorOrganization;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "organization/governingAuthorityFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> governingAuthorityFor;

    // NOTE: unidirectional from Concept vivo:researchAreaOf
    @NestedObject
    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "organization/affiliatedResearchArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private List<String> affiliatedResearchArea;

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

    public List<String> getWebsite() {
        return website;
    }

    public void setWebsite(List<String> website) {
        this.website = website;
    }

    public List<String> getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(List<String> websiteUrl) {
        this.websiteUrl = websiteUrl;
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

    public List<String> getHasSubOrganization() {
        return hasSubOrganization;
    }

    public void setHasSubOrganization(List<String> hasSubOrganization) {
        this.hasSubOrganization = hasSubOrganization;
    }

    public List<String> getOrganizationWithin() {
        return organizationWithin;
    }

    public void setOrganizationWithin(List<String> organizationWithin) {
        this.organizationWithin = organizationWithin;
    }

    public List<String> getLeadOrganizationOf() {
        return leadOrganizationOf;
    }

    public void setLeadOrganizationOf(List<String> leadOrganizationOf) {
        this.leadOrganizationOf = leadOrganizationOf;
    }

    public List<String> getHasCollaboratingOrganizationOrGroup() {
        return hasCollaboratingOrganizationOrGroup;
    }

    public void setHasCollaboratingOrganizationOrGroup(List<String> hasCollaboratingOrganizationOrGroup) {
        this.hasCollaboratingOrganizationOrGroup = hasCollaboratingOrganizationOrGroup;
    }

    public List<String> getHasAffiliatedOrganization() {
        return hasAffiliatedOrganization;
    }

    public void setHasAffiliatedOrganization(List<String> hasAffiliatedOrganization) {
        this.hasAffiliatedOrganization = hasAffiliatedOrganization;
    }

    public List<String> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<String> memberOf) {
        this.memberOf = memberOf;
    }

    public List<String> getClinicalActivity() {
        return clinicalActivity;
    }

    public void setClinicalActivity(List<String> clinicalActivity) {
        this.clinicalActivity = clinicalActivity;
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

    public List<String> getPerformsHumanStudy() {
        return performsHumanStudy;
    }

    public void setPerformsHumanStudy(List<String> performsHumanStudy) {
        this.performsHumanStudy = performsHumanStudy;
    }

    public List<String> getContractOrProviderForService() {
        return contractOrProviderForService;
    }

    public void setContractOrProviderForService(List<String> contractOrProviderForService) {
        this.contractOrProviderForService = contractOrProviderForService;
    }

    public List<String> getOutreachAndCommunityServiceActivity() {
        return outreachAndCommunityServiceActivity;
    }

    public void setOutreachAndCommunityServiceActivity(List<String> outreachAndCommunityServiceActivity) {
        this.outreachAndCommunityServiceActivity = outreachAndCommunityServiceActivity;
    }

    public List<String> getHasEquipment() {
        return hasEquipment;
    }

    public void setHasEquipment(List<String> hasEquipment) {
        this.hasEquipment = hasEquipment;
    }

    public List<String> getOffersCourse() {
        return offersCourse;
    }

    public void setOffersCourse(List<String> offersCourse) {
        this.offersCourse = offersCourse;
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

    public String getGeographicLocation() {
        return geographicLocation;
    }

    public void setGeographicLocation(String geographicLocation) {
        this.geographicLocation = geographicLocation;
    }

    public List<String> getLocatedAtFacility() {
        return locatedAtFacility;
    }

    public void setLocatedAtFacility(List<String> locatedAtFacility) {
        this.locatedAtFacility = locatedAtFacility;
    }

    public List<String> getPredecessorOrganization() {
        return predecessorOrganization;
    }

    public void setPredecessorOrganization(List<String> predecessorOrganization) {
        this.predecessorOrganization = predecessorOrganization;
    }

    public List<String> getSuccessorOrganization() {
        return successorOrganization;
    }

    public void setSuccessorOrganization(List<String> successorOrganization) {
        this.successorOrganization = successorOrganization;
    }

    public List<String> getGoverningAuthorityFor() {
        return governingAuthorityFor;
    }

    public void setGoverningAuthorityFor(List<String> governingAuthorityFor) {
        this.governingAuthorityFor = governingAuthorityFor;
    }

    public List<String> getAffiliatedResearchArea() {
        return affiliatedResearchArea;
    }

    public void setAffiliatedResearchArea(List<String> affiliatedResearchArea) {
        this.affiliatedResearchArea = affiliatedResearchArea;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

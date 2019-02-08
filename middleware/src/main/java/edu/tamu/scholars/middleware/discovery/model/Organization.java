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

}

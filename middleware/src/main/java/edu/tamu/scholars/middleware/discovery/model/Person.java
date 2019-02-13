package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;

@JsonInclude(NON_EMPTY)
//@formatter:off
@CollectionSource(
    template = "person/persons",
    key = "person.class",
    properties = {
        
    }
)
// @formatter:on
@SolrDocument(collection = "persons")
public class Person extends AbstractSolrDocument {

    @Indexed
    private String name;

    @Indexed
    private List<String> type;

    @Indexed
    private String image;

    @Indexed
    private String thumbnail;

    @Indexed
    private String primaryEmail;

    @Indexed
    private List<String> additionalEmail;

    @Indexed
    private String phone;

    @Indexed
    private List<String> websiteUrl;

    @Indexed
    private List<String> websiteLabel;

    @Indexed
    private String orcidId;

    @Indexed
    private String preferredTitle;

    @Indexed
    private List<String> position;

    @Indexed
    private List<String> positionTitle;

    @Indexed
    private List<String> positionOrganization;

    @Indexed
    private List<String> positionId;

    @Indexed
    private String overview;

    @Indexed
    private List<String> researchArea;

    @Indexed
    private List<String> researchAreaId;

    @Indexed
    private List<String> geographicFocus;

    @Indexed
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed
    private String hrJobTitle;

    @Indexed
    private List<String> keyword;

    @Indexed
    private List<String> headOf;

    @Indexed
    private List<String> headOfId;

    @Indexed
    private List<String> memberOf;

    @Indexed
    private List<String> memberOfId;

    @Indexed
    private List<String> hasCollaborator;

    @Indexed
    private List<String> hasCollaboratorId;

    @Indexed
    private List<String> clinicalActivity;

    @Indexed
    private List<String> clinicalActivityId;

    @Indexed
    private List<String> attendedEvent;

    @Indexed
    private List<String> attendedEventId;

    @Indexed
    private List<String> educationAndTraining;

    @Indexed
    private List<String> educationAndTrainingOrganization;

    @Indexed(type = "pdate")
    private List<String> educationAndTrainingDate;

    @Indexed
    private List<String> educationAndTrainingId;

    @Indexed
    private List<String> credentials;

    @Indexed
    private List<String> credentialsId;

    @Indexed
    private List<String> credentialEligibilityAttained;

    @Indexed
    private List<String> credentialEligibilityAttainedId;

    @Indexed
    private List<String> awardsAndHonors;

    @Indexed(type = "pdate")
    private List<String> awardsAndHonorsDate;

    @Indexed
    private List<String> awardsAndHonorsId;

    @Indexed
    private List<String> adviseeOf;

    @Indexed
    private List<String> adviseeOfId;

    @Indexed
    private List<String> selectedPublication;

    @Indexed
    private List<String> selectedPublicationType;

    @Indexed(type = "pdate")
    private List<String> selectedPublicationDate;

    @Indexed
    private List<String> selectedPublicationId;

    @Indexed
    private List<String> collectionOrSeriesEditorFor;

    @Indexed
    private List<String> collectionOrSeriesEditorForType;

    @Indexed(type = "pdate")
    private List<String> collectionOrSeriesEditorForDate;

    @Indexed
    private List<String> collectionOrSeriesEditorForId;

    @Indexed
    private List<String> editorOf;

    @Indexed
    private List<String> editorOfType;

    @Indexed(type = "pdate")
    private List<String> editorOfDate;

    @Indexed
    private List<String> editorOfId;

    @Indexed
    private List<String> presentation;

    @Indexed
    private List<String> presentationEvent;

    @Indexed(type = "pdate")
    private List<String> presentationDate;

    @Indexed
    private List<String> presentationId;

    @Indexed
    private List<String> featuredIn;

    @Indexed
    private List<String> featuredInType;

    @Indexed(type = "pdate")
    private List<String> featuredInDate;

    @Indexed
    private List<String> featuredInId;

    @Indexed
    private List<String> assigneeForPatent;

    @Indexed(type = "pdate")
    private List<String> assigneeForPatentDate;

    @Indexed
    private List<String> assigneeForPatentId;

    @Indexed
    private List<String> translatorOf;

    @Indexed
    private List<String> translatorOfType;

    @Indexed(type = "pdate")
    private List<String> translatorOfDate;

    @Indexed
    private List<String> translatorOfId;

    @Indexed
    private String researchOverview;

    @Indexed
    private List<String> principalInvestigatorOn;

    @Indexed
    private List<String> principalInvestigatorOnAwardedBy;

    @Indexed(type = "pdate")
    private List<String> principalInvestigatorOnDate;

    @Indexed
    private List<String> principalInvestigatorOnId;

    @Indexed
    private List<String> coPrincipalInvestigatorOn;

    @Indexed
    private List<String> coPrincipalInvestigatorOnAwardedBy;

    @Indexed(type = "pdate")
    private List<String> coPrincipalInvestigatorOnDate;

    @Indexed
    private List<String> coPrincipalInvestigatorOnId;

    @Indexed
    private List<String> investigatorOn;

    @Indexed
    private List<String> investigatorOnAwardedBy;

    @Indexed(type = "pdate")
    private List<String> investigatorOnDate;

    @Indexed
    private List<String> iInvestigatorOnId;

    @Indexed
    private List<String> otherResearchActivity;

    @Indexed
    private List<String> otherResearchActivityId;

    @Indexed
    private String teachingOverview;

    @Indexed
    private List<String> teachingActivity;

    @Indexed
    private List<String> teachingActivityId;

    @Indexed
    private List<String> advisee;

    @Indexed
    private List<String> adviseeId;

    @Indexed
    private String outreachOverview;

    @Indexed
    private List<String> reviewerOf;

    @Indexed
    private List<String> reviewerOfType;

    @Indexed(type = "pdate")
    private List<String> reviewerOfDate;

    @Indexed
    private List<String> reviewerOfId;

    @Indexed
    private List<String> contactOrProvidorForService;

    @Indexed
    private List<String> contactOrProvidorForServiceId;

    @Indexed
    private List<String> organizerOfEvent;

    @Indexed(type = "pdate")
    private List<String> organizerOfEventDate;

    @Indexed
    private List<String> organizerOfEventId;

    @Indexed
    private List<String> professionalServiceActivity;

    @Indexed
    private List<String> professionalServiceActivityId;

    @Indexed
    private List<String> outreachAndCommunityServiceActivity;

    @Indexed
    private List<String> outreachAndCommunityServiceActivityId;

    @Indexed
    private List<String> performsTechnique;

    @Indexed
    private List<String> performsTechniqueId;

    @Indexed
    private List<String> hasExpertiseInTechnique;

    @Indexed
    private List<String> hasExpertiseInTechniqueId;

    @Indexed
    private String isni;

    @Indexed
    private String netid;

    @Indexed
    private String researcherId;

    @Indexed
    private String twitter;

    @Indexed
    private String uid;

    @Indexed
    private String uin;

    @Indexed
    private String youtube;

    @Indexed
    private String eRACommonsId;

    @Indexed
    private String isiResearcherId;

    @Indexed
    private String scopusId;

    @Indexed
    private String healthCareProviderId;

    @Indexed
    private String email;

    @Indexed
    private String firstName;

    @Indexed
    private String middleName;

    @Indexed
    private String lastName;

    @Indexed
    private String fullName;

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

    @Indexed(type = "location")
    private String geographicLocation;

    @Indexed
    private String geographicLocationId;

    @Indexed
    private List<String> locatedInFacility;

    @Indexed
    private List<String> locatedInFacilityId;

    @Indexed
    private String fax;

    @Indexed
    private List<String> etdChairOf;

    @Indexed
    private List<String> etdChairOfPerson;

    @Indexed
    private List<String> etdChairOfDate;

    @Indexed
    private List<String> etdChairOfId;

    public Person() {

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

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public List<String> getAdditionalEmail() {
        return additionalEmail;
    }

    public void setAdditionalEmail(List<String> additionalEmail) {
        this.additionalEmail = additionalEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getOrcidId() {
        return orcidId;
    }

    public void setOrcidId(String orcidId) {
        this.orcidId = orcidId;
    }

    public String getPreferredTitle() {
        return preferredTitle;
    }

    public void setPreferredTitle(String preferredTitle) {
        this.preferredTitle = preferredTitle;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }

    public List<String> getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(List<String> positionTitle) {
        this.positionTitle = positionTitle;
    }

    public List<String> getPositionOrganization() {
        return positionOrganization;
    }

    public void setPositionOrganization(List<String> positionOrganization) {
        this.positionOrganization = positionOrganization;
    }

    public List<String> getPositionId() {
        return positionId;
    }

    public void setPositionId(List<String> positionId) {
        this.positionId = positionId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<String> getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(List<String> researchArea) {
        this.researchArea = researchArea;
    }

    public List<String> getResearchAreaId() {
        return researchAreaId;
    }

    public void setResearchAreaId(List<String> researchAreaId) {
        this.researchAreaId = researchAreaId;
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

    public String getHrJobTitle() {
        return hrJobTitle;
    }

    public void setHrJobTitle(String hrJobTitle) {
        this.hrJobTitle = hrJobTitle;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public List<String> getHeadOf() {
        return headOf;
    }

    public void setHeadOf(List<String> headOf) {
        this.headOf = headOf;
    }

    public List<String> getHeadOfId() {
        return headOfId;
    }

    public void setHeadOfId(List<String> headOfId) {
        this.headOfId = headOfId;
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

    public List<String> getHasCollaborator() {
        return hasCollaborator;
    }

    public void setHasCollaborator(List<String> hasCollaborator) {
        this.hasCollaborator = hasCollaborator;
    }

    public List<String> getHasCollaboratorId() {
        return hasCollaboratorId;
    }

    public void setHasCollaboratorId(List<String> hasCollaboratorId) {
        this.hasCollaboratorId = hasCollaboratorId;
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

    public List<String> getEducationAndTraining() {
        return educationAndTraining;
    }

    public void setEducationAndTraining(List<String> educationAndTraining) {
        this.educationAndTraining = educationAndTraining;
    }

    public List<String> getEducationAndTrainingOrganization() {
        return educationAndTrainingOrganization;
    }

    public void setEducationAndTrainingOrganization(List<String> educationAndTrainingOrganization) {
        this.educationAndTrainingOrganization = educationAndTrainingOrganization;
    }

    public List<String> getEducationAndTrainingDate() {
        return educationAndTrainingDate;
    }

    public void setEducationAndTrainingDate(List<String> educationAndTrainingDate) {
        this.educationAndTrainingDate = educationAndTrainingDate;
    }

    public List<String> getEducationAndTrainingId() {
        return educationAndTrainingId;
    }

    public void setEducationAndTrainingId(List<String> educationAndTrainingId) {
        this.educationAndTrainingId = educationAndTrainingId;
    }

    public List<String> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<String> credentials) {
        this.credentials = credentials;
    }

    public List<String> getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(List<String> credentialsId) {
        this.credentialsId = credentialsId;
    }

    public List<String> getCredentialEligibilityAttained() {
        return credentialEligibilityAttained;
    }

    public void setCredentialEligibilityAttained(List<String> credentialEligibilityAttained) {
        this.credentialEligibilityAttained = credentialEligibilityAttained;
    }

    public List<String> getCredentialEligibilityAttainedId() {
        return credentialEligibilityAttainedId;
    }

    public void setCredentialEligibilityAttainedId(List<String> credentialEligibilityAttainedId) {
        this.credentialEligibilityAttainedId = credentialEligibilityAttainedId;
    }

    public List<String> getAwardsAndHonors() {
        return awardsAndHonors;
    }

    public void setAwardsAndHonors(List<String> awardsAndHonors) {
        this.awardsAndHonors = awardsAndHonors;
    }

    public List<String> getAwardsAndHonorsDate() {
        return awardsAndHonorsDate;
    }

    public void setAwardsAndHonorsDate(List<String> awardsAndHonorsDate) {
        this.awardsAndHonorsDate = awardsAndHonorsDate;
    }

    public List<String> getAwardsAndHonorsId() {
        return awardsAndHonorsId;
    }

    public void setAwardsAndHonorsId(List<String> awardsAndHonorsId) {
        this.awardsAndHonorsId = awardsAndHonorsId;
    }

    public List<String> getAdviseeOf() {
        return adviseeOf;
    }

    public void setAdviseeOf(List<String> adviseeOf) {
        this.adviseeOf = adviseeOf;
    }

    public List<String> getAdviseeOfId() {
        return adviseeOfId;
    }

    public void setAdviseeOfId(List<String> adviseeOfId) {
        this.adviseeOfId = adviseeOfId;
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

    public List<String> getCollectionOrSeriesEditorFor() {
        return collectionOrSeriesEditorFor;
    }

    public void setCollectionOrSeriesEditorFor(List<String> collectionOrSeriesEditorFor) {
        this.collectionOrSeriesEditorFor = collectionOrSeriesEditorFor;
    }

    public List<String> getCollectionOrSeriesEditorForType() {
        return collectionOrSeriesEditorForType;
    }

    public void setCollectionOrSeriesEditorForType(List<String> collectionOrSeriesEditorForType) {
        this.collectionOrSeriesEditorForType = collectionOrSeriesEditorForType;
    }

    public List<String> getCollectionOrSeriesEditorForDate() {
        return collectionOrSeriesEditorForDate;
    }

    public void setCollectionOrSeriesEditorForDate(List<String> collectionOrSeriesEditorForDate) {
        this.collectionOrSeriesEditorForDate = collectionOrSeriesEditorForDate;
    }

    public List<String> getCollectionOrSeriesEditorForId() {
        return collectionOrSeriesEditorForId;
    }

    public void setCollectionOrSeriesEditorForId(List<String> collectionOrSeriesEditorForId) {
        this.collectionOrSeriesEditorForId = collectionOrSeriesEditorForId;
    }

    public List<String> getEditorOf() {
        return editorOf;
    }

    public void setEditorOf(List<String> editorOf) {
        this.editorOf = editorOf;
    }

    public List<String> getEditorOfType() {
        return editorOfType;
    }

    public void setEditorOfType(List<String> editorOfType) {
        this.editorOfType = editorOfType;
    }

    public List<String> getEditorOfDate() {
        return editorOfDate;
    }

    public void setEditorOfDate(List<String> editorOfDate) {
        this.editorOfDate = editorOfDate;
    }

    public List<String> getEditorOfId() {
        return editorOfId;
    }

    public void setEditorOfId(List<String> editorOfId) {
        this.editorOfId = editorOfId;
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

    public String getResearchOverview() {
        return researchOverview;
    }

    public void setResearchOverview(String researchOverview) {
        this.researchOverview = researchOverview;
    }

    public List<String> getPrincipalInvestigatorOn() {
        return principalInvestigatorOn;
    }

    public void setPrincipalInvestigatorOn(List<String> principalInvestigatorOn) {
        this.principalInvestigatorOn = principalInvestigatorOn;
    }

    public List<String> getPrincipalInvestigatorOnAwardedBy() {
        return principalInvestigatorOnAwardedBy;
    }

    public void setPrincipalInvestigatorOnAwardedBy(List<String> principalInvestigatorOnAwardedBy) {
        this.principalInvestigatorOnAwardedBy = principalInvestigatorOnAwardedBy;
    }

    public List<String> getPrincipalInvestigatorOnDate() {
        return principalInvestigatorOnDate;
    }

    public void setPrincipalInvestigatorOnDate(List<String> principalInvestigatorOnDate) {
        this.principalInvestigatorOnDate = principalInvestigatorOnDate;
    }

    public List<String> getPrincipalInvestigatorOnId() {
        return principalInvestigatorOnId;
    }

    public void setPrincipalInvestigatorOnId(List<String> principalInvestigatorOnId) {
        this.principalInvestigatorOnId = principalInvestigatorOnId;
    }

    public List<String> getCoPrincipalInvestigatorOn() {
        return coPrincipalInvestigatorOn;
    }

    public void setCoPrincipalInvestigatorOn(List<String> coPrincipalInvestigatorOn) {
        this.coPrincipalInvestigatorOn = coPrincipalInvestigatorOn;
    }

    public List<String> getCoPrincipalInvestigatorOnAwardedBy() {
        return coPrincipalInvestigatorOnAwardedBy;
    }

    public void setCoPrincipalInvestigatorOnAwardedBy(List<String> coPrincipalInvestigatorOnAwardedBy) {
        this.coPrincipalInvestigatorOnAwardedBy = coPrincipalInvestigatorOnAwardedBy;
    }

    public List<String> getCoPrincipalInvestigatorOnDate() {
        return coPrincipalInvestigatorOnDate;
    }

    public void setCoPrincipalInvestigatorOnDate(List<String> coPrincipalInvestigatorOnDate) {
        this.coPrincipalInvestigatorOnDate = coPrincipalInvestigatorOnDate;
    }

    public List<String> getCoPrincipalInvestigatorOnId() {
        return coPrincipalInvestigatorOnId;
    }

    public void setCoPrincipalInvestigatorOnId(List<String> coPrincipalInvestigatorOnId) {
        this.coPrincipalInvestigatorOnId = coPrincipalInvestigatorOnId;
    }

    public List<String> getInvestigatorOn() {
        return investigatorOn;
    }

    public void setInvestigatorOn(List<String> investigatorOn) {
        this.investigatorOn = investigatorOn;
    }

    public List<String> getInvestigatorOnAwardedBy() {
        return investigatorOnAwardedBy;
    }

    public void setInvestigatorOnAwardedBy(List<String> investigatorOnAwardedBy) {
        this.investigatorOnAwardedBy = investigatorOnAwardedBy;
    }

    public List<String> getInvestigatorOnDate() {
        return investigatorOnDate;
    }

    public void setInvestigatorOnDate(List<String> investigatorOnDate) {
        this.investigatorOnDate = investigatorOnDate;
    }

    public List<String> getiInvestigatorOnId() {
        return iInvestigatorOnId;
    }

    public void setiInvestigatorOnId(List<String> iInvestigatorOnId) {
        this.iInvestigatorOnId = iInvestigatorOnId;
    }

    public List<String> getOtherResearchActivity() {
        return otherResearchActivity;
    }

    public void setOtherResearchActivity(List<String> otherResearchActivity) {
        this.otherResearchActivity = otherResearchActivity;
    }

    public List<String> getOtherResearchActivityId() {
        return otherResearchActivityId;
    }

    public void setOtherResearchActivityId(List<String> otherResearchActivityId) {
        this.otherResearchActivityId = otherResearchActivityId;
    }

    public String getTeachingOverview() {
        return teachingOverview;
    }

    public void setTeachingOverview(String teachingOverview) {
        this.teachingOverview = teachingOverview;
    }

    public List<String> getTeachingActivity() {
        return teachingActivity;
    }

    public void setTeachingActivity(List<String> teachingActivity) {
        this.teachingActivity = teachingActivity;
    }

    public List<String> getTeachingActivityId() {
        return teachingActivityId;
    }

    public void setTeachingActivityId(List<String> teachingActivityId) {
        this.teachingActivityId = teachingActivityId;
    }

    public List<String> getAdvisee() {
        return advisee;
    }

    public void setAdvisee(List<String> advisee) {
        this.advisee = advisee;
    }

    public List<String> getAdviseeId() {
        return adviseeId;
    }

    public void setAdviseeId(List<String> adviseeId) {
        this.adviseeId = adviseeId;
    }

    public String getOutreachOverview() {
        return outreachOverview;
    }

    public void setOutreachOverview(String outreachOverview) {
        this.outreachOverview = outreachOverview;
    }

    public List<String> getReviewerOf() {
        return reviewerOf;
    }

    public void setReviewerOf(List<String> reviewerOf) {
        this.reviewerOf = reviewerOf;
    }

    public List<String> getReviewerOfType() {
        return reviewerOfType;
    }

    public void setReviewerOfType(List<String> reviewerOfType) {
        this.reviewerOfType = reviewerOfType;
    }

    public List<String> getReviewerOfDate() {
        return reviewerOfDate;
    }

    public void setReviewerOfDate(List<String> reviewerOfDate) {
        this.reviewerOfDate = reviewerOfDate;
    }

    public List<String> getReviewerOfId() {
        return reviewerOfId;
    }

    public void setReviewerOfId(List<String> reviewerOfId) {
        this.reviewerOfId = reviewerOfId;
    }

    public List<String> getContactOrProvidorForService() {
        return contactOrProvidorForService;
    }

    public void setContactOrProvidorForService(List<String> contactOrProvidorForService) {
        this.contactOrProvidorForService = contactOrProvidorForService;
    }

    public List<String> getContactOrProvidorForServiceId() {
        return contactOrProvidorForServiceId;
    }

    public void setContactOrProvidorForServiceId(List<String> contactOrProvidorForServiceId) {
        this.contactOrProvidorForServiceId = contactOrProvidorForServiceId;
    }

    public List<String> getOrganizerOfEvent() {
        return organizerOfEvent;
    }

    public void setOrganizerOfEvent(List<String> organizerOfEvent) {
        this.organizerOfEvent = organizerOfEvent;
    }

    public List<String> getOrganizerOfEventDate() {
        return organizerOfEventDate;
    }

    public void setOrganizerOfEventDate(List<String> organizerOfEventDate) {
        this.organizerOfEventDate = organizerOfEventDate;
    }

    public List<String> getOrganizerOfEventId() {
        return organizerOfEventId;
    }

    public void setOrganizerOfEventId(List<String> organizerOfEventId) {
        this.organizerOfEventId = organizerOfEventId;
    }

    public List<String> getProfessionalServiceActivity() {
        return professionalServiceActivity;
    }

    public void setProfessionalServiceActivity(List<String> professionalServiceActivity) {
        this.professionalServiceActivity = professionalServiceActivity;
    }

    public List<String> getProfessionalServiceActivityId() {
        return professionalServiceActivityId;
    }

    public void setProfessionalServiceActivityId(List<String> professionalServiceActivityId) {
        this.professionalServiceActivityId = professionalServiceActivityId;
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

    public List<String> getPerformsTechnique() {
        return performsTechnique;
    }

    public void setPerformsTechnique(List<String> performsTechnique) {
        this.performsTechnique = performsTechnique;
    }

    public List<String> getPerformsTechniqueId() {
        return performsTechniqueId;
    }

    public void setPerformsTechniqueId(List<String> performsTechniqueId) {
        this.performsTechniqueId = performsTechniqueId;
    }

    public List<String> getHasExpertiseInTechnique() {
        return hasExpertiseInTechnique;
    }

    public void setHasExpertiseInTechnique(List<String> hasExpertiseInTechnique) {
        this.hasExpertiseInTechnique = hasExpertiseInTechnique;
    }

    public List<String> getHasExpertiseInTechniqueId() {
        return hasExpertiseInTechniqueId;
    }

    public void setHasExpertiseInTechniqueId(List<String> hasExpertiseInTechniqueId) {
        this.hasExpertiseInTechniqueId = hasExpertiseInTechniqueId;
    }

    public String getIsni() {
        return isni;
    }

    public void setIsni(String isni) {
        this.isni = isni;
    }

    public String getNetid() {
        return netid;
    }

    public void setNetid(String netid) {
        this.netid = netid;
    }

    public String getResearcherId() {
        return researcherId;
    }

    public void setResearcherId(String researcherId) {
        this.researcherId = researcherId;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String geteRACommonsId() {
        return eRACommonsId;
    }

    public void seteRACommonsId(String eRACommonsId) {
        this.eRACommonsId = eRACommonsId;
    }

    public String getIsiResearcherId() {
        return isiResearcherId;
    }

    public void setIsiResearcherId(String isiResearcherId) {
        this.isiResearcherId = isiResearcherId;
    }

    public String getScopusId() {
        return scopusId;
    }

    public void setScopusId(String scopusId) {
        this.scopusId = scopusId;
    }

    public String getHealthCareProviderId() {
        return healthCareProviderId;
    }

    public void setHealthCareProviderId(String healthCareProviderId) {
        this.healthCareProviderId = healthCareProviderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public List<String> getLocatedInFacility() {
        return locatedInFacility;
    }

    public void setLocatedInFacility(List<String> locatedInFacility) {
        this.locatedInFacility = locatedInFacility;
    }

    public List<String> getLocatedInFacilityId() {
        return locatedInFacilityId;
    }

    public void setLocatedInFacilityId(List<String> locatedInFacilityId) {
        this.locatedInFacilityId = locatedInFacilityId;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<String> getEtdChairOf() {
        return etdChairOf;
    }

    public void setEtdChairOf(List<String> etdChairOf) {
        this.etdChairOf = etdChairOf;
    }

    public List<String> getEtdChairOfPerson() {
        return etdChairOfPerson;
    }

    public void setEtdChairOfPerson(List<String> etdChairOfPerson) {
        this.etdChairOfPerson = etdChairOfPerson;
    }

    public List<String> getEtdChairOfDate() {
        return etdChairOfDate;
    }

    public void setEtdChairOfDate(List<String> etdChairOfDate) {
        this.etdChairOfDate = etdChairOfDate;
    }

    public List<String> getEtdChairOfId() {
        return etdChairOfId;
    }

    public void setEtdChairOfId(List<String> etdChairOfId) {
        this.etdChairOfId = etdChairOfId;
    }

}

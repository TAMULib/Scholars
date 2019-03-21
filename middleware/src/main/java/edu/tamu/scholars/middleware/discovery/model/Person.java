package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "persons")
@CollectionSource(key = "person.class")
public class Person extends AbstractSolrDocument {

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/name", key = "person.name")
    private String name;

    @Indexed
    @PropertySource(template = "person/type", key = "person.type", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "person/image", key = "person.image")
    private String image;

    @Indexed
    @PropertySource(template = "person/thumbnail", key = "person.thumbnail")
    private String thumbnail;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/primaryEmail", key = "person.primaryEmail")
    private String primaryEmail;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/additionalEmail", key = "person.additionalEmail")
    private List<String> additionalEmail;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/phone", key = "person.phone")
    private String phone;

    @Indexed
    @PropertySource(template = "person/websiteUrl", key = "person.website.url")
    private List<String> websiteUrl;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/websiteLabel", key = "person.website.label")
    private List<String> websiteLabel;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/orcidId", key = "person.orcidId", parse = true)
    private String orcidId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/preferredTitle", key = "person.preferredTitle")
    private String preferredTitle;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/position", key = "person.position.title", id = "positionId")
    private List<String> position;

    @Indexed
    @PropertySource(template = "person/positionType", key = "person.position.type", parse = true)
    private List<String> positionType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/positionOrganization", key = "person.position.organization")
    private List<String> positionOrganization;

    @Indexed
    private List<String> positionId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/overview", key = "person.overview")
    private String overview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/researchArea", key = "person.researchArea", id = "researchAreaId")
    private List<String> researchArea;

    @Indexed
    private List<String> researchAreaId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/geographicFocus", key = "person.geographicFocus.name", id = "geographicFocusId")
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "person/geographicFocusType", key = "person.geographicFocus.type", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/hrJobTitle", key = "person.hrJobTitle")
    private String hrJobTitle;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/keyword", key = "person.keyword")
    private List<String> keyword;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/headOf", key = "person.headOf.role", id = "headOfId")
    private List<String> headOf;

    @Indexed
    @PropertySource(template = "person/headOfType", key = "person.headOf.type", parse = true)
    private List<String> headOfType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/headOfOrganization", key = "person.headOf.organization")
    private List<String> headOfOrganization;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/headOfStartDate", key = "person.headOf.startDate")
    private List<String> headOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/headOfEndDate", key = "person.headOf.endDate")
    private List<String> headOfEndDate;

    @Indexed
    private List<String> headOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/memberOf", key = "person.memberOf.role", id = "memberOfId")
    private List<String> memberOf;

    @Indexed
    @PropertySource(template = "person/memberOfType", key = "person.memberOf.type", parse = true)
    private List<String> memberOfType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/memberOfOrganization", key = "person.memberOf.organization")
    private List<String> memberOfOrganization;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/memberOfStartDate", key = "person.memberOf.startDate")
    private List<String> memberOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/memberOfEndDate", key = "person.memberOf.endDate")
    private List<String> memberOfEndDate;

    @Indexed
    private List<String> memberOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/hasCollaborator", key = "person.hasCollaborator", id = "hasCollaboratorId")
    private List<String> hasCollaborator;

    @Indexed
    private List<String> hasCollaboratorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/clinicalActivity", key = "person.clinicalActivity.name", id = "clinicalActivityId")
    private List<String> clinicalActivity;

    @Indexed
    @PropertySource(template = "person/clinicalActivityType", key = "person.clinicalActivity.type", parse = true)
    private List<String> clinicalActivityType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/clinicalActivityRole", key = "person.clinicalActivity.role")
    private List<String> clinicalActivityRole;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/clinicalActivityStartDate", key = "person.clinicalActivity.startDate")
    private List<String> clinicalActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/clinicalActivityEndDate", key = "person.clinicalActivity.endDate")
    private List<String> clinicalActivityEndDate;

    @Indexed
    private List<String> clinicalActivityId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/attendedEvent", key = "person.attendedEvent.name", id = "attendedEventId")
    private List<String> attendedEvent;

    @Indexed
    @PropertySource(template = "person/attendedEventType", key = "person.attendedEvent.name", parse = true)
    private List<String> attendedEventType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/attendedEventStartDate", key = "person.attendedEvent.startDate")
    private List<String> attendedEventStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/attendedEventEndDate", key = "person.attendedEvent.endDate")
    private List<String> attendedEventEndDate;

    @Indexed
    private List<String> attendedEventId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/educationAndTraining", key = "person.educationAndTraining.name", id = "educationAndTrainingId")
    private List<String> educationAndTraining;

    @Indexed
    @PropertySource(template = "person/educationAndTrainingRole", key = "person.educationAndTraining.role")
    private List<String> educationAndTrainingRole;

    @Indexed
    @PropertySource(template = "person/educationAndTrainingType", key = "person.educationAndTraining.type", parse = true)
    private List<String> educationAndTrainingType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/educationAndTrainingStartDate", key = "person.educationAndTraining.startDate")
    private List<String> educationAndTrainingStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/educationAndTrainingEndDate", key = "person.educationAndTraining.endDate")
    private List<String> educationAndTrainingEndDate;

    @Indexed
    private List<String> educationAndTrainingId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/credentials", key = "person.credentials.name", id = "credentialsId")
    private List<String> credentials;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/credentialsDateIssued", key = "person.credentials.dateIssued")
    private List<String> credentialsDateIssued;

    @Indexed
    private List<String> credentialsId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/credentialEligibilityAttained", key = "person.credentialEligibilityAttained.name", id = "credentialEligibilityAttainedId")
    private List<String> credentialEligibilityAttained;

    @Indexed
    @PropertySource(template = "person/credentialEligibilityAttainedType", key = "person.credentialEligibilityAttained.type", parse = true)
    private List<String> credentialEligibilityAttainedType;

    @Indexed
    private List<String> credentialEligibilityAttainedId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/awardAndHonor", key = "person.awardAndHonor.name", id = "awardAndHonorId")
    private List<String> awardAndHonor;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/awardAndHonorDate", key = "person.awardAndHonor.date")
    private List<String> awardAndHonorDate;

    @Indexed
    private List<String> awardAndHonorId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/adviseeOf", key = "person.adviseeOf.name", id = "adviseeOfId")
    private List<String> adviseeOf;

    @Indexed
    @PropertySource(template = "person/adviseeOfType", key = "person.adviseeOf.type", parse = true)
    private List<String> adviseeOfType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/adviseeOfCandidacy", key = "person.adviseeOf.candidacy")
    private List<String> adviseeOfCandidacy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeOfStartDate", key = "person.adviseeOf.startDate")
    private List<String> adviseeOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeOfEndDate", key = "person.adviseeOf.endDate")
    private List<String> adviseeOfEndDate;

    @Indexed
    private List<String> adviseeOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/selectedPublication", key = "person.selectedPublication.title", id = "selectedPublicationId")
    private List<String> selectedPublication;

    @Indexed
    @PropertySource(template = "person/selectedPublicationType", key = "person.selectedPublication.type", parse = true)
    private List<String> selectedPublicationType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/selectedPublicationDate", key = "person.selectedPublication.date")
    private List<String> selectedPublicationDate;

    @Indexed
    private List<String> selectedPublicationId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/collectionOrSeriesEditorFor", key = "person.collectionOrSeriesEditorFor.title", id = "collectionOrSeriesEditorForId")
    private List<String> collectionOrSeriesEditorFor;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/collectionOrSeriesEditorForRole", key = "person.collectionOrSeriesEditorFor.role")
    private List<String> collectionOrSeriesEditorForRole;

    @Indexed
    @PropertySource(template = "person/collectionOrSeriesEditorForType", key = "person.collectionOrSeriesEditorFor.type", parse = true)
    private List<String> collectionOrSeriesEditorForType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/collectionOrSeriesEditorForStartDate", key = "person.collectionOrSeriesEditorFor.startDate")
    private List<String> collectionOrSeriesEditorForStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/collectionOrSeriesEditorForEndDate", key = "person.collectionOrSeriesEditorFor.endDate")
    private List<String> collectionOrSeriesEditorForEndDate;

    @Indexed
    private List<String> collectionOrSeriesEditorForId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/editorOf", key = "person.editorOf.title", id = "editorOfId")
    private List<String> editorOf;

    @Indexed
    @PropertySource(template = "person/editorOfType", key = "person.editorOf.type", parse = true)
    private List<String> editorOfType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/editorOfPublisher", key = "person.editorOf.publisher", unique = true)
    private List<String> editorOfPublisher;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/editorOfFullAuthorList", key = "person.editorOf.fullAuthorList")
    private List<String> editorOfFullAuthorList;

    @Indexed
    @PropertySource(template = "person/editorOfPageStart", key = "person.editorOf.pageStart")
    private List<String> editorOfPageStart;

    @Indexed
    @PropertySource(template = "person/editorOfPageEnd", key = "person.editorOf.pageEnd")
    private List<String> editorOfPageEnd;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/editorOfDate", key = "person.editorOf.date")
    private List<String> editorOfDate;

    @Indexed
    private List<String> editorOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/presentation", key = "person.presentation.name", id = "presentationId")
    private List<String> presentation;

    @Indexed
    @PropertySource(template = "person/presentationType", key = "person.presentation.type", parse = true)
    private List<String> presentationType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/presentationRole", key = "person.presentation.role")
    private List<String> presentationRole;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/presentationEvent", key = "person.presentation.event", parse = true)
    private List<String> presentationEvent;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/presentationStartDate", key = "person.presentation.startDate")
    private List<String> presentationStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/presentationEndDate", key = "person.presentation.endDate")
    private List<String> presentationEndDate;

    @Indexed
    private List<String> presentationId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/featuredIn", key = "person.featuredIn.title", id = "featuredInId")
    private List<String> featuredIn;

    @Indexed
    @PropertySource(template = "person/featuredInType", key = "person.featuredIn.type", parse = true)
    private List<String> featuredInType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/featuredInDate", key = "person.featuredIn.date")
    private List<String> featuredInDate;

    @Indexed
    private List<String> featuredInId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/assigneeForPatent", key = "person.assigneeForPatent.name", id = "assigneeForPatentId")
    private List<String> assigneeForPatent;

    @Indexed
    @PropertySource(template = "person/assigneeForPatentType", key = "person.assigneeForPatent.type", parse = true)
    private List<String> assigneeForPatentType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/assigneeForPatentDate", key = "person.assigneeForPatent.date")
    private List<String> assigneeForPatentDate;

    @Indexed
    private List<String> assigneeForPatentId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/translatorOf", key = "person.translatorOf.title", id = "translatorOfId")
    private List<String> translatorOf;

    @Indexed
    @PropertySource(template = "person/translatorOfType", key = "person.translatorOf.type", parse = true)
    private List<String> translatorOfType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/translatorOfDate", key = "person.translatorOf.date")
    private List<String> translatorOfDate;

    @Indexed
    private List<String> translatorOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/researchOverview", key = "person.researchOverview")
    private String researchOverview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/principalInvestigatorOn", key = "person.principalInvestigatorOn.name", id = "principalInvestigatorOnId")
    private List<String> principalInvestigatorOn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/principalInvestigatorOnAwardedBy", key = "person.principalInvestigatorOn.awardedBy")
    private List<String> principalInvestigatorOnAwardedBy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/principalInvestigatorOnStartDate", key = "person.principalInvestigatorOn.startDate")
    private List<String> principalInvestigatorOnStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/principalInvestigatorOnEndDate", key = "person.principalInvestigatorOn.endDate")
    private List<String> principalInvestigatorOnEndDate;

    @Indexed
    private List<String> principalInvestigatorOnId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/coPrincipalInvestigatorOn", key = "person.coPrincipalInvestigatorOn.name", id = "coPrincipalInvestigatorOnId")
    private List<String> coPrincipalInvestigatorOn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/coPrincipalInvestigatorOnAwardedBy", key = "person.coPrincipalInvestigatorOn.awardedBy")
    private List<String> coPrincipalInvestigatorOnAwardedBy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/coPrincipalInvestigatorOnStartDate", key = "person.coPrincipalInvestigatorOn.startDate")
    private List<String> coPrincipalInvestigatorOnStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/coPrincipalInvestigatorOnEndDate", key = "person.coPrincipalInvestigatorOn.endDate")
    private List<String> coPrincipalInvestigatorOnEndDate;

    @Indexed
    private List<String> coPrincipalInvestigatorOnId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/investigatorOn", key = "person.investigatorOn.name", id = "investigatorOnId")
    private List<String> investigatorOn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/investigatorOnAwardedBy", key = "person.investigatorOn.awardedBy")
    private List<String> investigatorOnAwardedBy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/investigatorOnStartDate", key = "person.investigatorOn.startDate")
    private List<String> investigatorOnStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/investigatorOnEndDate", key = "person.investigatorOn.endDate")
    private List<String> investigatorOnEndDate;

    @Indexed
    private List<String> investigatorOnId;

    @Indexed
    @PropertySource(template = "person/otherResearchActivity", key = "person.otherResearchActivity.name", id = "otherResearchActivityId")
    private List<String> otherResearchActivity;

    @Indexed
    @PropertySource(template = "person/otherResearchActivityRole", key = "person.otherResearchActivity.role")
    private List<String> otherResearchActivityRole;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/otherResearchActivityStartDate", key = "person.otherResearchActivity.startDate")
    private List<String> otherResearchActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/otherResearchActivityEndDate", key = "person.otherResearchActivity.endDate")
    private List<String> otherResearchActivityEndDate;

    @Indexed
    private List<String> otherResearchActivityId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/teachingOverview", key = "person.teachingOverview")
    private String teachingOverview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/teachingActivity", key = "person.teachingActivity.name", id = "teachingActivityId")
    private List<String> teachingActivity;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/teachingActivityRole", key = "person.teachingActivity.role")
    private List<String> teachingActivityRole;

    @Indexed
    private List<String> teachingActivityId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/advisee", key = "person.advisee.name", id = "adviseeId")
    private List<String> advisee;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/adviseeCandidacy", key = "person.advisee.candidacy")
    private List<String> adviseeCandidacy;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/adviseeType", key = "person.advisee.type", parse = true)
    private List<String> adviseeType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeStartDate", key = "person.advisee.startDate")
    private List<String> adviseeStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeEndDate", key = "person.advisee.endDate")
    private List<String> adviseeEndDate;

    @Indexed
    private List<String> adviseeId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/outreachOverview", key = "person.outreachOverview")
    private String outreachOverview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/reviewerOf", key = "person.reviewerOf.title", id = "reviewerOfId")
    private List<String> reviewerOf;

    @Indexed
    @PropertySource(template = "person/reviewerOfType", key = "person.reviewerOf.type", parse = true)
    private List<String> reviewerOfType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/reviewerOfStartDate", key = "person.reviewerOf.startDate")
    private List<String> reviewerOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/reviewerOfEndDate", key = "person.reviewerOf.endDate")
    private List<String> reviewerOfEndDate;

    @Indexed
    private List<String> reviewerOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/contactOrProvidorForService", key = "person.contactOrProvidorForService.name", id = "contactOrProvidorForServiceId")
    private List<String> contactOrProvidorForService;

    @Indexed
    @PropertySource(template = "person/contactOrProvidorForServiceType", key = "person.contactOrProvidorForService.type", parse = true)
    private List<String> contactOrProvidorForServiceType;

    @Indexed
    private List<String> contactOrProvidorForServiceId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/organizerOfEvent", key = "person.organizerOfEvent.name", id = "organizerOfEventId")
    private List<String> organizerOfEvent;

    @Indexed
    @PropertySource(template = "person/organizerOfEventType", key = "person.organizerOfEvent.type", parse = true)
    private List<String> organizerOfEventType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/organizerOfEventStartDate", key = "person.organizerOfEvent.startDate")
    private List<String> organizerOfEventStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/organizerOfEventEndDate", key = "person.organizerOfEvent.endDate")
    private List<String> organizerOfEventEndDate;

    @Indexed
    private List<String> organizerOfEventId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/professionalServiceActivity", key = "person.professionalServiceActivity.name", id = "professionalServiceActivityId")
    private List<String> professionalServiceActivity;

    @Indexed
    @PropertySource(template = "person/professionalServiceActivityType", key = "person.professionalServiceActivity.type", parse = true)
    private List<String> professionalServiceActivityType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/professionalServiceActivityRole", key = "person.professionalServiceActivity.role")
    private List<String> professionalServiceActivityRole;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityStartDate", key = "person.professionalServiceActivity.startDate")
    private List<String> professionalServiceActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityEndDate", key = "person.professionalServiceActivity.endDate")
    private List<String> professionalServiceActivityEndDate;

    @Indexed
    private List<String> professionalServiceActivityId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/outreachAndCommunityServiceActivity", key = "person.outreachAndCommunityServiceActivity.name", id = "outreachAndCommunityServiceActivityId")
    private List<String> outreachAndCommunityServiceActivity;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/professionalServiceActivityRole", key = "person.outreachAndCommunityServiceActivity.role")
    private List<String> outreachAndCommunityServiceActivityRole;

    @Indexed
    @PropertySource(template = "person/professionalServiceActivityType", key = "person.outreachAndCommunityServiceActivity.type", parse = true)
    private List<String> outreachAndCommunityServiceActivityType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityStartDate", key = "person.outreachAndCommunityServiceActivity.startDate")
    private List<String> outreachAndCommunityServiceActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityEndDate", key = "person.outreachAndCommunityServiceActivity.endDate")
    private List<String> outreachAndCommunityServiceActivityEndDate;

    @Indexed
    private List<String> outreachAndCommunityServiceActivityId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/performsTechnique", key = "person.performsTechnique.name", id = "performsTechniqueId")
    private List<String> performsTechnique;

    @Indexed
    @PropertySource(template = "person/performsTechniqueType", key = "person.performsTechnique.type", parse = true)
    private List<String> performsTechniqueType;

    @Indexed
    private List<String> performsTechniqueId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/hasExpertiseInTechnique", key = "person.hasExpertiseInTechnique.name", id = "hasExpertiseInTechniqueId")
    private List<String> hasExpertiseInTechnique;

    @Indexed
    @PropertySource(template = "person/hasExpertiseInTechniqueType", key = "person.hasExpertiseInTechnique.type", parse = true)
    private List<String> hasExpertiseInTechniqueType;

    @Indexed
    private List<String> hasExpertiseInTechniqueId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/isni", key = "person.isni")
    private String isni;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/netid", key = "person.netid")
    private String netid;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/researcherId", key = "person.researcherId")
    private String researcherId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/twitter", key = "person.twitter")
    private String twitter;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/uid", key = "person.uid")
    private String uid;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/uin", key = "person.uin")
    private String uin;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/youtube", key = "person.youtube")
    private String youtube;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/sameAs", key = "person.sameAs.label", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "person/sameAsType", key = "person.sameAs.type", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/eraCommonsId", key = "person.eraCommonsId")
    private String eraCommonsId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/isiResearcherId", key = "person.isiResearcherId")
    private String isiResearcherId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/scopusId", key = "person.scopusId")
    private String scopusId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/healthCareProviderId", key = "person.healthCareProviderId")
    private String healthCareProviderId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/email", key = "person.email")
    private String email;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/firstName", key = "person.firstName")
    private String firstName;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/middleName", key = "person.middleName")
    private String middleName;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/lastName", key = "person.lastName")
    private String lastName;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/streetAddress", key = "person.address.streetAddress")
    private String streetAddress;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/locality", key = "person.address.locality")
    private String locality;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/region", key = "person.address.region")
    private String region;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/postalCode", key = "person.address.postalCode")
    private String postalCode;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/country", key = "person.address.country")
    private String country;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/geographicLocation", key = "person.geographicLocation", id = "geographicLocationId")
    private String geographicLocation;

    @Indexed
    private String geographicLocationId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/locatedInFacility", key = "person.locatedInFacility", id = "locatedInFacilityId")
    private List<String> locatedInFacility;

    @Indexed
    private List<String> locatedInFacilityId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/fax", key = "person.fax")
    private String fax;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/etdChairOf", key = "person.etdChairOf", id = "etdChairOfId")
    private List<String> etdChairOf;

    @Indexed
    private List<String> etdChairOfId;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/modTime", key = "person.modTime")
    private String modTime;

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

    public List<String> getPositionType() {
        return positionType;
    }

    public void setPositionType(List<String> positionType) {
        this.positionType = positionType;
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

    public List<String> getHeadOfType() {
        return headOfType;
    }

    public void setHeadOfType(List<String> headOfType) {
        this.headOfType = headOfType;
    }

    public List<String> getHeadOfOrganization() {
        return headOfOrganization;
    }

    public void setHeadOfOrganization(List<String> headOfOrganization) {
        this.headOfOrganization = headOfOrganization;
    }

    public List<String> getHeadOfStartDate() {
        return headOfStartDate;
    }

    public void setHeadOfStartDate(List<String> headOfStartDate) {
        this.headOfStartDate = headOfStartDate;
    }

    public List<String> getHeadOfEndDate() {
        return headOfEndDate;
    }

    public void setHeadOfEndDate(List<String> headOfEndDate) {
        this.headOfEndDate = headOfEndDate;
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

    public List<String> getMemberOfType() {
        return memberOfType;
    }

    public void setMemberOfType(List<String> memberOfType) {
        this.memberOfType = memberOfType;
    }

    public List<String> getMemberOfOrganization() {
        return memberOfOrganization;
    }

    public void setMemberOfOrganization(List<String> memberOfOrganization) {
        this.memberOfOrganization = memberOfOrganization;
    }

    public List<String> getMemberOfStartDate() {
        return memberOfStartDate;
    }

    public void setMemberOfStartDate(List<String> memberOfStartDate) {
        this.memberOfStartDate = memberOfStartDate;
    }

    public List<String> getMemberOfEndDate() {
        return memberOfEndDate;
    }

    public void setMemberOfEndDate(List<String> memberOfEndDate) {
        this.memberOfEndDate = memberOfEndDate;
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

    public List<String> getClinicalActivityType() {
        return clinicalActivityType;
    }

    public void setClinicalActivityType(List<String> clinicalActivityType) {
        this.clinicalActivityType = clinicalActivityType;
    }

    public List<String> getClinicalActivityRole() {
        return clinicalActivityRole;
    }

    public void setClinicalActivityRole(List<String> clinicalActivityRole) {
        this.clinicalActivityRole = clinicalActivityRole;
    }

    public List<String> getClinicalActivityStartDate() {
        return clinicalActivityStartDate;
    }

    public void setClinicalActivityStartDate(List<String> clinicalActivityStartDate) {
        this.clinicalActivityStartDate = clinicalActivityStartDate;
    }

    public List<String> getClinicalActivityEndDate() {
        return clinicalActivityEndDate;
    }

    public void setClinicalActivityEndDate(List<String> clinicalActivityEndDate) {
        this.clinicalActivityEndDate = clinicalActivityEndDate;
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

    public List<String> getAttendedEventType() {
        return attendedEventType;
    }

    public void setAttendedEventType(List<String> attendedEventType) {
        this.attendedEventType = attendedEventType;
    }

    public List<String> getAttendedEventStartDate() {
        return attendedEventStartDate;
    }

    public void setAttendedEventStartDate(List<String> attendedEventStartDate) {
        this.attendedEventStartDate = attendedEventStartDate;
    }

    public List<String> getAttendedEventEndDate() {
        return attendedEventEndDate;
    }

    public void setAttendedEventEndDate(List<String> attendedEventEndDate) {
        this.attendedEventEndDate = attendedEventEndDate;
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

    public List<String> getEducationAndTrainingRole() {
        return educationAndTrainingRole;
    }

    public void setEducationAndTrainingRole(List<String> educationAndTrainingRole) {
        this.educationAndTrainingRole = educationAndTrainingRole;
    }

    public List<String> getEducationAndTrainingType() {
        return educationAndTrainingType;
    }

    public void setEducationAndTrainingType(List<String> educationAndTrainingType) {
        this.educationAndTrainingType = educationAndTrainingType;
    }

    public List<String> getEducationAndTrainingStartDate() {
        return educationAndTrainingStartDate;
    }

    public void setEducationAndTrainingStartDate(List<String> educationAndTrainingStartDate) {
        this.educationAndTrainingStartDate = educationAndTrainingStartDate;
    }

    public List<String> getEducationAndTrainingEndDate() {
        return educationAndTrainingEndDate;
    }

    public void setEducationAndTrainingEndDate(List<String> educationAndTrainingEndDate) {
        this.educationAndTrainingEndDate = educationAndTrainingEndDate;
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

    public List<String> getCredentialsDateIssued() {
        return credentialsDateIssued;
    }

    public void setCredentialsDateIssued(List<String> credentialsDateIssued) {
        this.credentialsDateIssued = credentialsDateIssued;
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

    public List<String> getCredentialEligibilityAttainedType() {
        return credentialEligibilityAttainedType;
    }

    public void setCredentialEligibilityAttainedType(List<String> credentialEligibilityAttainedType) {
        this.credentialEligibilityAttainedType = credentialEligibilityAttainedType;
    }

    public List<String> getCredentialEligibilityAttainedId() {
        return credentialEligibilityAttainedId;
    }

    public void setCredentialEligibilityAttainedId(List<String> credentialEligibilityAttainedId) {
        this.credentialEligibilityAttainedId = credentialEligibilityAttainedId;
    }

    public List<String> getAwardAndHonor() {
        return awardAndHonor;
    }

    public void setAwardAndHonor(List<String> awardAndHonor) {
        this.awardAndHonor = awardAndHonor;
    }

    public List<String> getAwardAndHonorDate() {
        return awardAndHonorDate;
    }

    public void setAwardAndHonorDate(List<String> awardAndHonorDate) {
        this.awardAndHonorDate = awardAndHonorDate;
    }

    public List<String> getAwardAndHonorId() {
        return awardAndHonorId;
    }

    public void setAwardAndHonorId(List<String> awardAndHonorId) {
        this.awardAndHonorId = awardAndHonorId;
    }

    public List<String> getAdviseeOf() {
        return adviseeOf;
    }

    public void setAdviseeOf(List<String> adviseeOf) {
        this.adviseeOf = adviseeOf;
    }

    public List<String> getAdviseeOfType() {
        return adviseeOfType;
    }

    public void setAdviseeOfType(List<String> adviseeOfType) {
        this.adviseeOfType = adviseeOfType;
    }

    public List<String> getAdviseeOfCandidacy() {
        return adviseeOfCandidacy;
    }

    public void setAdviseeOfCandidacy(List<String> adviseeOfCandidacy) {
        this.adviseeOfCandidacy = adviseeOfCandidacy;
    }

    public List<String> getAdviseeOfStartDate() {
        return adviseeOfStartDate;
    }

    public void setAdviseeOfStartDate(List<String> adviseeOfStartDate) {
        this.adviseeOfStartDate = adviseeOfStartDate;
    }

    public List<String> getAdviseeOfEndDate() {
        return adviseeOfEndDate;
    }

    public void setAdviseeOfEndDate(List<String> adviseeOfEndDate) {
        this.adviseeOfEndDate = adviseeOfEndDate;
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

    public List<String> getCollectionOrSeriesEditorForRole() {
        return collectionOrSeriesEditorForRole;
    }

    public void setCollectionOrSeriesEditorForRole(List<String> collectionOrSeriesEditorForRole) {
        this.collectionOrSeriesEditorForRole = collectionOrSeriesEditorForRole;
    }

    public List<String> getCollectionOrSeriesEditorForType() {
        return collectionOrSeriesEditorForType;
    }

    public void setCollectionOrSeriesEditorForType(List<String> collectionOrSeriesEditorForType) {
        this.collectionOrSeriesEditorForType = collectionOrSeriesEditorForType;
    }

    public List<String> getCollectionOrSeriesEditorForStartDate() {
        return collectionOrSeriesEditorForStartDate;
    }

    public void setCollectionOrSeriesEditorForStartDate(List<String> collectionOrSeriesEditorForStartDate) {
        this.collectionOrSeriesEditorForStartDate = collectionOrSeriesEditorForStartDate;
    }

    public List<String> getCollectionOrSeriesEditorForEndDate() {
        return collectionOrSeriesEditorForEndDate;
    }

    public void setCollectionOrSeriesEditorForEndDate(List<String> collectionOrSeriesEditorForEndDate) {
        this.collectionOrSeriesEditorForEndDate = collectionOrSeriesEditorForEndDate;
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

    public List<String> getEditorOfPublisher() {
        return editorOfPublisher;
    }

    public void setEditorOfPublisher(List<String> editorOfPublisher) {
        this.editorOfPublisher = editorOfPublisher;
    }

    public List<String> getEditorOfFullAuthorList() {
        return editorOfFullAuthorList;
    }

    public void setEditorOfFullAuthorList(List<String> editorOfFullAuthorList) {
        this.editorOfFullAuthorList = editorOfFullAuthorList;
    }

    public List<String> getEditorOfPageStart() {
        return editorOfPageStart;
    }

    public void setEditorOfPageStart(List<String> editorOfPageStart) {
        this.editorOfPageStart = editorOfPageStart;
    }

    public List<String> getEditorOfPageEnd() {
        return editorOfPageEnd;
    }

    public void setEditorOfPageEnd(List<String> editorOfPageEnd) {
        this.editorOfPageEnd = editorOfPageEnd;
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

    public List<String> getPresentationType() {
        return presentationType;
    }

    public void setPresentationType(List<String> presentationType) {
        this.presentationType = presentationType;
    }

    public List<String> getPresentationRole() {
        return presentationRole;
    }

    public void setPresentationRole(List<String> presentationRole) {
        this.presentationRole = presentationRole;
    }

    public List<String> getPresentationEvent() {
        return presentationEvent;
    }

    public void setPresentationEvent(List<String> presentationEvent) {
        this.presentationEvent = presentationEvent;
    }

    public List<String> getPresentationStartDate() {
        return presentationStartDate;
    }

    public void setPresentationStartDate(List<String> presentationStartDate) {
        this.presentationStartDate = presentationStartDate;
    }

    public List<String> getPresentationEndDate() {
        return presentationEndDate;
    }

    public void setPresentationEndDate(List<String> presentationEndDate) {
        this.presentationEndDate = presentationEndDate;
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

    public List<String> getAssigneeForPatentType() {
        return assigneeForPatentType;
    }

    public void setAssigneeForPatentType(List<String> assigneeForPatentType) {
        this.assigneeForPatentType = assigneeForPatentType;
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

    public List<String> getPrincipalInvestigatorOnStartDate() {
        return principalInvestigatorOnStartDate;
    }

    public void setPrincipalInvestigatorOnStartDate(List<String> principalInvestigatorOnStartDate) {
        this.principalInvestigatorOnStartDate = principalInvestigatorOnStartDate;
    }

    public List<String> getPrincipalInvestigatorOnEndDate() {
        return principalInvestigatorOnEndDate;
    }

    public void setPrincipalInvestigatorOnEndDate(List<String> principalInvestigatorOnEndDate) {
        this.principalInvestigatorOnEndDate = principalInvestigatorOnEndDate;
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

    public List<String> getCoPrincipalInvestigatorOnStartDate() {
        return coPrincipalInvestigatorOnStartDate;
    }

    public void setCoPrincipalInvestigatorOnStartDate(List<String> coPrincipalInvestigatorOnStartDate) {
        this.coPrincipalInvestigatorOnStartDate = coPrincipalInvestigatorOnStartDate;
    }

    public List<String> getCoPrincipalInvestigatorOnEndDate() {
        return coPrincipalInvestigatorOnEndDate;
    }

    public void setCoPrincipalInvestigatorOnEndDate(List<String> coPrincipalInvestigatorOnEndDate) {
        this.coPrincipalInvestigatorOnEndDate = coPrincipalInvestigatorOnEndDate;
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

    public List<String> getInvestigatorOnStartDate() {
        return investigatorOnStartDate;
    }

    public void setInvestigatorOnStartDate(List<String> investigatorOnStartDate) {
        this.investigatorOnStartDate = investigatorOnStartDate;
    }

    public List<String> getInvestigatorOnEndDate() {
        return investigatorOnEndDate;
    }

    public void setInvestigatorOnEndDate(List<String> investigatorOnEndDate) {
        this.investigatorOnEndDate = investigatorOnEndDate;
    }

    public List<String> getInvestigatorOnId() {
        return investigatorOnId;
    }

    public void setInvestigatorOnId(List<String> investigatorOnId) {
        this.investigatorOnId = investigatorOnId;
    }

    public List<String> getOtherResearchActivity() {
        return otherResearchActivity;
    }

    public void setOtherResearchActivity(List<String> otherResearchActivity) {
        this.otherResearchActivity = otherResearchActivity;
    }

    public List<String> getOtherResearchActivityRole() {
        return otherResearchActivityRole;
    }

    public void setOtherResearchActivityRole(List<String> otherResearchActivityRole) {
        this.otherResearchActivityRole = otherResearchActivityRole;
    }

    public List<String> getOtherResearchActivityStartDate() {
        return otherResearchActivityStartDate;
    }

    public void setOtherResearchActivityStartDate(List<String> otherResearchActivityStartDate) {
        this.otherResearchActivityStartDate = otherResearchActivityStartDate;
    }

    public List<String> getOtherResearchActivityEndDate() {
        return otherResearchActivityEndDate;
    }

    public void setOtherResearchActivityEndDate(List<String> otherResearchActivityEndDate) {
        this.otherResearchActivityEndDate = otherResearchActivityEndDate;
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

    public List<String> getTeachingActivityRole() {
        return teachingActivityRole;
    }

    public void setTeachingActivityRole(List<String> teachingActivityRole) {
        this.teachingActivityRole = teachingActivityRole;
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

    public List<String> getAdviseeCandidacy() {
        return adviseeCandidacy;
    }

    public void setAdviseeCandidacy(List<String> adviseeCandidacy) {
        this.adviseeCandidacy = adviseeCandidacy;
    }

    public List<String> getAdviseeType() {
        return adviseeType;
    }

    public void setAdviseeType(List<String> adviseeType) {
        this.adviseeType = adviseeType;
    }

    public List<String> getAdviseeStartDate() {
        return adviseeStartDate;
    }

    public void setAdviseeStartDate(List<String> adviseeStartDate) {
        this.adviseeStartDate = adviseeStartDate;
    }

    public List<String> getAdviseeEndDate() {
        return adviseeEndDate;
    }

    public void setAdviseeEndDate(List<String> adviseeEndDate) {
        this.adviseeEndDate = adviseeEndDate;
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

    public List<String> getReviewerOfStartDate() {
        return reviewerOfStartDate;
    }

    public void setReviewerOfStartDate(List<String> reviewerOfStartDate) {
        this.reviewerOfStartDate = reviewerOfStartDate;
    }

    public List<String> getReviewerOfEndDate() {
        return reviewerOfEndDate;
    }

    public void setReviewerOfEndDate(List<String> reviewerOfEndDate) {
        this.reviewerOfEndDate = reviewerOfEndDate;
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

    public List<String> getContactOrProvidorForServiceType() {
        return contactOrProvidorForServiceType;
    }

    public void setContactOrProvidorForServiceType(List<String> contactOrProvidorForServiceType) {
        this.contactOrProvidorForServiceType = contactOrProvidorForServiceType;
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

    public List<String> getOrganizerOfEventType() {
        return organizerOfEventType;
    }

    public void setOrganizerOfEventType(List<String> organizerOfEventType) {
        this.organizerOfEventType = organizerOfEventType;
    }

    public List<String> getOrganizerOfEventStartDate() {
        return organizerOfEventStartDate;
    }

    public void setOrganizerOfEventStartDate(List<String> organizerOfEventStartDate) {
        this.organizerOfEventStartDate = organizerOfEventStartDate;
    }

    public List<String> getOrganizerOfEventEndDate() {
        return organizerOfEventEndDate;
    }

    public void setOrganizerOfEventEndDate(List<String> organizerOfEventEndDate) {
        this.organizerOfEventEndDate = organizerOfEventEndDate;
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

    public List<String> getProfessionalServiceActivityType() {
        return professionalServiceActivityType;
    }

    public void setProfessionalServiceActivityType(List<String> professionalServiceActivityType) {
        this.professionalServiceActivityType = professionalServiceActivityType;
    }

    public List<String> getProfessionalServiceActivityRole() {
        return professionalServiceActivityRole;
    }

    public void setProfessionalServiceActivityRole(List<String> professionalServiceActivityRole) {
        this.professionalServiceActivityRole = professionalServiceActivityRole;
    }

    public List<String> getProfessionalServiceActivityStartDate() {
        return professionalServiceActivityStartDate;
    }

    public void setProfessionalServiceActivityStartDate(List<String> professionalServiceActivityStartDate) {
        this.professionalServiceActivityStartDate = professionalServiceActivityStartDate;
    }

    public List<String> getProfessionalServiceActivityEndDate() {
        return professionalServiceActivityEndDate;
    }

    public void setProfessionalServiceActivityEndDate(List<String> professionalServiceActivityEndDate) {
        this.professionalServiceActivityEndDate = professionalServiceActivityEndDate;
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

    public List<String> getOutreachAndCommunityServiceActivityRole() {
        return outreachAndCommunityServiceActivityRole;
    }

    public void setOutreachAndCommunityServiceActivityRole(List<String> outreachAndCommunityServiceActivityRole) {
        this.outreachAndCommunityServiceActivityRole = outreachAndCommunityServiceActivityRole;
    }

    public List<String> getOutreachAndCommunityServiceActivityType() {
        return outreachAndCommunityServiceActivityType;
    }

    public void setOutreachAndCommunityServiceActivityType(List<String> outreachAndCommunityServiceActivityType) {
        this.outreachAndCommunityServiceActivityType = outreachAndCommunityServiceActivityType;
    }

    public List<String> getOutreachAndCommunityServiceActivityStartDate() {
        return outreachAndCommunityServiceActivityStartDate;
    }

    public void setOutreachAndCommunityServiceActivityStartDate(List<String> outreachAndCommunityServiceActivityStartDate) {
        this.outreachAndCommunityServiceActivityStartDate = outreachAndCommunityServiceActivityStartDate;
    }

    public List<String> getOutreachAndCommunityServiceActivityEndDate() {
        return outreachAndCommunityServiceActivityEndDate;
    }

    public void setOutreachAndCommunityServiceActivityEndDate(List<String> outreachAndCommunityServiceActivityEndDate) {
        this.outreachAndCommunityServiceActivityEndDate = outreachAndCommunityServiceActivityEndDate;
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

    public List<String> getPerformsTechniqueType() {
        return performsTechniqueType;
    }

    public void setPerformsTechniqueType(List<String> performsTechniqueType) {
        this.performsTechniqueType = performsTechniqueType;
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

    public List<String> getHasExpertiseInTechniqueType() {
        return hasExpertiseInTechniqueType;
    }

    public void setHasExpertiseInTechniqueType(List<String> hasExpertiseInTechniqueType) {
        this.hasExpertiseInTechniqueType = hasExpertiseInTechniqueType;
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

    public String getEraCommonsId() {
        return eraCommonsId;
    }

    public void setEraCommonsId(String eraCommonsId) {
        this.eraCommonsId = eraCommonsId;
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

    public List<String> getEtdChairOfId() {
        return etdChairOfId;
    }

    public void setEtdChairOfId(List<String> etdChairOfId) {
        this.etdChairOfId = etdChairOfId;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

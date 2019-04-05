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
@CollectionSource(predicate = "http://xmlns.com/foaf/0.1/Person")
public class Person extends AbstractSolrDocument {

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/name", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String name;

    @Indexed
    @PropertySource(template = "person/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "person/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed
    @PropertySource(template = "person/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Indexed
    @PropertySource(template = "person/primaryEmail", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private String primaryEmail;

    @Indexed
    @PropertySource(template = "person/additionalEmail", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private List<String> additionalEmail;

    @Indexed
    @PropertySource(template = "person/phone", predicate = "http://www.w3.org/2006/vcard/ns#telephone")
    private String phone;

    @Indexed
    @PropertySource(template = "person/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "person/websiteLabel", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> websiteLabel;

    @Indexed
    @PropertySource(template = "person/orcidId", predicate = "http://vivoweb.org/ontology/core#orcidId", parse = true)
    private String orcidId;

    @Indexed
    @PropertySource(template = "person/preferredTitle", predicate = "http://www.w3.org/2006/vcard/ns#title")
    private String preferredTitle;

    @Indexed
    @PropertySource(template = "person/position", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "positionId")
    private List<String> position;

    @Indexed
    @PropertySource(template = "person/positionType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> positionType;

    @Indexed
    @PropertySource(template = "person/positionOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> positionOrganization;

    @Indexed
    private List<String> positionId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/overview", predicate = "http://vivoweb.org/ontology/core#overview")
    private String overview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/researchArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "researchAreaId")
    private List<String> researchArea;

    @Indexed
    private List<String> researchAreaId;

    @Indexed
    @PropertySource(template = "person/geographicFocus", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "geographicFocusId")
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "person/geographicFocusType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed
    @PropertySource(template = "person/hrJobTitle", predicate = "http://vivoweb.org/ontology/core#hrJobTitle")
    private String hrJobTitle;

    @Indexed
    @PropertySource(template = "person/keyword", predicate = "http://vivoweb.org/ontology/core#freetextKeyword")
    private List<String> keyword;

    @Indexed
    @PropertySource(template = "person/headOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "headOfId")
    private List<String> headOf;

    @Indexed
    @PropertySource(template = "person/headOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> headOfType;

    @Indexed
    @PropertySource(template = "person/headOfOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> headOfOrganization;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/headOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> headOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/headOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> headOfEndDate;

    @Indexed
    private List<String> headOfId;

    @Indexed
    @PropertySource(template = "person/memberOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "memberOfId")
    private List<String> memberOf;

    @Indexed
    @PropertySource(template = "person/memberOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> memberOfType;

    @Indexed
    @PropertySource(template = "person/memberOfOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> memberOfOrganization;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/memberOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> memberOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/memberOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> memberOfEndDate;

    @Indexed
    private List<String> memberOfId;

    @Indexed
    @PropertySource(template = "person/hasCollaborator", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "hasCollaboratorId")
    private List<String> hasCollaborator;

    @Indexed
    private List<String> hasCollaboratorId;

    @Indexed
    @PropertySource(template = "person/clinicalActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "clinicalActivityId")
    private List<String> clinicalActivity;

    @Indexed
    @PropertySource(template = "person/clinicalActivityType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> clinicalActivityType;

    @Indexed
    @PropertySource(template = "person/clinicalActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> clinicalActivityRole;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/clinicalActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> clinicalActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/clinicalActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> clinicalActivityEndDate;

    @Indexed
    private List<String> clinicalActivityId;

    @Indexed
    @PropertySource(template = "person/attendedEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "attendedEventId")
    private List<String> attendedEvent;

    @Indexed
    @PropertySource(template = "person/attendedEventType", predicate = "person.attendedEvent.name", parse = true)
    private List<String> attendedEventType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/attendedEventStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> attendedEventStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/attendedEventEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> attendedEventEndDate;

    @Indexed
    private List<String> attendedEventId;

    @Indexed
    @PropertySource(template = "person/educationAndTraining", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "educationAndTrainingId")
    private List<String> educationAndTraining;

    @Indexed
    @PropertySource(template = "person/educationAndTrainingRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> educationAndTrainingRole;

    @Indexed
    @PropertySource(template = "person/educationAndTrainingType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> educationAndTrainingType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/educationAndTrainingStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> educationAndTrainingStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/educationAndTrainingEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> educationAndTrainingEndDate;

    @Indexed
    private List<String> educationAndTrainingId;

    @Indexed
    @PropertySource(template = "person/credentials", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "credentialsId")
    private List<String> credentials;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/credentialsDateIssued", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> credentialsDateIssued;

    @Indexed
    private List<String> credentialsId;

    @Indexed
    @PropertySource(template = "person/credentialEligibilityAttained", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "credentialEligibilityAttainedId")
    private List<String> credentialEligibilityAttained;

    @Indexed
    @PropertySource(template = "person/credentialEligibilityAttainedType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> credentialEligibilityAttainedType;

    @Indexed
    private List<String> credentialEligibilityAttainedId;

    @Indexed
    @PropertySource(template = "person/awardAndHonor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "awardAndHonorId")
    private List<String> awardAndHonor;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/awardAndHonorDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardAndHonorDate;

    @Indexed
    private List<String> awardAndHonorId;

    @Indexed
    @PropertySource(template = "person/adviseeOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "adviseeOfId")
    private List<String> adviseeOf;

    @Indexed
    @PropertySource(template = "person/adviseeOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> adviseeOfType;

    @Indexed
    @PropertySource(template = "person/adviseeOfCandidacy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> adviseeOfCandidacy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeOfEndDate;

    @Indexed
    private List<String> adviseeOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/selectedPublication", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "selectedPublicationId")
    private List<String> selectedPublication;

    @Indexed
    @PropertySource(template = "person/selectedPublicationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> selectedPublicationType;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/selectedPublicationVenue", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> selectedPublicationVenue;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/selectedPublicationDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> selectedPublicationDate;

    @Indexed
    private List<String> selectedPublicationId;

    @Indexed
    @PropertySource(template = "person/collectionOrSeriesEditorFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "collectionOrSeriesEditorForId")
    private List<String> collectionOrSeriesEditorFor;

    @Indexed
    @PropertySource(template = "person/collectionOrSeriesEditorForRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> collectionOrSeriesEditorForRole;

    @Indexed
    @PropertySource(template = "person/collectionOrSeriesEditorForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> collectionOrSeriesEditorForType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/collectionOrSeriesEditorForStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> collectionOrSeriesEditorForStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/collectionOrSeriesEditorForEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> collectionOrSeriesEditorForEndDate;

    @Indexed
    private List<String> collectionOrSeriesEditorForId;

    @Indexed
    @PropertySource(template = "person/editorOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "editorOfId")
    private List<String> editorOf;

    @Indexed
    @PropertySource(template = "person/editorOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> editorOfType;

    @Indexed
    @PropertySource(template = "person/editorOfPublisher", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private List<String> editorOfPublisher;

    @Indexed
    @PropertySource(template = "person/editorOfFullAuthorList", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#fullAuthorList")
    private List<String> editorOfFullAuthorList;

    @Indexed
    @PropertySource(template = "person/editorOfPageStart", predicate = "http://purl.org/ontology/bibo/pageStart")
    private List<String> editorOfPageStart;

    @Indexed
    @PropertySource(template = "person/editorOfPageEnd", predicate = "http://purl.org/ontology/bibo/pageEnd")
    private List<String> editorOfPageEnd;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/editorOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> editorOfDate;

    @Indexed
    private List<String> editorOfId;

    @Indexed
    @PropertySource(template = "person/presentation", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "presentationId")
    private List<String> presentation;

    @Indexed
    @PropertySource(template = "person/presentationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> presentationType;

    @Indexed
    @PropertySource(template = "person/presentationRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> presentationRole;

    @Indexed
    @PropertySource(template = "person/presentationEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", parse = true)
    private List<String> presentationEvent;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/presentationStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> presentationStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/presentationEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> presentationEndDate;

    @Indexed
    private List<String> presentationId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/featuredIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "featuredInId")
    private List<String> featuredIn;

    @Indexed
    @PropertySource(template = "person/featuredInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> featuredInType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/featuredInDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> featuredInDate;

    @Indexed
    private List<String> featuredInId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/assigneeForPatent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "assigneeForPatentId")
    private List<String> assigneeForPatent;

    @Indexed
    @PropertySource(template = "person/assigneeForPatentType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> assigneeForPatentType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/assigneeForPatentDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> assigneeForPatentDate;

    @Indexed
    private List<String> assigneeForPatentId;

    @Indexed
    @PropertySource(template = "person/translatorOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "translatorOfId")
    private List<String> translatorOf;

    @Indexed
    @PropertySource(template = "person/translatorOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> translatorOfType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/translatorOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> translatorOfDate;

    @Indexed
    private List<String> translatorOfId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/researchOverview", predicate = "http://vivoweb.org/ontology/core#researchOverview")
    private String researchOverview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/principalInvestigatorOn", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "principalInvestigatorOnId")
    private List<String> principalInvestigatorOn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/principalInvestigatorOnAwardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> principalInvestigatorOnAwardedBy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/principalInvestigatorOnStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> principalInvestigatorOnStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/principalInvestigatorOnEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> principalInvestigatorOnEndDate;

    @Indexed
    private List<String> principalInvestigatorOnId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/coPrincipalInvestigatorOn", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "coPrincipalInvestigatorOnId")
    private List<String> coPrincipalInvestigatorOn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/coPrincipalInvestigatorOnAwardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> coPrincipalInvestigatorOnAwardedBy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/coPrincipalInvestigatorOnStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> coPrincipalInvestigatorOnStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/coPrincipalInvestigatorOnEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> coPrincipalInvestigatorOnEndDate;

    @Indexed
    private List<String> coPrincipalInvestigatorOnId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/investigatorOn", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "investigatorOnId")
    private List<String> investigatorOn;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/investigatorOnAwardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> investigatorOnAwardedBy;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/investigatorOnStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> investigatorOnStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/investigatorOnEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> investigatorOnEndDate;

    @Indexed
    private List<String> investigatorOnId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/otherResearchActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "otherResearchActivityId")
    private List<String> otherResearchActivity;

    @Indexed
    @PropertySource(template = "person/otherResearchActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> otherResearchActivityRole;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/otherResearchActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> otherResearchActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/otherResearchActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> otherResearchActivityEndDate;

    @Indexed
    private List<String> otherResearchActivityId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/teachingOverview", predicate = "http://vivoweb.org/ontology/core#teachingOverview")
    private String teachingOverview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/teachingActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "teachingActivityId")
    private List<String> teachingActivity;

    @Indexed
    @PropertySource(template = "person/teachingActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> teachingActivityRole;

    @Indexed
    private List<String> teachingActivityId;

    @Indexed
    @PropertySource(template = "person/advisee", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "adviseeId")
    private List<String> advisee;

    @Indexed
    @PropertySource(template = "person/adviseeCandidacy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> adviseeCandidacy;

    @Indexed
    @PropertySource(template = "person/adviseeType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> adviseeType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/adviseeEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeEndDate;

    @Indexed
    private List<String> adviseeId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/outreachOverview", predicate = "http://vivoweb.org/ontology/core#outreachOverview")
    private String outreachOverview;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/reviewerOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "reviewerOfId")
    private List<String> reviewerOf;

    @Indexed
    @PropertySource(template = "person/reviewerOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> reviewerOfType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/reviewerOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> reviewerOfStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/reviewerOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> reviewerOfEndDate;

    @Indexed
    private List<String> reviewerOfId;

    @Indexed
    @PropertySource(template = "person/contactOrProvidorForService", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "contactOrProvidorForServiceId")
    private List<String> contactOrProvidorForService;

    @Indexed
    @PropertySource(template = "person/contactOrProvidorForServiceType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> contactOrProvidorForServiceType;

    @Indexed
    private List<String> contactOrProvidorForServiceId;

    @Indexed
    @PropertySource(template = "person/organizerOfEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "organizerOfEventId")
    private List<String> organizerOfEvent;

    @Indexed
    @PropertySource(template = "person/organizerOfEventType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> organizerOfEventType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/organizerOfEventStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> organizerOfEventStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/organizerOfEventEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> organizerOfEventEndDate;

    @Indexed
    private List<String> organizerOfEventId;

    @Indexed
    @PropertySource(template = "person/professionalServiceActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "professionalServiceActivityId")
    private List<String> professionalServiceActivity;

    @Indexed
    @PropertySource(template = "person/professionalServiceActivityType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> professionalServiceActivityType;

    @Indexed
    @PropertySource(template = "person/professionalServiceActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> professionalServiceActivityRole;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> professionalServiceActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> professionalServiceActivityEndDate;

    @Indexed
    private List<String> professionalServiceActivityId;

    @Indexed
    @PropertySource(template = "person/outreachAndCommunityServiceActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "outreachAndCommunityServiceActivityId")
    private List<String> outreachAndCommunityServiceActivity;

    @Indexed
    @PropertySource(template = "person/professionalServiceActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> outreachAndCommunityServiceActivityRole;

    @Indexed
    @PropertySource(template = "person/professionalServiceActivityType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> outreachAndCommunityServiceActivityType;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> outreachAndCommunityServiceActivityStartDate;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/professionalServiceActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> outreachAndCommunityServiceActivityEndDate;

    @Indexed
    private List<String> outreachAndCommunityServiceActivityId;

    @Indexed
    @PropertySource(template = "person/performsTechnique", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "performsTechniqueId")
    private List<String> performsTechnique;

    @Indexed
    @PropertySource(template = "person/performsTechniqueType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> performsTechniqueType;

    @Indexed
    private List<String> performsTechniqueId;

    @Indexed
    @PropertySource(template = "person/hasExpertiseInTechnique", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "hasExpertiseInTechniqueId")
    private List<String> hasExpertiseInTechnique;

    @Indexed
    @PropertySource(template = "person/hasExpertiseInTechniqueType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasExpertiseInTechniqueType;

    @Indexed
    private List<String> hasExpertiseInTechniqueId;

    @Indexed
    @PropertySource(template = "person/isni", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#ISNI")
    private String isni;

    @Indexed
    @PropertySource(template = "person/netid", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#NETID")
    private String netid;

    @Indexed
    @PropertySource(template = "person/researcherId", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#ResearcherId")
    private String researcherId;

    @Indexed
    @PropertySource(template = "person/twitter", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#twitterId")
    private String twitter;

    @Indexed
    @PropertySource(template = "person/uid", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#UID")
    private String uid;

    @Indexed
    @PropertySource(template = "person/uin", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#UIN")
    private String uin;

    @Indexed
    @PropertySource(template = "person/youtube", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#youtube")
    private String youtube;

    @Indexed
    @PropertySource(template = "person/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "person/sameAsType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed
    @PropertySource(template = "person/eraCommonsId", predicate = "http://vivoweb.org/ontology/core#eRACommonsId")
    private String eraCommonsId;

    @Indexed
    @PropertySource(template = "person/isiResearcherId", predicate = "http://vivoweb.org/ontology/core#researcherId")
    private String isiResearcherId;

    @Indexed
    @PropertySource(template = "person/scopusId", predicate = "http://vivoweb.org/ontology/core#scopusId")
    private String scopusId;

    @Indexed
    @PropertySource(template = "person/healthCareProviderId", predicate = "http://purl.obolibrary.org/obo/ARG_0000197")
    private String healthCareProviderId;

    @Indexed
    @PropertySource(template = "person/email", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private String email;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/firstName", predicate = "http://www.w3.org/2006/vcard/ns#givenName")
    private String firstName;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/middleName", predicate = "http://www.w3.org/2006/vcard/ns#middleName")
    private String middleName;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/lastName", predicate = "http://www.w3.org/2006/vcard/ns#familyName")
    private String lastName;

    @Indexed
    @PropertySource(template = "person/streetAddress", predicate = "http://www.w3.org/2006/vcard/ns#streetAddress")
    private String streetAddress;

    @Indexed
    @PropertySource(template = "person/locality", predicate = "http://www.w3.org/2006/vcard/ns#locality")
    private String locality;

    @Indexed
    @PropertySource(template = "person/region", predicate = "http://www.w3.org/2006/vcard/ns#region")
    private String region;

    @Indexed
    @PropertySource(template = "person/postalCode", predicate = "http://www.w3.org/2006/vcard/ns#postalCode")
    private String postalCode;

    @Indexed
    @PropertySource(template = "person/country", predicate = "http://www.w3.org/2006/vcard/ns#country")
    private String country;

    @Indexed
    @PropertySource(template = "person/geographicLocation", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "geographicLocationId")
    private String geographicLocation;

    @Indexed
    private String geographicLocationId;

    @Indexed
    @PropertySource(template = "person/locatedInFacility", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "locatedInFacilityId")
    private List<String> locatedInFacility;

    @Indexed
    private List<String> locatedInFacilityId;

    @Indexed
    @PropertySource(template = "person/fax", predicate = "http://www.w3.org/2006/vcard/ns#fax")
    private String fax;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "person/etdChairOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "etdChairOfId")
    private List<String> etdChairOf;

    @Indexed
    private List<String> etdChairOfId;

    @Indexed(type = "pdate")
    @PropertySource(template = "person/modTime", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#modTime")
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

    public List<String> getSelectedPublicationVenue() {
        return selectedPublicationVenue;
    }

    public void setSelectedPublicationVenue(List<String> selectedPublicationVenue) {
        this.selectedPublicationVenue = selectedPublicationVenue;
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

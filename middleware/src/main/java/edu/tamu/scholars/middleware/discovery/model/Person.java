package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.NestedMultiValuedProperty;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject;
import edu.tamu.scholars.middleware.discovery.annotation.NestedObject.Reference;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "persons")
@CollectionSource(predicate = "http://xmlns.com/foaf/0.1/Person")
public class Person extends AbstractSolrDocument {

    @Indexed(type = "sorting_string", copyTo = "_text_")
    @PropertySource(template = "person/name", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String name;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "person/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/primaryEmail", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private String primaryEmail;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "person/additionalEmail", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private List<String> additionalEmail;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/phone", predicate = "http://www.w3.org/2006/vcard/ns#telephone")
    private String phone;

    @Indexed(type = "whole_strings")
    @NestedObject({ @Reference(value = "websiteUrl", key = "url") })
    @PropertySource(template = "person/website", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> website;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "person/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/orcidId", predicate = "http://vivoweb.org/ontology/core#orcidId", parse = true)
    private String orcidId;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/preferredTitle", predicate = "http://www.w3.org/2006/vcard/ns#title")
    private String preferredTitle;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "positionType", key = "type"), @Reference(value = "positionOrganization", key = "organization") })
    @PropertySource(template = "person/position", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> position;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/positionType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> positionType;

    @NestedMultiValuedProperty
    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/positionOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> positionOrganization;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "person/overview", predicate = "http://vivoweb.org/ontology/core#overview")
    private String overview;

    @NestedObject
    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "person/researchArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> researchArea;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "geographicFocusType", key = "type") })
    @PropertySource(template = "person/geographicFocus", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> geographicFocus;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/geographicFocusType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> geographicFocusType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/hrJobTitle", predicate = "http://vivoweb.org/ontology/core#hrJobTitle")
    private String hrJobTitle;

    @Indexed(type = "delimited_strings")
    @PropertySource(template = "person/keyword", predicate = "http://vivoweb.org/ontology/core#freetextKeyword")
    private List<String> keyword;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "headOfType", key = "type"), @Reference(value = "headOfOrganization", key = "organization"), @Reference(value = "headOfStartDate", key = "startDate"), @Reference(value = "headOfEndDate", key = "endDate") })
    @PropertySource(template = "person/headOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> headOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/headOfOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> headOfOrganization;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/headOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> headOfStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/headOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> headOfEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "memberOfType", key = "type"), @Reference(value = "memberOfOrganization", key = "organization"), @Reference(value = "memberOfStartDate", key = "startDate"), @Reference(value = "memberOfEndDate", key = "endDate") })
    @PropertySource(template = "person/memberOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> memberOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/memberOfOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> memberOfOrganization;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/memberOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> memberOfStartDate;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/memberOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> memberOfEndDate;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/hasCollaborator", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasCollaborator;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "clinicalActivityType", key = "type"), @Reference(value = "clinicalActivityRole", key = "role"), @Reference(value = "clinicalActivityStartDate", key = "startDate"), @Reference(value = "clinicalActivityEndDate", key = "endDate") })
    @PropertySource(template = "person/clinicalActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> clinicalActivity;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/clinicalActivityType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> clinicalActivityType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/clinicalActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> clinicalActivityRole;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/clinicalActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> clinicalActivityStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/clinicalActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> clinicalActivityEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "attendedEventType", key = "type"), @Reference(value = "attendedEventStartDate", key = "startDate"), @Reference(value = "attendedEventEndDate", key = "endDate") })
    @PropertySource(template = "person/attendedEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> attendedEvent;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/attendedEventType", predicate = "person.attendedEvent.name", parse = true)
    private List<String> attendedEventType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/attendedEventStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> attendedEventStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/attendedEventEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> attendedEventEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "educationAndTrainingOrganization", key = "organization"), @Reference(value = "educationAndTrainingMajorField", key = "field"), @Reference(value = "educationAndTrainingDegreeAbbreviation", key = "abbreviation"), @Reference(value = "educationAndTrainingStartDate", key = "startDate"), @Reference(value = "educationAndTrainingEndDate", key = "endDate") })
    @PropertySource(template = "person/educationAndTraining", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> educationAndTraining;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/educationAndTrainingOrganization", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> educationAndTrainingOrganization;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/educationAndTrainingMajorField", predicate = "http://vivoweb.org/ontology/core#majorField")
    private List<String> educationAndTrainingMajorField;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/educationAndTrainingDegreeAbbreviation", predicate = "http://vivoweb.org/ontology/core#abbreviation")
    private List<String> educationAndTrainingDegreeAbbreviation;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/educationAndTrainingStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> educationAndTrainingStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/educationAndTrainingEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> educationAndTrainingEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "credentialsDateIssued", key = "dateIssued") })
    @PropertySource(template = "person/credentials", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> credentials;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/credentialsDateIssued", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> credentialsDateIssued;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "credentialEligibilityAttainedType", key = "type") })
    @PropertySource(template = "person/credentialEligibilityAttained", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> credentialEligibilityAttained;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/credentialEligibilityAttainedType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> credentialEligibilityAttainedType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "awardAndHonorDate", key = "date") })
    @PropertySource(template = "person/awardAndHonor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> awardAndHonor;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/awardAndHonorDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> awardAndHonorDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "adviseeOfType", key = "type"), @Reference(value = "adviseeOfCandidacy", key = "candidacy"), @Reference(value = "adviseeOfStartDate", key = "startDate"), @Reference(value = "adviseeOfEndDate", key = "endDate") })
    @PropertySource(template = "person/adviseeOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> adviseeOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/adviseeOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> adviseeOfType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/adviseeOfCandidacy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> adviseeOfCandidacy;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/adviseeOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeOfStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/adviseeOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeOfEndDate;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "selectedPublicationType", key = "type"), @Reference(value = "selectedPublicationPublisher", key = "publisher"), @Reference(value = "selectedPublicationVenue", key = "venue"), @Reference(value = "selectedPublicationDate", key = "date") })
    @PropertySource(template = "person/selectedPublication", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> selectedPublication;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/selectedPublicationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> selectedPublicationType;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "person/selectedPublicationPublisher", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private List<String> selectedPublicationPublisher;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "person/selectedPublicationVenue", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private List<String> selectedPublicationVenue;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/selectedPublicationDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> selectedPublicationDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "collectionOrSeriesEditorForType", key = "type"), @Reference(value = "collectionOrSeriesEditorForRole", key = "role"), @Reference(value = "collectionOrSeriesEditorForStartDate", key = "startDate"), @Reference(value = "collectionOrSeriesEditorForEndDate", key = "endDate") })
    @PropertySource(template = "person/collectionOrSeriesEditorFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> collectionOrSeriesEditorFor;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/collectionOrSeriesEditorForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> collectionOrSeriesEditorForType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/collectionOrSeriesEditorForRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> collectionOrSeriesEditorForRole;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/collectionOrSeriesEditorForStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> collectionOrSeriesEditorForStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/collectionOrSeriesEditorForEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> collectionOrSeriesEditorForEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "editorOfType", key = "type"), @Reference(value = "editorOfPublisher", key = "publisher"), @Reference(value = "editorOfFullAuthorList", key = "authors"), @Reference(value = "editorOfPageStart", key = "pageStart"), @Reference(value = "editorOfPageEnd", key = "pageEnd"), @Reference(value = "editorOfDate", key = "date") })
    @PropertySource(template = "person/editorOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> editorOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/editorOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> editorOfType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/editorOfPublisher", predicate = "http://www.w3.org/2000/01/rdf-schema#label", unique = true)
    private List<String> editorOfPublisher;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/editorOfFullAuthorList", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#fullAuthorList")
    private List<String> editorOfFullAuthorList;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/editorOfPageStart", predicate = "http://purl.org/ontology/bibo/pageStart")
    private List<String> editorOfPageStart;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/editorOfPageEnd", predicate = "http://purl.org/ontology/bibo/pageEnd")
    private List<String> editorOfPageEnd;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/editorOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> editorOfDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "presentationType", key = "type"), @Reference(value = "presentationRole", key = "role"), @Reference(value = "presentationEvent", key = "event"), @Reference(value = "presentationStartDate", key = "startDate"), @Reference(value = "presentationEndDate", key = "endDate") })
    @PropertySource(template = "person/presentation", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> presentation;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/presentationType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> presentationType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/presentationRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> presentationRole;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/presentationEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", parse = true)
    private List<String> presentationEvent;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/presentationStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> presentationStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/presentationEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> presentationEndDate;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "featuredInType", key = "type"), @Reference(value = "featuredInDate", key = "date") })
    @PropertySource(template = "person/featuredIn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> featuredIn;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/featuredInType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> featuredInType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/featuredInDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> featuredInDate;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "assigneeForPatentType", key = "type"), @Reference(value = "assigneeForPatentDate", key = "date") })
    @PropertySource(template = "person/assigneeForPatent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> assigneeForPatent;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/assigneeForPatentType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> assigneeForPatentType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/assigneeForPatentDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> assigneeForPatentDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "translatorOfType", key = "type"), @Reference(value = "translatorOfDate", key = "date") })
    @PropertySource(template = "person/translatorOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> translatorOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/translatorOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> translatorOfType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/translatorOfDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> translatorOfDate;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "person/researchOverview", predicate = "http://vivoweb.org/ontology/core#researchOverview")
    private String researchOverview;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "principalInvestigatorOnAwardedBy", key = "awardedBy"), @Reference(value = "principalInvestigatorOnStartDate", key = "startDate"), @Reference(value = "principalInvestigatorOnEndDate", key = "endDate") })
    @PropertySource(template = "person/principalInvestigatorOn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> principalInvestigatorOn;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "person/principalInvestigatorOnAwardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> principalInvestigatorOnAwardedBy;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/principalInvestigatorOnStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> principalInvestigatorOnStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/principalInvestigatorOnEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> principalInvestigatorOnEndDate;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "coPrincipalInvestigatorOnAwardedBy", key = "awardedBy"), @Reference(value = "coPrincipalInvestigatorOnStartDate", key = "startDate"), @Reference(value = "coPrincipalInvestigatorOnEndDate", key = "endDate") })
    @PropertySource(template = "person/coPrincipalInvestigatorOn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> coPrincipalInvestigatorOn;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "person/coPrincipalInvestigatorOnAwardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> coPrincipalInvestigatorOnAwardedBy;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/coPrincipalInvestigatorOnStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> coPrincipalInvestigatorOnStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/coPrincipalInvestigatorOnEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> coPrincipalInvestigatorOnEndDate;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "investigatorOnAwardedBy", key = "awardedBy"), @Reference(value = "investigatorOnStartDate", key = "startDate"), @Reference(value = "investigatorOnEndDate", key = "endDate") })
    @PropertySource(template = "person/investigatorOn", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> investigatorOn;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "person/investigatorOnAwardedBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> investigatorOnAwardedBy;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/investigatorOnStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> investigatorOnStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/investigatorOnEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> investigatorOnEndDate;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "otherResearchActivityRole", key = "role"), @Reference(value = "otherResearchActivityStartDate", key = "startDate"), @Reference(value = "otherResearchActivityEndDate", key = "endDate") })
    @PropertySource(template = "person/otherResearchActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> otherResearchActivity;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/otherResearchActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> otherResearchActivityRole;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/otherResearchActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> otherResearchActivityStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/otherResearchActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> otherResearchActivityEndDate;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "person/teachingOverview", predicate = "http://vivoweb.org/ontology/core#teachingOverview")
    private String teachingOverview;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "teachingActivityRole", key = "role") })
    @PropertySource(template = "person/teachingActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> teachingActivity;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/teachingActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> teachingActivityRole;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "adviseeType", key = "type"), @Reference(value = "adviseeCandidacy", key = "candidacy"), @Reference(value = "adviseeStartDate", key = "startDate"), @Reference(value = "adviseeEndDate", key = "endDate") })
    @PropertySource(template = "person/advisee", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> advisee;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/adviseeType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> adviseeType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/adviseeCandidacy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> adviseeCandidacy;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/adviseeStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/adviseeEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> adviseeEndDate;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "person/outreachOverview", predicate = "http://vivoweb.org/ontology/core#outreachOverview")
    private String outreachOverview;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "reviewerOfType", key = "type"), @Reference(value = "reviewerOfStartDate", key = "startDate"), @Reference(value = "reviewerOfEndDate", key = "endDate") })
    @PropertySource(template = "person/reviewerOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> reviewerOf;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/reviewerOfType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> reviewerOfType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/reviewerOfStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> reviewerOfStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/reviewerOfEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> reviewerOfEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "contactOrProvidorForServiceType", key = "type") })
    @PropertySource(template = "person/contactOrProvidorForService", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> contactOrProvidorForService;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/contactOrProvidorForServiceType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> contactOrProvidorForServiceType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "organizerOfEventType", key = "type"), @Reference(value = "organizerOfEventStartDate", key = "startDate"), @Reference(value = "organizerOfEventEndDate", key = "endDate") })
    @PropertySource(template = "person/organizerOfEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> organizerOfEvent;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/organizerOfEventType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> organizerOfEventType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/organizerOfEventStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> organizerOfEventStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/organizerOfEventEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> organizerOfEventEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "professionalServiceActivityType", key = "type"), @Reference(value = "professionalServiceActivityRole", key = "role"), @Reference(value = "professionalServiceActivityStartDate", key = "startDate"), @Reference(value = "professionalServiceActivityEndDate", key = "endDate") })
    @PropertySource(template = "person/professionalServiceActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> professionalServiceActivity;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/professionalServiceActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> professionalServiceActivityRole;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/professionalServiceActivityType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> professionalServiceActivityType;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/professionalServiceActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> professionalServiceActivityStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/professionalServiceActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> professionalServiceActivityEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "outreachAndCommunityServiceActivityType", key = "type"), @Reference(value = "outreachAndCommunityServiceActivityRole", key = "role"), @Reference(value = "outreachAndCommunityServiceActivityStartDate", key = "startDate"), @Reference(value = "outreachAndCommunityServiceActivityEndDate", key = "endDate") })
    @PropertySource(template = "person/outreachAndCommunityServiceActivity", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> outreachAndCommunityServiceActivity;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/professionalServiceActivityType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> outreachAndCommunityServiceActivityType;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/professionalServiceActivityRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> outreachAndCommunityServiceActivityRole;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/professionalServiceActivityStartDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> outreachAndCommunityServiceActivityStartDate;

    @Indexed(type = "nested_dates")
    @PropertySource(template = "person/professionalServiceActivityEndDate", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> outreachAndCommunityServiceActivityEndDate;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "performsTechniqueType", key = "type") })
    @PropertySource(template = "person/performsTechnique", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> performsTechnique;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/performsTechniqueType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> performsTechniqueType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "hasExpertiseInTechniqueType", key = "type") })
    @PropertySource(template = "person/hasExpertiseInTechnique", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasExpertiseInTechnique;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/hasExpertiseInTechniqueType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasExpertiseInTechniqueType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/isni", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#ISNI")
    private String isni;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/netid", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#NETID")
    private String netid;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/researcherId", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#ResearcherId")
    private String researcherId;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/twitter", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#twitterId")
    private String twitter;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/uid", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#UID")
    private String uid;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/uin", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#UIN")
    private String uin;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/youtube", predicate = "http://vivo.library.tamu.edu/ontology/TAMU#youtube")
    private String youtube;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "sameAsType", key = "type") })
    @PropertySource(template = "person/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> sameAs;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "person/sameAsType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> sameAsType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/eraCommonsId", predicate = "http://vivoweb.org/ontology/core#eRACommonsId")
    private String eraCommonsId;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/isiResearcherId", predicate = "http://vivoweb.org/ontology/core#researcherId")
    private String isiResearcherId;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/scopusId", predicate = "http://vivoweb.org/ontology/core#scopusId")
    private String scopusId;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/healthCareProviderId", predicate = "http://purl.obolibrary.org/obo/ARG_0000197")
    private String healthCareProviderId;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/email", predicate = "http://www.w3.org/2006/vcard/ns#email")
    private String email;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "person/firstName", predicate = "http://www.w3.org/2006/vcard/ns#givenName")
    private String firstName;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "person/middleName", predicate = "http://www.w3.org/2006/vcard/ns#middleName")
    private String middleName;

    @Indexed(type = "whole_string", copyTo = "_text_")
    @PropertySource(template = "person/lastName", predicate = "http://www.w3.org/2006/vcard/ns#familyName")
    private String lastName;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/streetAddress", predicate = "http://www.w3.org/2006/vcard/ns#streetAddress")
    private String streetAddress;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/locality", predicate = "http://www.w3.org/2006/vcard/ns#locality")
    private String locality;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/region", predicate = "http://www.w3.org/2006/vcard/ns#region")
    private String region;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/postalCode", predicate = "http://www.w3.org/2006/vcard/ns#postalCode")
    private String postalCode;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/country", predicate = "http://www.w3.org/2006/vcard/ns#country")
    private String country;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/geographicLocation", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String geographicLocation;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "person/locatedInFacility", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> locatedInFacility;

    @Indexed(type = "whole_string")
    @PropertySource(template = "person/fax", predicate = "http://www.w3.org/2006/vcard/ns#fax")
    private String fax;

    @NestedObject
    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "person/etdChairOf", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> etdChairOf;

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

    public List<String> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<String> memberOf) {
        this.memberOf = memberOf;
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

    public List<String> getHasCollaborator() {
        return hasCollaborator;
    }

    public void setHasCollaborator(List<String> hasCollaborator) {
        this.hasCollaborator = hasCollaborator;
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

    public List<String> getEducationAndTrainingMajorField() {
        return educationAndTrainingMajorField;
    }

    public void setEducationAndTrainingMajorField(List<String> educationAndTrainingMajorField) {
        this.educationAndTrainingMajorField = educationAndTrainingMajorField;
    }

    public List<String> getEducationAndTrainingDegreeAbbreviation() {
        return educationAndTrainingDegreeAbbreviation;
    }

    public void setEducationAndTrainingDegreeAbbreviation(List<String> educationAndTrainingDegreeAbbreviation) {
        this.educationAndTrainingDegreeAbbreviation = educationAndTrainingDegreeAbbreviation;
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

    public List<String> getSelectedPublicationPublisher() {
        return selectedPublicationPublisher;
    }

    public void setSelectedPublicationPublisher(List<String> selectedPublicationPublisher) {
        this.selectedPublicationPublisher = selectedPublicationPublisher;
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

    public List<String> getCollectionOrSeriesEditorForRole() {
        return collectionOrSeriesEditorForRole;
    }

    public void setCollectionOrSeriesEditorForRole(List<String> collectionOrSeriesEditorForRole) {
        this.collectionOrSeriesEditorForRole = collectionOrSeriesEditorForRole;
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

    public List<String> getAdvisee() {
        return advisee;
    }

    public void setAdvisee(List<String> advisee) {
        this.advisee = advisee;
    }

    public List<String> getAdviseeType() {
        return adviseeType;
    }

    public void setAdviseeType(List<String> adviseeType) {
        this.adviseeType = adviseeType;
    }

    public List<String> getAdviseeCandidacy() {
        return adviseeCandidacy;
    }

    public void setAdviseeCandidacy(List<String> adviseeCandidacy) {
        this.adviseeCandidacy = adviseeCandidacy;
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

    public List<String> getProfessionalServiceActivity() {
        return professionalServiceActivity;
    }

    public void setProfessionalServiceActivity(List<String> professionalServiceActivity) {
        this.professionalServiceActivity = professionalServiceActivity;
    }

    public List<String> getProfessionalServiceActivityRole() {
        return professionalServiceActivityRole;
    }

    public void setProfessionalServiceActivityRole(List<String> professionalServiceActivityRole) {
        this.professionalServiceActivityRole = professionalServiceActivityRole;
    }

    public List<String> getProfessionalServiceActivityType() {
        return professionalServiceActivityType;
    }

    public void setProfessionalServiceActivityType(List<String> professionalServiceActivityType) {
        this.professionalServiceActivityType = professionalServiceActivityType;
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

    public List<String> getOutreachAndCommunityServiceActivity() {
        return outreachAndCommunityServiceActivity;
    }

    public void setOutreachAndCommunityServiceActivity(List<String> outreachAndCommunityServiceActivity) {
        this.outreachAndCommunityServiceActivity = outreachAndCommunityServiceActivity;
    }

    public List<String> getOutreachAndCommunityServiceActivityType() {
        return outreachAndCommunityServiceActivityType;
    }

    public void setOutreachAndCommunityServiceActivityType(List<String> outreachAndCommunityServiceActivityType) {
        this.outreachAndCommunityServiceActivityType = outreachAndCommunityServiceActivityType;
    }

    public List<String> getOutreachAndCommunityServiceActivityRole() {
        return outreachAndCommunityServiceActivityRole;
    }

    public void setOutreachAndCommunityServiceActivityRole(List<String> outreachAndCommunityServiceActivityRole) {
        this.outreachAndCommunityServiceActivityRole = outreachAndCommunityServiceActivityRole;
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

    public List<String> getLocatedInFacility() {
        return locatedInFacility;
    }

    public void setLocatedInFacility(List<String> locatedInFacility) {
        this.locatedInFacility = locatedInFacility;
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

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

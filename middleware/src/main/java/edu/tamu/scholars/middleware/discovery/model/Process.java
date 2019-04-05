package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.discovery.annotation.CollectionSource;
import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

@JsonInclude(NON_EMPTY)
@SolrDocument(collection = "processes")
@CollectionSource(predicate = "http://purl.obolibrary.org/obo/BFO_0000015")
public class Process extends AbstractSolrDocument {

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "process/title", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String title;

    @Indexed
    @PropertySource(template = "process/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "process/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed
    @PropertySource(template = "process/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Indexed
    @PropertySource(template = "process/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "process/websiteLabel", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> websiteLabel;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "process/description", predicate = "http://vivoweb.org/ontology/core#description")
    private String description;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "process/offeredBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "offeredById")
    private List<String> offeredBy;

    @Indexed
    @PropertySource(template = "process/offeredByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> offeredByType;

    @Indexed
    private List<String> offeredById;

    @Indexed(type = "pdate")
    @PropertySource(template = "process/dateTimeIntervalStart", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalStart;

    @Indexed(type = "pdate")
    @PropertySource(template = "process/dateTimeIntervalEnd", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalEnd;

    @Indexed
    @PropertySource(template = "process/occursWithinEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "occursWithinEventId")
    private List<String> occursWithinEvent;

    @Indexed
    private List<String> occursWithinEventId;

    @Indexed
    @PropertySource(template = "process/includesEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "includesEventId")
    private List<String> includesEvent;

    @Indexed
    private List<String> includesEventId;

    @Indexed
    @PropertySource(template = "process/inEventSeries", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "inEventSeriesId")
    private List<String> inEventSeries;

    @Indexed
    private List<String> inEventSeriesId;

    @Indexed
    @PropertySource(template = "process/participant", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "participantId")
    private List<String> participant;

    @Indexed
    @PropertySource(template = "process/participantRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> participantRole;

    @Indexed
    private List<String> participantId;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "process/hasSubjectArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasSubjectArea;

    @Indexed
    private List<String> hasSubjectAreaId;

    @Indexed
    @PropertySource(template = "process/hasPrerequisite", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "hasPrerequisiteId")
    private List<String> hasPrerequisite;

    @Indexed
    @PropertySource(template = "process/hasPrerequisiteType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasPrerequisiteType;

    @Indexed
    private List<String> hasPrerequisiteId;

    @Indexed
    @PropertySource(template = "process/prerequisiteFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "prerequisiteForId")
    private List<String> prerequisiteFor;

    @Indexed
    @PropertySource(template = "process/prerequisiteForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> prerequisiteForType;

    @Indexed
    private List<String> prerequisiteForId;

    @Indexed
    @PropertySource(template = "process/credits", predicate = "http://vivoweb.org/ontology/core#courseCredits")
    private String credits;

    @Indexed
    @PropertySource(template = "process/geographicFocus", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "geographicFocusId")
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "process/geographicFocusType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed
    @PropertySource(template = "process/outputPublicationOrOtherWork", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "outputPublicationOrOtherWorkId")
    private List<String> outputPublicationOrOtherWork;

    @Indexed
    @PropertySource(template = "process/outputPublicationOrOtherWorkType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> outputPublicationOrOtherWorkType;

    @Indexed
    private List<String> outputPublicationOrOtherWorkId;

    @Indexed
    @PropertySource(template = "process/relatedDocument", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "relatedDocumentId")
    private List<String> relatedDocument;

    @Indexed
    @PropertySource(template = "process/relatedDocumentType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> relatedDocumentType;

    @Indexed
    private List<String> relatedDocumentId;

    @Indexed
    @PropertySource(template = "process/contactInformation", predicate = "http://vivoweb.org/ontology/core#contactInformation")
    private List<String> contactInformation;

    @Indexed
    @PropertySource(template = "process/heldInFacility", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "heldInFacilityId")
    private List<String> heldInFacility;

    @Indexed
    private List<String> heldInFacilityId;

    @Indexed
    @PropertySource(template = "process/heldInGeographicLocation", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "heldInGeographicLocationId")
    private List<String> heldInGeographicLocation;

    @Indexed
    private List<String> heldInGeographicLocationId;

    @Indexed
    @PropertySource(template = "process/hasOutput", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "hasOutputId")
    private List<String> hasOutput;

    @Indexed
    @PropertySource(template = "process/hasOutputType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasOutputType;

    @Indexed
    private List<String> hasOutputId;

    @Indexed
    @PropertySource(template = "process/hasParticipant", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "hasParticipantId")
    private List<String> hasParticipant;

    @Indexed
    @PropertySource(template = "process/hasParticipantType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasParticipantType;

    @Indexed
    private List<String> hasParticipantId;

    @Indexed
    @PropertySource(template = "process/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "process/sameAs", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> sameAsType;

    @Indexed
    private List<String> sameAsId;

    @Indexed(type = "pdate")
    @PropertySource(template = "process/modTime", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#modTime")
    private String modTime;

    public Process() {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(List<String> offeredBy) {
        this.offeredBy = offeredBy;
    }

    public List<String> getOfferedByType() {
        return offeredByType;
    }

    public void setOfferedByType(List<String> offeredByType) {
        this.offeredByType = offeredByType;
    }

    public List<String> getOfferedById() {
        return offeredById;
    }

    public void setOfferedById(List<String> offeredById) {
        this.offeredById = offeredById;
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

    public List<String> getOccursWithinEvent() {
        return occursWithinEvent;
    }

    public void setOccursWithinEvent(List<String> occursWithinEvent) {
        this.occursWithinEvent = occursWithinEvent;
    }

    public List<String> getOccursWithinEventId() {
        return occursWithinEventId;
    }

    public void setOccursWithinEventId(List<String> occursWithinEventId) {
        this.occursWithinEventId = occursWithinEventId;
    }

    public List<String> getIncludesEvent() {
        return includesEvent;
    }

    public void setIncludesEvent(List<String> includesEvent) {
        this.includesEvent = includesEvent;
    }

    public List<String> getIncludesEventId() {
        return includesEventId;
    }

    public void setIncludesEventId(List<String> includesEventId) {
        this.includesEventId = includesEventId;
    }

    public List<String> getInEventSeries() {
        return inEventSeries;
    }

    public void setInEventSeries(List<String> inEventSeries) {
        this.inEventSeries = inEventSeries;
    }

    public List<String> getInEventSeriesId() {
        return inEventSeriesId;
    }

    public void setInEventSeriesId(List<String> inEventSeriesId) {
        this.inEventSeriesId = inEventSeriesId;
    }

    public List<String> getParticipant() {
        return participant;
    }

    public void setParticipant(List<String> participant) {
        this.participant = participant;
    }

    public List<String> getParticipantRole() {
        return participantRole;
    }

    public void setParticipantRole(List<String> participantRole) {
        this.participantRole = participantRole;
    }

    public List<String> getParticipantId() {
        return participantId;
    }

    public void setParticipantId(List<String> participantId) {
        this.participantId = participantId;
    }

    public List<String> getHasSubjectArea() {
        return hasSubjectArea;
    }

    public void setHasSubjectArea(List<String> hasSubjectArea) {
        this.hasSubjectArea = hasSubjectArea;
    }

    public List<String> getHasSubjectAreaId() {
        return hasSubjectAreaId;
    }

    public void setHasSubjectAreaId(List<String> hasSubjectAreaId) {
        this.hasSubjectAreaId = hasSubjectAreaId;
    }

    public List<String> getHasPrerequisite() {
        return hasPrerequisite;
    }

    public void setHasPrerequisite(List<String> hasPrerequisite) {
        this.hasPrerequisite = hasPrerequisite;
    }

    public List<String> getHasPrerequisiteType() {
        return hasPrerequisiteType;
    }

    public void setHasPrerequisiteType(List<String> hasPrerequisiteType) {
        this.hasPrerequisiteType = hasPrerequisiteType;
    }

    public List<String> getHasPrerequisiteId() {
        return hasPrerequisiteId;
    }

    public void setHasPrerequisiteId(List<String> hasPrerequisiteId) {
        this.hasPrerequisiteId = hasPrerequisiteId;
    }

    public List<String> getPrerequisiteFor() {
        return prerequisiteFor;
    }

    public void setPrerequisiteFor(List<String> prerequisiteFor) {
        this.prerequisiteFor = prerequisiteFor;
    }

    public List<String> getPrerequisiteForType() {
        return prerequisiteForType;
    }

    public void setPrerequisiteForType(List<String> prerequisiteForType) {
        this.prerequisiteForType = prerequisiteForType;
    }

    public List<String> getPrerequisiteForId() {
        return prerequisiteForId;
    }

    public void setPrerequisiteForId(List<String> prerequisiteForId) {
        this.prerequisiteForId = prerequisiteForId;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
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

    public List<String> getOutputPublicationOrOtherWork() {
        return outputPublicationOrOtherWork;
    }

    public void setOutputPublicationOrOtherWork(List<String> outputPublicationOrOtherWork) {
        this.outputPublicationOrOtherWork = outputPublicationOrOtherWork;
    }

    public List<String> getOutputPublicationOrOtherWorkType() {
        return outputPublicationOrOtherWorkType;
    }

    public void setOutputPublicationOrOtherWorkType(List<String> outputPublicationOrOtherWorkType) {
        this.outputPublicationOrOtherWorkType = outputPublicationOrOtherWorkType;
    }

    public List<String> getOutputPublicationOrOtherWorkId() {
        return outputPublicationOrOtherWorkId;
    }

    public void setOutputPublicationOrOtherWorkId(List<String> outputPublicationOrOtherWorkId) {
        this.outputPublicationOrOtherWorkId = outputPublicationOrOtherWorkId;
    }

    public List<String> getRelatedDocument() {
        return relatedDocument;
    }

    public void setRelatedDocument(List<String> relatedDocument) {
        this.relatedDocument = relatedDocument;
    }

    public List<String> getRelatedDocumentType() {
        return relatedDocumentType;
    }

    public void setRelatedDocumentType(List<String> relatedDocumentType) {
        this.relatedDocumentType = relatedDocumentType;
    }

    public List<String> getRelatedDocumentId() {
        return relatedDocumentId;
    }

    public void setRelatedDocumentId(List<String> relatedDocumentId) {
        this.relatedDocumentId = relatedDocumentId;
    }

    public List<String> getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(List<String> contactInformation) {
        this.contactInformation = contactInformation;
    }

    public List<String> getHeldInFacility() {
        return heldInFacility;
    }

    public void setHeldInFacility(List<String> heldInFacility) {
        this.heldInFacility = heldInFacility;
    }

    public List<String> getHeldInFacilityId() {
        return heldInFacilityId;
    }

    public void setHeldInFacilityId(List<String> heldInFacilityId) {
        this.heldInFacilityId = heldInFacilityId;
    }

    public List<String> getHeldInGeographicLocation() {
        return heldInGeographicLocation;
    }

    public void setHeldInGeographicLocation(List<String> heldInGeographicLocation) {
        this.heldInGeographicLocation = heldInGeographicLocation;
    }

    public List<String> getHeldInGeographicLocationId() {
        return heldInGeographicLocationId;
    }

    public void setHeldInGeographicLocationId(List<String> heldInGeographicLocationId) {
        this.heldInGeographicLocationId = heldInGeographicLocationId;
    }

    public List<String> getHasOutput() {
        return hasOutput;
    }

    public void setHasOutput(List<String> hasOutput) {
        this.hasOutput = hasOutput;
    }

    public List<String> getHasOutputType() {
        return hasOutputType;
    }

    public void setHasOutputType(List<String> hasOutputType) {
        this.hasOutputType = hasOutputType;
    }

    public List<String> getHasOutputId() {
        return hasOutputId;
    }

    public void setHasOutputId(List<String> hasOutputId) {
        this.hasOutputId = hasOutputId;
    }

    public List<String> getHasParticipant() {
        return hasParticipant;
    }

    public void setHasParticipant(List<String> hasParticipant) {
        this.hasParticipant = hasParticipant;
    }

    public List<String> getHasParticipantType() {
        return hasParticipantType;
    }

    public void setHasParticipantType(List<String> hasParticipantType) {
        this.hasParticipantType = hasParticipantType;
    }

    public List<String> getHasParticipantId() {
        return hasParticipantId;
    }

    public void setHasParticipantId(List<String> hasParticipantId) {
        this.hasParticipantId = hasParticipantId;
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

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

    public List<String> getSameAsId() {
        return sameAsId;
    }

    public void setSameAsId(List<String> sameAsId) {
        this.sameAsId = sameAsId;
    }

}

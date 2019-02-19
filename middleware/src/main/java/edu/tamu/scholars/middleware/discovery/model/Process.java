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
@CollectionSource(key = "process.class")
public class Process extends AbstractSolrDocument {

    @Indexed
    @PropertySource(template = "process/title", key = "process.title")
    private String title;

    @Indexed
    @PropertySource(template = "process/type", key = "process.type", parse = true)
    private List<String> type;

    @Indexed
    @PropertySource(template = "process/image", key = "process.image")
    private String image;

    @Indexed
    @PropertySource(template = "process/thumbnail", key = "process.thumbnail")
    private String thumbnail;

    @Indexed
    @PropertySource(template = "process/websiteUrl", key = "process.website.url")
    private List<String> websiteUrl;

    @Indexed
    @PropertySource(template = "process/websiteLabel", key = "process.website.label")
    private List<String> websiteLabel;

    @Indexed
    @PropertySource(template = "process/description", key = "process.description")
    private String description;

    @Indexed
    // @PropertySource(template = "process/offeredBy", key = "process.offeredBy.name", id = "offeredById")
    private List<String> offeredBy;

    @Indexed
    // @PropertySource(template = "process/offeredByType", key = "process.offeredBy.type", parse = true)
    private List<String> offeredByType;

    @Indexed
    private List<String> offeredById;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalStart", key = "relationship.dateTimeInterval.start")
    private List<String> dateTimeIntervalStart;

    @Indexed(type = "pdate")
    @PropertySource(template = "relationship/dateTimeIntervalEnd", key = "relationship.dateTimeInterval.end")
    private List<String> dateTimeIntervalEnd;

    @Indexed
    // @PropertySource(template = "process/occursWithinEvent", key = "process.occursWithinEvent", id = "occursWithinEventId")
    private List<String> occursWithinEvent;

    @Indexed
    private List<String> occursWithinEventId;

    @Indexed
    // @PropertySource(template = "process/includesEvent", key = "process.includesEvent", id = "includesEventId")
    private List<String> includesEvent;

    @Indexed
    private List<String> includesEventId;

    @Indexed
    // @PropertySource(template = "process/inEventSeries", key = "process.inEventSeries", id = "inEventSeriesId")
    private List<String> inEventSeries;

    @Indexed
    private List<String> inEventSeriesId;

    @Indexed
    // @PropertySource(template = "process/participant", key = "process.participant.name", id = "participantId")
    private List<String> participant;

    @Indexed
    // @PropertySource(template = "process/participantType", key = "process.participant.type", parse = true)
    private List<String> participantType;

    @Indexed
    private List<String> participantId;

    @Indexed
    // @PropertySource(template = "process/hasSubjectArea", key = "process.hasSubjectArea", id = "hasSubjectAreaId")
    private List<String> hasSubjectArea;

    @Indexed
    private List<String> hasSubjectAreaId;

    @Indexed
    // @PropertySource(template = "process/hasPrerequisite", key = "process.hasPrerequisite.name", id = "hasPrerequisiteId")
    private List<String> hasPrerequisite;

    @Indexed
    // @PropertySource(template = "process/hasPrerequisiteType", key = "process.hasPrerequisite.type", parse = true)
    private List<String> hasPrerequisiteType;

    @Indexed
    private List<String> hasPrerequisiteId;

    @Indexed
    // @PropertySource(template = "process/prerequisiteFor", key = "process.prerequisiteFor.name", id = "prerequisiteForId")
    private List<String> prerequisiteFor;

    @Indexed
    // @PropertySource(template = "process/prerequisiteForType", key = "process.prerequisiteFor.type", parse = true)
    private List<String> prerequisiteForType;

    @Indexed
    private List<String> prerequisiteForId;

    @Indexed
    // @PropertySource(template = "process/credits", key = "process.credits")
    private String credits;

    @Indexed
    @PropertySource(template = "process/geographicFocus", key = "process.geographicFocus.name", id = "geographicFocusId")
    private List<String> geographicFocus;

    @Indexed
    @PropertySource(template = "process/geographicFocusType", key = "process.geographicFocus.type", parse = true)
    private List<String> geographicFocusType;

    @Indexed
    private List<String> geographicFocusId;

    @Indexed
    // @PropertySource(template = "process/outputPublicationOrOtherWork", key = "process.outputPublicationOrOtherWork.name", id = "outputPublicationOrOtherWorkId")
    private List<String> outputPublicationOrOtherWork;

    @Indexed
    // @PropertySource(template = "process/outputPublicationOrOtherWorkType", key = "process.outputPublicationOrOtherWork.type", parse = true)
    private List<String> outputPublicationOrOtherWorkType;

    @Indexed
    private List<String> outputPublicationOrOtherWorkId;

    @Indexed
    // @PropertySource(template = "process/relatedDocument", key = "process.relatedDocument.name", id = "relatedDocumentId")
    private List<String> relatedDocument;

    @Indexed
    // @PropertySource(template = "process/relatedDocumentType", key = "process.relatedDocument.type", parse = true)
    private List<String> relatedDocumentType;

    @Indexed
    private List<String> relatedDocumentId;

    @Indexed
    // @PropertySource(template = "process/contactInformation", key = "process.contactInformation")
    private List<String> contactInformation;

    @Indexed
    // @PropertySource(template = "process/heldInFacility", key = "process.heldInFacility", id = "heldInFacilityId")
    private List<String> heldInFacility;

    @Indexed
    private List<String> heldInFacilityId;

    @Indexed
    // @PropertySource(template = "process/heldInGeographicLocation", key = "process.heldInGeographicLocation", id = "heldInGeographicLocationId")
    private List<String> heldInGeographicLocation;

    @Indexed
    private List<String> heldInGeographicLocationId;

    @Indexed
    // @PropertySource(template = "process/hasOutput", key = "process.hasOutput.name", id = "hasOutputId")
    private List<String> hasOutput;

    @Indexed
    // @PropertySource(template = "process/hasOutputType", key = "process.hasOutput.type", parse = true)
    private List<String> hasOutputType;

    @Indexed
    private List<String> hasOutputId;

    @Indexed
    // @PropertySource(template = "process/hasParticipant", key = "process.hasParticipant.name", id = "hasParticipantId")
    private List<String> hasParticipant;

    @Indexed
    // @PropertySource(template = "process/hasParticipantType", key = "process.hasParticipant.type", parse = true)
    private List<String> hasParticipantType;

    @Indexed
    private List<String> hasParticipantId;

    @Indexed
    @PropertySource(template = "process/sameAs", key = "process.sameAs.name", id = "sameAsId")
    private List<String> sameAs;

    @Indexed
    @PropertySource(template = "process/sameAs", key = "process.sameAs.type", parse = true)
    private List<String> sameAsType;

    @Indexed(type = "pdate")
    @PropertySource(template = "process/modTime", key = "process.modTime")
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

    public List<String> getParticipantType() {
        return participantType;
    }

    public void setParticipantType(List<String> participantType) {
        this.participantType = participantType;
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

}

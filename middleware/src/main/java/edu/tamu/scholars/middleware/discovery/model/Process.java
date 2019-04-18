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
@SolrDocument(collection = "processes")
@CollectionSource(predicate = "http://purl.obolibrary.org/obo/BFO_0000015")
public class Process extends AbstractSolrDocument {

    @Indexed(type = "sorting_string", copyTo = "_text_")
    @PropertySource(template = "process/title", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private String title;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "process/type", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> type;

    @Indexed(type = "whole_string")
    @PropertySource(template = "process/image", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String image;

    @Indexed(type = "whole_string")
    @PropertySource(template = "process/thumbnail", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/public#directDownloadUrl")
    private String thumbnail;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "websiteUrl", key = "url") })
    @PropertySource(template = "process/website", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> website;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/websiteUrl", predicate = "http://www.w3.org/2006/vcard/ns#url")
    private List<String> websiteUrl;

    @Indexed(copyTo = "_text_")
    @PropertySource(template = "process/description", predicate = "http://vivoweb.org/ontology/core#description")
    private String description;

    @Indexed(type = "nested_strings", copyTo = "_text_")
    @NestedObject({ @Reference(value = "offeredByType", key = "type") })
    @PropertySource(template = "process/offeredBy", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> offeredBy;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/offeredByType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> offeredByType;

    @Indexed(type = "pdates")
    @PropertySource(template = "process/dateTimeIntervalStart", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalStart;

    @Indexed(type = "pdates")
    @PropertySource(template = "process/dateTimeIntervalEnd", predicate = "http://vivoweb.org/ontology/core#dateTime")
    private List<String> dateTimeIntervalEnd;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/occursWithinEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> occursWithinEvent;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/includesEvent", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> includesEvent;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/inEventSeries", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> inEventSeries;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "participantRole", key = "role") })
    @PropertySource(template = "process/participant", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> participant;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/participantRole", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> participantRole;

    @NestedObject
    @Indexed(type = "nested_strings", copyTo = "_text_")
    @PropertySource(template = "process/hasSubjectArea", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasSubjectArea;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "hasPrerequisiteType", key = "type") })
    @PropertySource(template = "process/hasPrerequisite", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasPrerequisite;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/hasPrerequisiteType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasPrerequisiteType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "prerequisiteForType", key = "type") })
    @PropertySource(template = "process/prerequisiteFor", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> prerequisiteFor;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/prerequisiteForType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> prerequisiteForType;

    @Indexed(type = "whole_string")
    @PropertySource(template = "process/credits", predicate = "http://vivoweb.org/ontology/core#courseCredits")
    private String credits;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "geographicFocusType", key = "type") })
    @PropertySource(template = "process/geographicFocus", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> geographicFocus;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/geographicFocusType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> geographicFocusType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "outputPublicationOrOtherWorkType", key = "type") })
    @PropertySource(template = "process/outputPublicationOrOtherWork", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> outputPublicationOrOtherWork;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/outputPublicationOrOtherWorkType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> outputPublicationOrOtherWorkType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "relatedDocumentType", key = "type") })
    @PropertySource(template = "process/relatedDocument", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> relatedDocument;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/relatedDocumentType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> relatedDocumentType;

    @Indexed(type = "whole_strings")
    @PropertySource(template = "process/contactInformation", predicate = "http://vivoweb.org/ontology/core#contactInformation")
    private List<String> contactInformation;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/heldInFacility", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> heldInFacility;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/heldInGeographicLocation", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> heldInGeographicLocation;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "hasOutputType", key = "type") })
    @PropertySource(template = "process/hasOutput", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasOutput;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/hasOutputType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasOutputType;

    @Indexed(type = "nested_strings")
    @NestedObject({ @Reference(value = "hasParticipantType", key = "type") })
    @PropertySource(template = "process/hasParticipant", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> hasParticipant;

    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/hasParticipantType", predicate = "http://vitro.mannlib.cornell.edu/ns/vitro/0.7#mostSpecificType", parse = true)
    private List<String> hasParticipantType;

    @NestedObject
    @Indexed(type = "nested_strings")
    @PropertySource(template = "process/sameAs", predicate = "http://www.w3.org/2000/01/rdf-schema#label")
    private List<String> sameAs;

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

    public List<String> getIncludesEvent() {
        return includesEvent;
    }

    public void setIncludesEvent(List<String> includesEvent) {
        this.includesEvent = includesEvent;
    }

    public List<String> getInEventSeries() {
        return inEventSeries;
    }

    public void setInEventSeries(List<String> inEventSeries) {
        this.inEventSeries = inEventSeries;
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

    public List<String> getHasSubjectArea() {
        return hasSubjectArea;
    }

    public void setHasSubjectArea(List<String> hasSubjectArea) {
        this.hasSubjectArea = hasSubjectArea;
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

    public List<String> getHeldInGeographicLocation() {
        return heldInGeographicLocation;
    }

    public void setHeldInGeographicLocation(List<String> heldInGeographicLocation) {
        this.heldInGeographicLocation = heldInGeographicLocation;
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

    public List<String> getSameAs() {
        return sameAs;
    }

    public void setSameAs(List<String> sameAs) {
        this.sameAs = sameAs;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

}

package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.harvest.annotation.CollectionSource;

@JsonInclude(NON_EMPTY)
@CollectionSource(key = "person.class")
// @formatter:off
//@PropertySource(
//    key = "person.class",
//    sparql = {
//        @PropertySource.Sparql(
//            template = "person/person",
//            properties = {
//                @PropertySource.Property(name = "uid", key = "person.uid"),
//                @PropertySource.Property(name = "name", key = "person.name"),
//                @PropertySource.Property(name = "firstName", key = "person.firstName"),
//                @PropertySource.Property(name = "middleName", key = "person.middleName"),
//                @PropertySource.Property(name = "lastName", key = "person.lastName"),
//                @PropertySource.Property(name = "title", key = "person.title"),
//                @PropertySource.Property(name = "email", key = "person.email"),
//                @PropertySource.Property(name = "overview", key = "person.overview"),
//                @PropertySource.Property(name = "researcherId", key = "person.researcherId"),
//                @PropertySource.Property(name = "scopusId", key = "person.scopusId"),
//                @PropertySource.Property(name = "orcidId", key = "person.orcidId", parse = true)
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/address",
//            properties = {
//                @PropertySource.Property(name = "postalCode", key = "person.address.postalCode"),
//                @PropertySource.Property(name = "region", key = "person.address.region"),
//                @PropertySource.Property(name = "streetAddress", key = "person.address.streetAddress"),
//                @PropertySource.Property(name = "locality", key = "person.address.locality"),
//                @PropertySource.Property(name = "country", key = "person.address.country")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/phone",
//            properties = {
//                @PropertySource.Property(name = "phone", key = "person.phone")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/links",
//            properties = {
//                @PropertySource.Property(name = "linkUrls", key = "person.links.url"),
//                @PropertySource.Property(name = "linkLabels", key = "person.links.label")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/image",
//            properties = {
//                @PropertySource.Property(name = "imageFilename", key = "person.image.filename"),
//                @PropertySource.Property(name = "imageMimeType", key = "person.image.mimeType"),
//                @PropertySource.Property(name = "imageId", key = "person.image.downloadLocation", parse = true)
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/thumbnail",
//            properties = {
//                @PropertySource.Property(name = "thumbnailFilename", key = "person.thumbnail.filename"),
//                @PropertySource.Property(name = "thumbnailMimeType", key = "person.thumbnail.mimeType"),
//                @PropertySource.Property(name = "thumbnailId", key = "person.thumbnail.downloadLocation", parse = true)
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/researchAreas",
//            properties = {
//                @PropertySource.Property(name = "researchAreas", key = "person.researchAreas", id = "researchAreaIds")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/positions",
//            properties = {
//                @PropertySource.Property(name = "positions", key = "person.positions", id = "positionIds")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/organizations",
//            properties = {
//                @PropertySource.Property(name = "organizations", key = "person.organizations", id = "organizationIds")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/teachings",
//            properties = {
//                @PropertySource.Property(name = "teachings", key = "person.teachings", id = "teachingIds")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/degrees",
//            properties = {
//                @PropertySource.Property(name = "degrees", key = "person.degrees.label", id = "degreeIds"),
//                @PropertySource.Property(name = "degreeFields", key = "person.degrees.field"),
//                @PropertySource.Property(name = "degreeDates", key = "person.degrees.date")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/publications",
//            properties = {
//                @PropertySource.Property(name = "publications", key = "person.publications.label", id = "publicationIds"),
//                @PropertySource.Property(name = "publicationDates", key = "person.publications.date"),
//                @PropertySource.Property(name = "publicationTypes", key = "person.publications.type", parse = true)
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/grants",
//            properties = {
//                @PropertySource.Property(name = "grants", key = "person.grants.label", id = "grantIds"),
//                @PropertySource.Property(name = "grantDates", key = "person.grants.date")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/awards",
//            properties = {
//                @PropertySource.Property(name = "awards", key = "person.awards.label", id = "awardIds"),
//                @PropertySource.Property(name = "awardDates", key = "person.awards.date")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/memberships",
//            properties = {
//                @PropertySource.Property(name = "memberships", key = "person.memberships.label", id = "membershipIds"),
//                @PropertySource.Property(name = "membershipDates", key = "person.memberships.date")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/advisings",
//            properties = {
//                @PropertySource.Property(name = "advisings", key = "person.advisings.label", id = "advisingIds"),
//                @PropertySource.Property(name = "advisingDates", key = "person.advisings.date")
//            }
//        ),
//        @PropertySource.Sparql(
//            template = "person/workByStudents",
//            properties = {
//                @PropertySource.Property(name = "workByStudents", key = "person.workByStudents", id = "workByStudentIds")
//            }
//        )
//    }
//)
// @formatter:on
@SolrDocument(collection = "persons")
public class Person extends AbstractSolrDocument {

    @Indexed
    private String uid;

    @Indexed
    private String name;

    @Indexed
    private String firstName;

    @Indexed
    private String middleName;

    @Indexed
    private String lastName;

    @Indexed
    private String overview;

    @Indexed
    private String orcidId;

    @Indexed
    private String researcherId;

    @Indexed
    private String scopusId;

    @Indexed
    private String title;

    @Indexed
    private String email;

    @Indexed
    private String phone;

    @Indexed
    private String postalCode;

    @Indexed
    private String region;

    @Indexed
    private String streetAddress;

    @Indexed
    private String locality;

    @Indexed
    private String country;

    @Indexed
    private String imageFilename;

    @Indexed
    private String imageMimeType;

    @Indexed
    private String imageId;

    @Indexed
    private String thumbnailFilename;

    @Indexed
    private String thumbnailMimeType;

    @Indexed
    private String thumbnailId;

    @Indexed
    private List<String> linkUrls;

    @Indexed
    private List<String> linkLabels;

    @Indexed
    private List<String> researchAreas;

    @Indexed
    private List<String> researchAreaIds;

    @Indexed
    private List<String> positions;

    @Indexed
    private List<String> positionIds;

    @Indexed
    private List<String> organizations;

    @Indexed
    private List<String> organizationIds;

    @Indexed
    private List<String> teachings;

    @Indexed
    private List<String> teachingIds;

    @Indexed
    private List<String> degrees;

    @Indexed
    private List<String> degreeFields;

    @Indexed(type = "pdate")
    private List<String> degreeDates;

    @Indexed
    private List<String> degreeIds;

    @Indexed
    private List<String> publications;

    @Indexed
    private List<String> publicationTypes;

    @Indexed(type = "pdate")
    private List<String> publicationDates;

    @Indexed
    private List<String> publicationIds;

    @Indexed
    private List<String> grants;

    @Indexed(type = "pdate")
    private List<String> grantDates;

    @Indexed
    private List<String> grantIds;

    @Indexed
    private List<String> awards;

    @Indexed(type = "pdate")
    private List<String> awardDates;

    @Indexed
    private List<String> awardIds;

    @Indexed
    private List<String> memberships;

    @Indexed(type = "pdate")
    private List<String> membershipDates;

    @Indexed
    private List<String> membershipIds;

    @Indexed
    private List<String> advisings;

    @Indexed(type = "pdate")
    private List<String> advisingDates;

    @Indexed
    private List<String> advisingIds;

    @Indexed
    private List<String> workByStudents;

    @Indexed
    private List<String> workByStudentIds;

    public Person() {
        this.linkUrls = new ArrayList<String>();
        this.linkLabels = new ArrayList<String>();
        this.researchAreas = new ArrayList<String>();
        this.researchAreaIds = new ArrayList<String>();
        this.positions = new ArrayList<String>();
        this.positionIds = new ArrayList<String>();
        this.organizations = new ArrayList<String>();
        this.organizationIds = new ArrayList<String>();
        this.teachings = new ArrayList<String>();
        this.teachingIds = new ArrayList<String>();
        this.degrees = new ArrayList<String>();
        this.degreeFields = new ArrayList<String>();
        this.degreeDates = new ArrayList<String>();
        this.degreeIds = new ArrayList<String>();
        this.publications = new ArrayList<String>();
        this.publicationTypes = new ArrayList<String>();
        this.publicationDates = new ArrayList<String>();
        this.publicationIds = new ArrayList<String>();
        this.grants = new ArrayList<String>();
        this.grantDates = new ArrayList<String>();
        this.grantIds = new ArrayList<String>();
        this.awards = new ArrayList<String>();
        this.awardDates = new ArrayList<String>();
        this.awardIds = new ArrayList<String>();
        this.memberships = new ArrayList<String>();
        this.membershipDates = new ArrayList<String>();
        this.membershipIds = new ArrayList<String>();
        this.advisings = new ArrayList<String>();
        this.advisingDates = new ArrayList<String>();
        this.advisingIds = new ArrayList<String>();
        this.workByStudents = new ArrayList<String>();
        this.workByStudentIds = new ArrayList<String>();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOrcidId() {
        return orcidId;
    }

    public void setOrcidId(String orcidId) {
        this.orcidId = orcidId;
    }

    public String getResearcherId() {
        return researcherId;
    }

    public void setResearcherId(String researcherId) {
        this.researcherId = researcherId;
    }

    public String getScopusId() {
        return scopusId;
    }

    public void setScopusId(String scopusId) {
        this.scopusId = scopusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getThumbnailFilename() {
        return thumbnailFilename;
    }

    public void setThumbnailFilename(String thumbnailFilename) {
        this.thumbnailFilename = thumbnailFilename;
    }

    public String getThumbnailMimeType() {
        return thumbnailMimeType;
    }

    public void setThumbnailMimeType(String thumbnailMimeType) {
        this.thumbnailMimeType = thumbnailMimeType;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public List<String> getLinkUrls() {
        return linkUrls;
    }

    public void setLinkUrls(List<String> linkUrls) {
        this.linkUrls = linkUrls;
    }

    public List<String> getLinkLabels() {
        return linkLabels;
    }

    public void setLinkLabels(List<String> linkLabels) {
        this.linkLabels = linkLabels;
    }

    public List<String> getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(List<String> researchAreas) {
        this.researchAreas = researchAreas;
    }

    public List<String> getResearchAreaIds() {
        return researchAreaIds;
    }

    public void setResearchAreaIds(List<String> researchAreaIds) {
        this.researchAreaIds = researchAreaIds;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public List<String> getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(List<String> positionIds) {
        this.positionIds = positionIds;
    }

    public List<String> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<String> organizations) {
        this.organizations = organizations;
    }

    public List<String> getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(List<String> organizationIds) {
        this.organizationIds = organizationIds;
    }

    public List<String> getTeachings() {
        return teachings;
    }

    public void setTeachings(List<String> teachings) {
        this.teachings = teachings;
    }

    public List<String> getTeachingIds() {
        return teachingIds;
    }

    public void setTeachingIds(List<String> teachingIds) {
        this.teachingIds = teachingIds;
    }

    public List<String> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<String> degrees) {
        this.degrees = degrees;
    }

    public List<String> getDegreeFields() {
        return degreeFields;
    }

    public void setDegreeFields(List<String> degreeFields) {
        this.degreeFields = degreeFields;
    }

    public List<String> getDegreeDates() {
        return degreeDates;
    }

    public void setDegreeDates(List<String> degreeDates) {
        this.degreeDates = degreeDates;
    }

    public List<String> getDegreeIds() {
        return degreeIds;
    }

    public void setDegreeIds(List<String> degreeIds) {
        this.degreeIds = degreeIds;
    }

    public List<String> getPublications() {
        return publications;
    }

    public void setPublications(List<String> publications) {
        this.publications = publications;
    }

    public List<String> getPublicationTypes() {
        return publicationTypes;
    }

    public void setPublicationTypes(List<String> publicationTypes) {
        this.publicationTypes = publicationTypes;
    }

    public List<String> getPublicationDates() {
        return publicationDates;
    }

    public void setPublicationDates(List<String> publicationDates) {
        this.publicationDates = publicationDates;
    }

    public List<String> getPublicationIds() {
        return publicationIds;
    }

    public void setPublicationIds(List<String> publicationIds) {
        this.publicationIds = publicationIds;
    }

    public List<String> getGrants() {
        return grants;
    }

    public void setGrants(List<String> grants) {
        this.grants = grants;
    }

    public List<String> getGrantDates() {
        return grantDates;
    }

    public void setGrantDates(List<String> grantDates) {
        this.grantDates = grantDates;
    }

    public List<String> getGrantIds() {
        return grantIds;
    }

    public void setGrantIds(List<String> grantIds) {
        this.grantIds = grantIds;
    }

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public List<String> getAwardDates() {
        return awardDates;
    }

    public void setAwardDates(List<String> awardDates) {
        this.awardDates = awardDates;
    }

    public List<String> getAwardIds() {
        return awardIds;
    }

    public void setAwardIds(List<String> awardIds) {
        this.awardIds = awardIds;
    }

    public List<String> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<String> memberships) {
        this.memberships = memberships;
    }

    public List<String> getMembershipDates() {
        return membershipDates;
    }

    public void setMembershipDates(List<String> membershipDates) {
        this.membershipDates = membershipDates;
    }

    public List<String> getMembershipIds() {
        return membershipIds;
    }

    public void setMembershipIds(List<String> membershipIds) {
        this.membershipIds = membershipIds;
    }

    public List<String> getAdvisings() {
        return advisings;
    }

    public void setAdvisings(List<String> advisings) {
        this.advisings = advisings;
    }

    public List<String> getAdvisingDates() {
        return advisingDates;
    }

    public void setAdvisingDates(List<String> advisingDates) {
        this.advisingDates = advisingDates;
    }

    public List<String> getAdvisingIds() {
        return advisingIds;
    }

    public void setAdvisingIds(List<String> advisingIds) {
        this.advisingIds = advisingIds;
    }

    public List<String> getWorkByStudents() {
        return workByStudents;
    }

    public void setWorkByStudents(List<String> workByStudents) {
        this.workByStudents = workByStudents;
    }

    public List<String> getWorkByStudentIds() {
        return workByStudentIds;
    }

    public void setWorkByStudentIds(List<String> workByStudentIds) {
        this.workByStudentIds = workByStudentIds;
    }

}

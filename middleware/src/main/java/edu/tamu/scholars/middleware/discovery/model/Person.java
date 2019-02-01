package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.harvest.annotation.Source;

@JsonInclude(NON_EMPTY)
// @formatter:off
@Source(
    key = "person.class",
    properties = {
        @Source.Property(name = "uid", key = "person.uid"),
        @Source.Property(name = "firstName", key = "person.firstName"),
        @Source.Property(name = "lastName", key = "person.lastName"),
        @Source.Property(name = "overview", key = "person.overview")
    },
    sparql = {
        @Source.Sparql(
            template = "person/orcid",
            properties = {
                @Source.Property(name = "orcid", key = "person.orcid", parse = true)
            }
        ),
        @Source.Sparql(
            template = "person/address",
            properties = {
                @Source.Property(name = "postalCode", key = "person.vcard.address.postalCode"),
                @Source.Property(name = "region", key = "person.vcard.address.region"),
                @Source.Property(name = "streetAddress", key = "person.vcard.address.streetAddress"),
                @Source.Property(name = "locality", key = "person.vcard.address.locality"),
                @Source.Property(name = "country", key = "person.vcard.address.country")
            }
        ),
        @Source.Sparql(
            template = "person/title",
            properties = {
                @Source.Property(name = "title", key = "person.vcard.title")
            }
        ),
        @Source.Sparql(
            template = "person/email",
            properties = {
                @Source.Property(name = "email", key = "person.vcard.email")
            }
        ),
        @Source.Sparql(
            template = "person/phone",
            properties = {
                @Source.Property(name = "phone", key = "person.vcard.phone")
            }
        ),
        @Source.Sparql(
            template = "person/url",
            properties = {
                @Source.Property(name = "linkUrl", key = "person.vcard.link.url"),
                @Source.Property(name = "linkLabel", key = "person.vcard.link.label")
            }
        ),
        @Source.Sparql(
            template = "person/image",
            properties = {
                @Source.Property(name = "imageFilename", key = "person.image.filename"),
                @Source.Property(name = "imageMimeType", key = "person.image.mimeType"),
                @Source.Property(name = "imageId", key = "person.image.downloadLocation", parse = true)
            }
        ),
        @Source.Sparql(
            template = "person/thumbnail",
            properties = {
                @Source.Property(name = "thumbnailFilename", key = "person.thumbnail.filename"),
                @Source.Property(name = "thumbnailMimeType", key = "person.thumbnail.mimeType"),
                @Source.Property(name = "thumbnailId", key = "person.thumbnail.downloadLocation", parse = true)
            }
        ),
        @Source.Sparql(
            template = "person/researchAreas",
            properties = {
                @Source.Property(name = "researchAreas", key = "person.researchAreas", id = "researchAreaIds")
            }
        ),
        @Source.Sparql(
            template = "person/positions",
            properties = {
                @Source.Property(name = "positions", key = "person.positions", id = "positionIds")
            }
        ),
        @Source.Sparql(
            template = "person/organizations",
            properties = {
                @Source.Property(name = "organizations", key = "person.organizations", id = "organizationIds")
            }
        ),
        @Source.Sparql(
            template = "person/courses",
            properties = {
                @Source.Property(name = "courses", key = "person.courses", id = "courseIds")
            }
        ),
        @Source.Sparql(
            template = "person/degrees",
            properties = {
                @Source.Property(name = "degrees", key = "person.degrees.label", id = "degreeIds"),
                @Source.Property(name = "degreeFields", key = "person.degrees.field"),
                @Source.Property(name = "degreeDates", key = "person.degrees.date")
            }
        ),
        @Source.Sparql(
            template = "person/publications",
            properties = {
                @Source.Property(name = "publications", key = "person.publications.label", id = "publicationIds"),
                @Source.Property(name = "publicationDates", key = "person.publications.date"),
                @Source.Property(name = "publicationTypes", key = "person.publications.type.value", schema = "person.publications.type.schema", parse = true)
            }
        ),
        @Source.Sparql(
            template = "person/grants",
            properties = {
                @Source.Property(name = "grants", key = "person.grants.label", id = "grantIds"),
                @Source.Property(name = "grantDates", key = "person.grants.date")
            }
        ),
        @Source.Sparql(
            template = "person/awards",
            properties = {
                @Source.Property(name = "awards", key = "person.awards.label", id = "awardIds"),
                @Source.Property(name = "awardDates", key = "person.awards.date")
            }
        ),
        @Source.Sparql(
            template = "person/memberships",
            properties = {
                @Source.Property(name = "memberships", key = "person.memberships.label", id = "membershipIds"),
                @Source.Property(name = "membershipDates", key = "person.memberships.date")
            }
        ),
        @Source.Sparql(
            template = "person/advisings",
            properties = {
                @Source.Property(name = "advisings", key = "person.advisings.label", id = "advisingIds"),
                @Source.Property(name = "advisingDates", key = "person.advisings.date")
            }
        ),
        @Source.Sparql(
            template = "person/workByStudents",
            properties = {
                @Source.Property(name = "workByStudents", key = "person.workByStudents", id = "workByStudentIds")
            }
        )
    }
)
// @formatter:on
@SolrDocument(collection = "persons")
public class Person extends AbstractSolrDocument {

    @Indexed
    private String uid;

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    @Indexed
    private String overview;

    @Indexed
    private String orcid;

    @Indexed
    private String title;

    @Indexed
    private String email;

    @Indexed
    private String phone;

    @Indexed
    private List<String> linkUrl;

    @Indexed
    private List<String> linkLabel;

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
    private List<String> courses;

    @Indexed
    private List<String> courseIds;

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
        this.linkUrl = new ArrayList<String>();
        this.linkLabel = new ArrayList<String>();
        this.researchAreas = new ArrayList<String>();
        this.researchAreaIds = new ArrayList<String>();
        this.positions = new ArrayList<String>();
        this.positionIds = new ArrayList<String>();
        this.organizations = new ArrayList<String>();
        this.organizationIds = new ArrayList<String>();
        this.courses = new ArrayList<String>();
        this.courseIds = new ArrayList<String>();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
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

    public List<String> getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(List<String> linkUrl) {
        this.linkUrl = linkUrl;
    }

    public List<String> getLinkLabel() {
        return linkLabel;
    }

    public void setLinkLabel(List<String> linkLabel) {
        this.linkLabel = linkLabel;
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

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public List<String> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<String> courseIds) {
        this.courseIds = courseIds;
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

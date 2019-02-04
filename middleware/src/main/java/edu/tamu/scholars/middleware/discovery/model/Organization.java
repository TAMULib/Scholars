package edu.tamu.scholars.middleware.discovery.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.fasterxml.jackson.annotation.JsonInclude;

import edu.tamu.scholars.middleware.harvest.annotation.Source;

@JsonInclude(NON_EMPTY)
//@formatter:off
@Source(
  key = "organization.class",
  sparql = {
      @Source.Sparql(
          template = "organization/organization",
          properties = {
              @Source.Property(name = "type", key = "organization.type.value", schema = "organization.type.schema", parse = true),
              @Source.Property(name = "name", key = "organization.name"),
              @Source.Property(name = "abbreviation", key = "organization.abbreviation"),
              @Source.Property(name = "overview", key = "organization.overview")
          }
      ),
      @Source.Sparql(
          template = "organization/date",
          properties = {
              @Source.Property(name = "date", key = "organization.date")
          }
      ),
      @Source.Sparql(
          template = "organization/image",
          properties = {
              @Source.Property(name = "imageFilename", key = "organization.image.filename"),
              @Source.Property(name = "imageMimeType", key = "organization.image.mimeType"),
              @Source.Property(name = "imageId", key = "organization.image.downloadLocation", parse = true)
          }
      ),
      @Source.Sparql(
          template = "organization/address",
          properties = {
              @Source.Property(name = "postalCode", key = "organization.address.postalCode"),
              @Source.Property(name = "region", key = "organization.address.region"),
              @Source.Property(name = "streetAddress", key = "organization.address.streetAddress"),
              @Source.Property(name = "locality", key = "organization.address.locality"),
              @Source.Property(name = "country", key = "organization.address.country")
          }
      ),
      @Source.Sparql(
          template = "organization/email",
          properties = {
              @Source.Property(name = "email", key = "organization.email")
          }
      ),
      @Source.Sparql(
          template = "organization/geo",
          properties = {
              @Source.Property(name = "geo", key = "organization.geo")
          }
      ),
      @Source.Sparql(
          template = "organization/successor",
          properties = {
              @Source.Property(name = "successor", key = "organization.successor", id = "successorId")
          }
      ),
      @Source.Sparql(
          template = "organization/predecessor",
          properties = {
              @Source.Property(name = "predecessor", key = "organization.predecessor", id = "predecessorId")
          }
      ),
      @Source.Sparql(
          template = "organization/links",
          properties = {
              @Source.Property(name = "linkUrls", key = "organization.links.url"),
              @Source.Property(name = "linkLabels", key = "organization.links.label")
          }
      ),
      @Source.Sparql(
          template = "organization/degrees",
          properties = {
              @Source.Property(name = "degrees", key = "organization.degrees.label", id = "degreeIds"),
              @Source.Property(name = "degreeFields", key = "organization.degrees.field"),
              @Source.Property(name = "degreeDates", key = "organization.degrees.date")
          }
      ),
      @Source.Sparql(
          template = "organization/partOf",
          properties = {
              @Source.Property(name = "partOf", key = "organization.partOf", id = "partOfId")
          }
      ),
      @Source.Sparql(
          template = "organization/hasParts",
          properties = {
              @Source.Property(name = "hasParts", key = "organization.hasParts", id = "hasPartIds")
          }
      )
  }
)
//@formatter:on
@SolrDocument(collection = "organizations")
public class Organization extends AbstractSolrDocument {

    @Indexed
    private String type;

    @Indexed
    private String name;

    @Indexed
    private String abbreviation;

    @Indexed
    private String overview;

    @Indexed(type = "pdate")
    private String date;

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
    private String email;

    @Indexed(type = "location")
    private String geo;

    @Indexed
    private String successor;

    @Indexed
    private String successorId;

    @Indexed
    private String predecessor;

    @Indexed
    private String predecessorId;

    @Indexed
    private String partOf;

    @Indexed
    private String partOfId;

    @Indexed
    private List<String> linkUrls;

    @Indexed
    private List<String> linkLabels;

    @Indexed
    private List<String> degrees;

    @Indexed
    private List<String> degreeFields;

    @Indexed(type = "pdate")
    private List<String> degreeDates;

    @Indexed
    private List<String> degreeIds;

    @Indexed
    private List<String> hasParts;

    @Indexed
    private List<String> hasPartIds;

    public Organization() {
        this.type = "Organization";
        this.linkUrls = new ArrayList<String>();
        this.linkLabels = new ArrayList<String>();
        this.hasParts = new ArrayList<String>();
        this.hasPartIds = new ArrayList<String>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getSuccessor() {
        return successor;
    }

    public void setSuccessor(String successor) {
        this.successor = successor;
    }

    public String getSuccessorId() {
        return successorId;
    }

    public void setSuccessorId(String successorId) {
        this.successorId = successorId;
    }

    public String getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(String predecessor) {
        this.predecessor = predecessor;
    }

    public String getPredecessorId() {
        return predecessorId;
    }

    public void setPredecessorId(String predecessorId) {
        this.predecessorId = predecessorId;
    }

    public String getPartOf() {
        return partOf;
    }

    public void setPartOf(String partOf) {
        this.partOf = partOf;
    }

    public String getPartOfId() {
        return partOfId;
    }

    public void setPartOfId(String partOfId) {
        this.partOfId = partOfId;
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

    public List<String> getHasParts() {
        return hasParts;
    }

    public void setHasParts(List<String> hasParts) {
        this.hasParts = hasParts;
    }

    public List<String> getHasPartIds() {
        return hasPartIds;
    }

    public void setHasPartIds(List<String> hasPartIds) {
        this.hasPartIds = hasPartIds;
    }

}

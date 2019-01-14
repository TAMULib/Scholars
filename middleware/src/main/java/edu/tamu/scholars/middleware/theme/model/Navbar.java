package edu.tamu.scholars.middleware.theme.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class Navbar {

    @Column(name = "navbar_brand_text")
    private String brandText;

    @Column(name = "navbar_brand_uri")
    private String brandUri;

    @Column(name = "navbar_logo_uri")
    private String logoUri;

    @ElementCollection
    @CollectionTable(name = "theme_navbar_links")
    private List<Link> links;

    @ElementCollection
    @CollectionTable(name = "theme_navbar_variables")
    private List<Style> variables;

    public Navbar() {
        super();
        this.links = new ArrayList<Link>();
        this.variables = new ArrayList<Style>();
    }

    public String getBrandText() {
        return brandText;
    }

    public void setBrandText(String brandText) {
        this.brandText = brandText;
    }

    public String getBrandUri() {
        return brandUri;
    }

    public void setBrandUri(String brandUri) {
        this.brandUri = brandUri;
    }

    public String getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Style> getVariables() {
        return variables;
    }

    public void setVariables(List<Style> variables) {
        this.variables = variables;
    }

}

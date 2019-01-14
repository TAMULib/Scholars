package edu.tamu.scholars.middleware.theme.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class Banner {

    @Column(name = "banner_image_uri")
    private String imageUri;

    @Column(name = "banner_alt_text")
    private String altText;

    @ElementCollection
    @CollectionTable(name = "theme_banner_variables")
    private List<Style> variables;

    public Banner() {
        super();
        this.variables = new ArrayList<Style>();
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public List<Style> getVariables() {
        return variables;
    }

    public void setVariables(List<Style> variables) {
        this.variables = variables;
    }

}

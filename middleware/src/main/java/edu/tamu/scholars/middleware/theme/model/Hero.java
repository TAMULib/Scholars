package edu.tamu.scholars.middleware.theme.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Hero {

    @Column
    private String imageUri;

    @Column
    private String imageAlt;

    @Column
    private String watermarkImageUri;

    @Column
    private String watermarkText;

    @Column(columnDefinition = "TEXT")
    private String baseText;

    @Column
    private String fontColor;

    @Column
    private String linkColor;

    @Column
    private String linkHoverColor;

    @Column
    private int interval;

    public Hero() {
        super();
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getWatermarkImageUri() {
        return watermarkImageUri;
    }

    public void setWatermarkImageUri(String watermarkImageUri) {
        this.watermarkImageUri = watermarkImageUri;
    }

    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    public String getBaseText() {
        return baseText;
    }

    public void setBaseText(String baseText) {
        this.baseText = baseText;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getLinkColor() {
        return linkColor;
    }

    public void setLinkColor(String linkColor) {
        this.linkColor = linkColor;
    }

    public String getLinkHoverColor() {
        return linkHoverColor;
    }

    public void setLinkHoverColor(String linkHoverColor) {
        this.linkHoverColor = linkHoverColor;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

}

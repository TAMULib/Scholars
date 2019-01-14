package edu.tamu.scholars.middleware.theme.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Style {

    @Column
    private String key;

    @Column
    private String value;

    public Style() {
        super();
    }

    public Style(String key, String value) {
        this();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

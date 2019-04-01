package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DisplaySection {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    public String template;

    public DisplaySection() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}

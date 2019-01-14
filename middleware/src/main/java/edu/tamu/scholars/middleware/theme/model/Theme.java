package edu.tamu.scholars.middleware.theme.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "themes")
public class Theme implements Serializable {

    private static final long serialVersionUID = 3711737239238294248L;

    @Id
    @JsonInclude(Include.NON_EMPTY)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "${Theme.nameRequired}")
    @Size(min = 2, max = 64, message = "${Theme.nameSize}")
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String organization;

    @Column(nullable = false)
    private boolean active;

    @Embedded
    private Home home;

    @Embedded
    private Header header;

    @Embedded
    private Footer footer;

    @ElementCollection
    private List<Style> colors;

    @ElementCollection
    private List<Style> variants;

    @ElementCollection
    private List<Style> variables;

    public Theme() {
        super();
        this.active = false;
        this.home = new Home();
        this.header = new Header();
        this.footer = new Footer();
        this.colors = new ArrayList<Style>();
        this.variants = new ArrayList<Style>();
        this.variables = new ArrayList<Style>();
    }

    public Theme(String name, String organization) {
        this();
        this.name = name;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public List<Style> getColors() {
        return colors;
    }

    public void setColors(List<Style> colors) {
        this.colors = colors;
    }

    public List<Style> getVariants() {
        return variants;
    }

    public void setVariants(List<Style> variants) {
        this.variants = variants;
    }

    public List<Style> getVariables() {
        return variables;
    }

    public void setVariables(List<Style> variables) {
        this.variables = variables;
    }

}

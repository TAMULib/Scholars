package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tab_views")
public class TabView extends View {

    private static final long serialVersionUID = -6232439954200363571L;

    @Column(nullable = false)
    private boolean hidden;

    @ElementCollection
    public List<DisplaySection> sections;

    public TabView() {
        hidden = false;
        sections = new ArrayList<DisplaySection>();
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<DisplaySection> getSections() {
        return sections;
    }

    public void setSections(List<DisplaySection> sections) {
        this.sections = sections;
    }

}

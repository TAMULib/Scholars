package edu.tamu.scholars.middleware.view.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tab_views")
public class TabView extends View {

    private static final long serialVersionUID = -6232439954200363571L;

    @ElementCollection
    public List<DisplaySection> sections;

    public TabView() {

    }

    public List<DisplaySection> getSections() {
        return sections;
    }

    public void setSections(List<DisplaySection> sections) {
        this.sections = sections;
    }

}

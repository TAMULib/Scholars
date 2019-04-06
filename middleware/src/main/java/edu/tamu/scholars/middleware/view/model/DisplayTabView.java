package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "display_tabs")
public class DisplayTabView extends View {

    private static final long serialVersionUID = -6232439954200363571L;

    @Column(nullable = false)
    private boolean hidden;

    @JoinColumn(name = "display_tab_view_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<DisplayTabSectionView> sections;

    public DisplayTabView() {
        hidden = false;
        sections = new ArrayList<DisplayTabSectionView>();
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<DisplayTabSectionView> getSections() {
        return sections;
    }

    public void setSections(List<DisplayTabSectionView> sections) {
        this.sections = sections;
    }

}

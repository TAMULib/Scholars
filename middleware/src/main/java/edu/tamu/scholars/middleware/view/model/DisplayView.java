package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "display_views")
public class DisplayView extends View {

    private static final long serialVersionUID = 7556127622115170597L;

    // TODO: add validation to prevent any given type belonging to multiple display views
    @ElementCollection
    private List<String> types;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mainContentTemplate;

    @Column(columnDefinition = "TEXT")
    private String leftScanTemplate;

    @Column(columnDefinition = "TEXT")
    private String rightScanTemplate;

    @JoinColumn(name = "display_view_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DisplayTabView> tabs;

    public DisplayView() {
        super();
        types = new ArrayList<String>();
        tabs = new ArrayList<DisplayTabView>();
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getMainContentTemplate() {
        return mainContentTemplate;
    }

    public void setMainContentTemplate(String mainContentTemplate) {
        this.mainContentTemplate = mainContentTemplate;
    }

    public String getLeftScanTemplate() {
        return leftScanTemplate;
    }

    public void setLeftScanTemplate(String leftScanTemplate) {
        this.leftScanTemplate = leftScanTemplate;
    }

    public String getRightScanTemplate() {
        return rightScanTemplate;
    }

    public void setRightScanTemplate(String rightScanTemplate) {
        this.rightScanTemplate = rightScanTemplate;
    }

    public List<DisplayTabView> getTabs() {
        return tabs;
    }

    public void setTabs(List<DisplayTabView> tabs) {
        this.tabs = tabs;
    }

}

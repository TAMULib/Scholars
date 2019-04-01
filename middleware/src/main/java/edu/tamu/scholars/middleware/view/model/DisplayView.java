package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "display_views")
public class DisplayView extends ResourceView {

    private static final long serialVersionUID = 7556127622115170597L;

    @Column(columnDefinition = "TEXT")
    private String mainContentTemplate;

    @Column(columnDefinition = "TEXT")
    private String leftScanTemplate;

    @Column(columnDefinition = "TEXT")
    private String rightScanTemplate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TabView> tabs;

    public DisplayView() {
        super();
        tabs = new ArrayList<TabView>();
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

    public List<TabView> getTabs() {
        return tabs;
    }

    public void setTabs(List<TabView> tabs) {
        this.tabs = tabs;
    }

}

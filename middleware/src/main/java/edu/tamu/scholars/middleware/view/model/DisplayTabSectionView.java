package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "display_tab_sections")
@AttributeOverride(name = "name", column = @Column(nullable = false))
public class DisplayTabSectionView extends View {

    private static final long serialVersionUID = 938457239875938467L;

    @Column(nullable = false)
    private boolean hidden;

    @Column(nullable = false, columnDefinition = "TEXT")
    public String template;

    @ElementCollection
    private List<String> requiredFields;

    @ElementCollection
    private List<LazyReference> lazyReferences;

    @JoinColumn(name = "display_tab_section_id")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DisplayTabSectionSubsectionView> subsections;

    public DisplayTabSectionView() {
        super();
        hidden = false;
        requiredFields = new ArrayList<String>();
        lazyReferences = new ArrayList<LazyReference>();
        subsections = new ArrayList<DisplayTabSectionSubsectionView>();
    }

    public String getName() {
        return name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    public List<LazyReference> getLazyReferences() {
        return lazyReferences;
    }

    public void setLazyReferences(List<LazyReference> lazyReferences) {
        this.lazyReferences = lazyReferences;
    }

    public List<DisplayTabSectionSubsectionView> getSubsections() {
        return subsections;
    }

    public void setSubsections(List<DisplayTabSectionSubsectionView> subsections) {
        this.subsections = subsections;
    }

}

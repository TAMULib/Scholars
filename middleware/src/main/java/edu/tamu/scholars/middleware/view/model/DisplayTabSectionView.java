package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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

    public DisplayTabSectionView() {
        super();
        hidden = false;
        requiredFields = new ArrayList<String>();
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

}

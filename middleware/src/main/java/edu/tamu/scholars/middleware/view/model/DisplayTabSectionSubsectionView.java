package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "display_tab_section_subsections")
@AttributeOverride(name = "name", column = @Column(nullable = false))
public class DisplayTabSectionSubsectionView extends View {

    private static final long serialVersionUID = 7776446742411477782L;

    @Column(nullable = false)
    public String field;

    @ElementCollection
    private List<Filter> filters;

    @ElementCollection
    private List<Sort> sort;

    @Column(nullable = false)
    private int pageSize;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String template;

    public DisplayTabSectionSubsectionView() {
        super();
        filters = new ArrayList<Filter>();
        sort = new ArrayList<Sort>();
        pageSize = 5;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Sort> getSort() {
        return sort;
    }

    public void setSort(List<Sort> sort) {
        this.sort = sort;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}

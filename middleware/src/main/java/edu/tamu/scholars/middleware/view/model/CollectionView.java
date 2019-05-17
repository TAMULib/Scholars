package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MapKeyColumn;
import javax.persistence.MappedSuperclass;

import edu.tamu.scholars.middleware.view.annotation.ValidCollectionFacets;
import edu.tamu.scholars.middleware.view.annotation.ValidCollectionFilters;

@MappedSuperclass
@ValidCollectionFacets(message = "{CollectionView.validCollectionFacets}")
@ValidCollectionFilters(message = "{CollectionView.validCollectionFilters}")
public abstract class CollectionView extends ResourceView {

    private static final long serialVersionUID = 6875458024293994230L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Layout layout;

    @ElementCollection
    @MapKeyColumn(name = "key")
    @Column(name = "template", columnDefinition = "TEXT")
    private Map<String, String> templates;

    @ElementCollection
    private List<String> styles;

    @ElementCollection
    private List<Facet> facets;

    @ElementCollection
    private List<Filter> filters;

    @ElementCollection
    private List<Sort> sort;

    public CollectionView() {
        super();
        templates = new HashMap<String, String>();
        styles = new ArrayList<String>();
        facets = new ArrayList<Facet>();
        filters = new ArrayList<Filter>();
        sort = new ArrayList<Sort>();
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Map<String, String> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, String> templates) {
        this.templates = templates;
    }

    public List<String> getStyles() {
        return styles;
    }

    public void setStyles(List<String> styles) {
        this.styles = styles;
    }

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
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

}

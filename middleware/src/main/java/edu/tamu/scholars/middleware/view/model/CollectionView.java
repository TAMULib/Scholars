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
import edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection;

@MappedSuperclass
@ValidCollectionFacets(message = "{CollectionView.validCollectionFacets}")
@ValidCollectionFilters(message = "{CollectionView.validCollectionFilters}")
public abstract class CollectionView extends View {

    private static final long serialVersionUID = 6875458024293994230L;

    @ValidDiscoveryCollection(message = "{CollectionView.validDiscoveryCollection}")
    @Column(nullable = false)
    private String collection;

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

    public CollectionView() {
        super();
        templates = new HashMap<String, String>();
        styles = new ArrayList<String>();
        facets = new ArrayList<Facet>();
        filters = new ArrayList<Filter>();
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

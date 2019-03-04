package edu.tamu.scholars.middleware.view.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private List<Facet> facets;

    @ElementCollection
    private List<Filter> filters;

    public CollectionView() {
        super();
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

package edu.tamu.scholars.middleware.view.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "discovery_views")
public class DiscoveryView extends CollectionView {

    private static final long serialVersionUID = 6627502439871091387L;

    public DiscoveryView() {
        super();
    }

}

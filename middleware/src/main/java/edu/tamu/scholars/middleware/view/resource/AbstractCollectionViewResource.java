package edu.tamu.scholars.middleware.view.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import edu.tamu.scholars.middleware.view.model.CollectionView;

public abstract class AbstractCollectionViewResource<V extends CollectionView> extends Resource<V> {

    public AbstractCollectionViewResource(V content, Iterable<Link> links) {
        super(content, links);
    }

}

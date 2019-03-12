package edu.tamu.scholars.middleware.view.resource;

import org.springframework.hateoas.Link;

import edu.tamu.scholars.middleware.view.model.DirectoryView;

public class DirectoryViewResource extends AbstractCollectionViewResource<DirectoryView> {

    public DirectoryViewResource(DirectoryView content, Iterable<Link> links) {
        super(content, links);
    }

}

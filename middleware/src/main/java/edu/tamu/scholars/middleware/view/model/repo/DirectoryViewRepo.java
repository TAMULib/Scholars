package edu.tamu.scholars.middleware.view.model.repo;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edu.tamu.scholars.middleware.view.model.DirectoryView;

@RepositoryRestResource
public interface DirectoryViewRepo extends ViewRepo<DirectoryView> {

}

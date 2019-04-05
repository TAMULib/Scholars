package edu.tamu.scholars.middleware.view.model.repo;

import org.springframework.data.repository.NoRepositoryBean;

import edu.tamu.scholars.middleware.theme.model.repo.NamedRepo;
import edu.tamu.scholars.middleware.view.model.View;

@NoRepositoryBean
public interface ViewRepo<V extends View> extends NamedRepo<V> {

}

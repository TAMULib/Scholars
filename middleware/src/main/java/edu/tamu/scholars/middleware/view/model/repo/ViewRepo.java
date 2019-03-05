package edu.tamu.scholars.middleware.view.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

import edu.tamu.scholars.middleware.view.model.View;

@NoRepositoryBean
public interface ViewRepo<V extends View> extends JpaRepository<V, Long> {

    @RestResource(exported = false)
    public Optional<V> findByName(String name);

}

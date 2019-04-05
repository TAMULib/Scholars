package edu.tamu.scholars.middleware.theme.model.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface NamedRepo<E> extends JpaRepository<E, Long> {

    @RestResource(exported = false)
    public Optional<E> findByName(String name);

}

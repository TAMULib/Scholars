package edu.tamu.scholars.middleware.view.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.view.model.DiscoveryView;

@Repository
public interface DiscoveryViewRepo extends JpaRepository<DiscoveryView, Long> {

}

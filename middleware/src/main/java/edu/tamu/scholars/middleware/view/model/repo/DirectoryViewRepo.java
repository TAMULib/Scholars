package edu.tamu.scholars.middleware.view.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.view.model.DirectoryView;

@Repository
public interface DirectoryViewRepo extends JpaRepository<DirectoryView, Long> {

}

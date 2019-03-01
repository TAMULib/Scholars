package edu.tamu.scholars.middleware.view.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.tamu.scholars.middleware.view.model.ResultView;

@Repository
public interface ResultViewRepo extends JpaRepository<ResultView, Long> {

}

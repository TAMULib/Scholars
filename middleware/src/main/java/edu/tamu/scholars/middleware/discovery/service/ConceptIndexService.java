package edu.tamu.scholars.middleware.discovery.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Concept;
import edu.tamu.scholars.middleware.discovery.model.repo.ConceptRepo;

@Service
public class ConceptIndexService extends AbstractSolrIndexService<Concept, ConceptRepo>{

	@Override
	public Class<?> type() {
		return Concept.class;
	}

}

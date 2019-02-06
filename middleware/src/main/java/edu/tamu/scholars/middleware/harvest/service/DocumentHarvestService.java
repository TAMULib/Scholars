package edu.tamu.scholars.middleware.harvest.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Document;
import edu.tamu.scholars.middleware.discovery.service.DocumentIndexService;

@Service
public class DocumentHarvestService extends AbstractHarvestService<Document, DocumentIndexService> {

}

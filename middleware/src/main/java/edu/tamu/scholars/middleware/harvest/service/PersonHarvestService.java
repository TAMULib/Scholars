package edu.tamu.scholars.middleware.harvest.service;

import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.discovery.model.Person;
import edu.tamu.scholars.middleware.discovery.service.PersonIndexService;

@Service
public class PersonHarvestService extends AbstractHarvestService<Person, PersonIndexService> {

}

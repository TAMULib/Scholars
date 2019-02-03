package edu.tamu.scholars.middleware.harvest.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class VivoHarvestService {

    private final static Logger logger = LoggerFactory.getLogger(VivoHarvestService.class);

    @Autowired
    private List<HarvestService> harvesters;

    @Scheduled(fixedDelayString = "${middleware.harvest.fixedDelay}", initialDelayString = "${middleware.harvest.initialDelay}")
    public void harvest() {
        Instant allHarvsetStart = Instant.now();
        for(HarvestService harvester : harvesters) {
            logger.info(String.format("Harvesting %s", harvester.name()));
            Instant start = Instant.now();
            harvester.harvest();
            logger.info(String.format("Harvesting %s finished. %f seconds", harvester.name(), Duration.between(start, Instant.now()).toMillis() / 1000.0));
        }
        logger.info(String.format("Harvest finished. %s seconds", Duration.between(allHarvsetStart, Instant.now()).toMillis() / 1000.0));
    }

}

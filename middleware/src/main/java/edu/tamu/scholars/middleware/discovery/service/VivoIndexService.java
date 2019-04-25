package edu.tamu.scholars.middleware.discovery.service;

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
public class VivoIndexService {

    private final static Logger logger = LoggerFactory.getLogger(VivoIndexService.class);

    private boolean indexing = false;

    @Autowired
    private List<SolrIndexService> indexers;

    @Scheduled(fixedDelayString = "${middleware.index.fixedDelay}", initialDelayString = "${middleware.index.initialDelay}")
    public void harvest() {
        if (indexing) {
            logger.info("Already indexing. Waiting for next schedule.");
            return;
        }
        indexing = true;
        Instant allIndexStart = Instant.now();
        indexers.stream().forEach(indexer -> {
            logger.info(String.format("Indexing %s", indexer.name()));
            Instant indexStart = Instant.now();
            indexer.index();
            logger.info(String.format("Indexing %s finished. %f seconds", indexer.name(), Duration.between(indexStart, Instant.now()).toMillis() / 1000.0));
        });
        logger.info(String.format("Indexing finished. %s seconds", Duration.between(allIndexStart, Instant.now()).toMillis() / 1000.0));
        indexing = false;
    }

}

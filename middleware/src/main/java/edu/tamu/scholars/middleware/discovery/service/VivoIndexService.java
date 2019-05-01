package edu.tamu.scholars.middleware.discovery.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.tamu.scholars.middleware.service.Triplestore;

@Service
@Profile("!test")
public class VivoIndexService {

    private final static Logger logger = LoggerFactory.getLogger(VivoIndexService.class);

    private final static AtomicBoolean indexing = new AtomicBoolean(false);

    @Value("${middleware.index.onStartup:false}")
    public boolean indexOnStartup;

    @Autowired
    private List<SolrIndexService> indexers;

    @Autowired
    private Triplestore triplestore;

    @PostConstruct
    public void indexOnStartup() {
        if (indexOnStartup) {
            CompletableFuture.runAsync(() -> index());
        }
    }

    @Scheduled(cron = "${middleware.index.cron}", zone = "${middleware.index.zone}")
    public void index() {
        if (indexing.compareAndSet(false, true)) {
            Instant start = Instant.now();
            logger.info("Indexing...");
            logger.info("Loading dataset...");
            triplestore.init();
            logger.info(String.format("Loading finished. %s seconds", Duration.between(start, Instant.now()).toMillis() / 1000.0));
            indexers.stream().forEach(indexer -> {
                logger.info(String.format("Indexing %s documents", indexer.name()));
                Instant indexStart = Instant.now();
                indexer.index();
                logger.info(String.format("Indexing %s documents finished. %f seconds", indexer.name(), Duration.between(indexStart, Instant.now()).toMillis() / 1000.0));
            });
            logger.info(String.format("Indexing finished. %s seconds", Duration.between(start, Instant.now()).toMillis() / 1000.0));
            triplestore.destroy();
            indexing.set(false);
        } else {
            logger.info("Already indexing. Waiting for next schedule.");
        }
    }

}

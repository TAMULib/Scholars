package edu.tamu.scholars.middleware.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import edu.tamu.scholars.middleware.service.SDBTriplestore;
import edu.tamu.scholars.middleware.service.Triplestore;

@Configuration
public class HarvestConfig {

    @Autowired
    private TriplestoreConfig triplestoreConfig;

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Triplestore tirplestore() throws Exception {
        switch (triplestoreConfig.getType()) {
        case SDB:
            return new SDBTriplestore();
        case TDB:
            throw new Exception("TDB is not yet supported!");
        default:
            throw new Exception("Unknown triplestore type!");
        }
    }

}

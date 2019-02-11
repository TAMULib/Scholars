package edu.tamu.scholars.middleware.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import edu.tamu.scholars.middleware.service.SDBTriplestore;
import edu.tamu.scholars.middleware.service.TDBTriplestore;
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
            return new TDBTriplestore();
        default:
            throw new Exception(String.format("%s is not a valid triplestore type!", triplestoreConfig.getType()));
        }
    }

}

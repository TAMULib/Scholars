package edu.tamu.scholars.middleware.config;

import static org.springframework.data.solr.core.schema.SolrPersistentEntitySchemaCreator.Feature.CREATE_MISSING_FIELDS;

import java.util.Collections;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@Profile("!test")
@EnableSolrRepositories(basePackages = { "edu.tamu.scholars.middleware.discovery" }, schemaCreationSupport = true)
public class SolrConfig {

    @Value("${spring.data.solr.host:http://localhost:8983/solr}")
    private String solrHost;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrHost).build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        SolrTemplate solrTemplate = new SolrTemplate(client);
        solrTemplate.setSchemaCreationFeatures(Collections.singletonList(CREATE_MISSING_FIELDS));
        solrTemplate.afterPropertiesSet();
        return solrTemplate;
    }

}

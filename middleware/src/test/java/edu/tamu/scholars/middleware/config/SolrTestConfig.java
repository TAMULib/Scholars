package edu.tamu.scholars.middleware.config;

import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactoryBean;

@Configuration
@Profile("test")
@EnableSolrRepositories(basePackages = "edu.tamu.scholars.middleware.discovery", schemaCreationSupport = true)
public class SolrTestConfig {

    @Bean
    public EmbeddedSolrServer solrServer() throws Exception {
        EmbeddedSolrServerFactoryBean factory = new EmbeddedSolrServerFactoryBean();
        factory.setSolrHome("classpath:solr");
        return factory.getObject();
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {
        return new SolrTemplate(solrServer());
    }

}

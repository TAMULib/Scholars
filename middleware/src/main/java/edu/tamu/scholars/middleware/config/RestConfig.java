package edu.tamu.scholars.middleware.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(SolrDocument.class));
        List<Class<?>> solrDocumentTypes = new ArrayList<Class<?>>();
        for (BeanDefinition beanDefinition : provider.findCandidateComponents("edu.tamu.scholars.middleware")) {
            try {
                Class<?> solrDocumentType = Class.forName(beanDefinition.getBeanClassName());
                solrDocumentTypes.add(solrDocumentType);
            } catch (ClassNotFoundException e) {

            }
        }
        config.exposeIdsFor(solrDocumentTypes.toArray(new Class<?>[solrDocumentTypes.size()]));
    }

}

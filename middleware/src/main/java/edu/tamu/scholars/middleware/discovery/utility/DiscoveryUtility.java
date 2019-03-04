package edu.tamu.scholars.middleware.discovery.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.solr.core.mapping.SolrDocument;

import edu.tamu.scholars.middleware.discovery.annotation.PropertySource;

public class DiscoveryUtility {

    private static final String DISCOVERY_MODEL_PACKAGE = "edu.tamu.scholars.middleware.discovery.model";

    public static List<String> getFields(String collection) {
        List<String> fields = new ArrayList<String>();
        for (BeanDefinition beanDefinition : getSolrDocumentBeanDefinitions()) {
            try {
                Class<?> type = Class.forName(beanDefinition.getBeanClassName());
                for (Field field : FieldUtils.getFieldsListWithAnnotation(type, PropertySource.class)) {
                    fields.add(field.getName());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return fields;
    }

    public static boolean hasIndexField(String collection, String field) {
        Optional<Class<?>> type = getCollectionType(collection);
        if (type.isPresent()) {
            for (Field f : FieldUtils.getFieldsListWithAnnotation(type.get(), PropertySource.class)) {
                if (String.class.isAssignableFrom(f.getType()) && f.getName().equals(field)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCollection(String collection) {
        for (BeanDefinition beanDefinition : getSolrDocumentBeanDefinitions()) {
            try {
                Class<?> type = Class.forName(beanDefinition.getBeanClassName());
                SolrDocument solrDocument = type.getAnnotation(SolrDocument.class);
                if (collection.equals(solrDocument.collection())) {
                    return true;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static Set<BeanDefinition> getSolrDocumentBeanDefinitions() {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(SolrDocument.class));
        return provider.findCandidateComponents(DISCOVERY_MODEL_PACKAGE);
    }

    private static Optional<Class<?>> getCollectionType(String collection) {
        for (BeanDefinition beanDefinition : getSolrDocumentBeanDefinitions()) {
            try {
                Class<?> type = Class.forName(beanDefinition.getBeanClassName());
                SolrDocument solrDocument = type.getAnnotation(SolrDocument.class);
                if (collection.equals(solrDocument.collection())) {
                    return Optional.of(type);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

}

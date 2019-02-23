package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.result.FacetPage;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.custom.SolrDocumentRepoCustom;

public abstract class AbstractSolrDocumentRepoImpl<D extends AbstractSolrDocument> implements SolrDocumentRepoCustom<D> {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public FacetPage<D> search(String query, String[] fields, Pageable page) {
        FacetQuery facetQuery = new SimpleFacetQuery();
        Criteria criteria = new Criteria();
        if (query != null) {
            criteria = new Criteria();
            for (Field field : FieldUtils.getFieldsWithAnnotation(type(), Indexed.class)) {
                Indexed indexed = field.getAnnotation(Indexed.class);
                if (indexed.type().equals("string") || (indexed.type().isEmpty() && String.class.isAssignableFrom(field.getType()))) {
                    criteria = criteria.or(new Criteria(field.getName()).contains(query));
                }
            }
        } else {
            criteria = new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD);
        }
        facetQuery.addCriteria(criteria);
        if (fields != null) {
            facetQuery.setFacetOptions(new FacetOptions(fields));
        }
        facetQuery.setPageRequest(page);
        facetQuery.setDefaultOperator(Operator.OR);
        return solrTemplate.queryForFacetPage(collection(), facetQuery, type());
    }

    public abstract String collection();

    public abstract Class<D> type();

}

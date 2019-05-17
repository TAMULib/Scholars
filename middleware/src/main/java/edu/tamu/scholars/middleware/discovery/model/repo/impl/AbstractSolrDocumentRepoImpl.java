package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import static org.springframework.data.solr.core.query.Criteria.WILDCARD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Criteria.OperationKey;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FieldWithFacetParameters;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.util.MultiValueMap;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.custom.SolrDocumentRepoCustom;

public abstract class AbstractSolrDocumentRepoImpl<D extends AbstractSolrDocument> implements SolrDocumentRepoCustom<D> {

    private static final String INDEX_QUERY_PARAM_DELIMETER = ",";
    private static final String FILTER_TEMPLATE = "%s.filter";

    private static final int LIMIT = Integer.MAX_VALUE;
    private static final int OFFSET = 0;

    @Value("${spring.data.solr.parser:edismax}")
    private String queryParser;

    @Value("${spring.data.solr.operator:AND}")
    private Operator queryOperator;

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public FacetPage<D> search(String query, String index, String[] facets, MultiValueMap<String, String> params, Pageable page) {
        FacetQuery facetQuery = new SimpleFacetQuery();

        if (query != null) {
            facetQuery.addCriteria(new SimpleStringCriteria(query));
        } else {
            facetQuery.addCriteria(new Criteria(WILDCARD).expression(WILDCARD));
        }

        if (index != null) {
            String[] indexParts = index.split(INDEX_QUERY_PARAM_DELIMETER);
            // NOTE: to support all operation keys additional values will have to be included in the index query parameter
            // TODO: if invalid index query parameter, consider an argument resolver into Indexable class
            if (indexParts.length == 3) {
                Criteria criteria = buildCriteria(indexParts);
                FilterQuery filterQuery = new SimpleFilterQuery(criteria);
                facetQuery.addFilterQuery(filterQuery);
            }
        }

        if (facets != null) {

            FacetOptions facetOptions = new FacetOptions();
            for (String facet : facets) {
                FieldWithFacetParameters fieldWithFacetParameters = new FieldWithFacetParameters(facet);

                fieldWithFacetParameters.setLimit(LIMIT);

                fieldWithFacetParameters.setOffset(OFFSET);

                fieldWithFacetParameters.setSort(FacetOptions.FacetSort.COUNT);

                // NOTE: other possible; method, minCount, missing, and prefix

                facetOptions.addFacetOnField(fieldWithFacetParameters);

                List<String> filters = params.get(String.format(FILTER_TEMPLATE, facet));
                if (filters != null) {
                    for (String filter : filters) {
                        FilterQuery filterQuery = new SimpleFilterQuery(new Criteria(facet).is(filter));
                        facetQuery.addFilterQuery(filterQuery);
                    }
                }

            }

            facetQuery.setFacetOptions(facetOptions);
        }

        facetQuery.setDefaultOperator(queryOperator);

        facetQuery.setDefType(queryParser);

        facetQuery.setPageRequest(page);

        return solrTemplate.queryForFacetPage(collection(), facetQuery, type());
    }

    @Override
    public long count(String query, String[] fields, MultiValueMap<String, String> params) {
        SimpleQuery simpleQuery = new SimpleQuery();

        if (query != null) {
            simpleQuery.addCriteria(new SimpleStringCriteria(query));
        } else {
            simpleQuery.addCriteria(new Criteria(WILDCARD).expression(WILDCARD));
        }

        if (fields != null) {
            for (String field : fields) {
                List<String> filters = params.get(String.format(FILTER_TEMPLATE, field));
                if (filters != null) {
                    for (String filter : filters) {
                        FilterQuery filterQuery = new SimpleFilterQuery(new Criteria(field).is(filter));
                        simpleQuery.addFilterQuery(filterQuery);
                    }
                }
            }
        }

        simpleQuery.setDefaultOperator(queryOperator);

        simpleQuery.setDefType(queryParser);

        return solrTemplate.count(collection(), simpleQuery, type());
    }

    public abstract String collection();

    public abstract Class<D> type();

    private Criteria buildCriteria(String[] indexParts) {
        String field = indexParts[0];
        String option = indexParts[2];
        OperationKey operationKey = OperationKey.valueOf(indexParts[1]);
        Criteria criteria = new Criteria(field);
        switch (operationKey) {
        case BETWEEN:
            // NOTE: not supported yet
            break;
        case CONTAINS:
            criteria.contains(option);
            break;
        case ENDS_WITH:
            criteria.endsWith(option);
            break;
        case EQUALS:
            criteria.is(option);
            break;
        case EXPRESSION:
            criteria.expression(option);
            break;
        case FUNCTION:
            // NOTE: not supported
            break;
        case FUZZY:
            // NOTE: more arguments can be used for fuzzy compare
            criteria.fuzzy(option);
            break;
        case NEAR:
            // NOTE: not supported yet
            break;
        case SLOPPY:
            // NOTE: not supported
            break;
        case STARTS_WITH:
            criteria.startsWith(option);
            break;
        case WITHIN:
            // NOTE: not supported yet
            break;
        default:
            break;
        }
        return criteria;
    }

}

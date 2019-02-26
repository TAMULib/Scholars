package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FieldWithFacetParameters;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.util.MultiValueMap;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.custom.SolrDocumentRepoCustom;

public abstract class AbstractSolrDocumentRepoImpl<D extends AbstractSolrDocument> implements SolrDocumentRepoCustom<D> {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public FacetPage<D> search(String query, String[] facets, MultiValueMap<String, String> params, Pageable page) {
        FacetQuery facetQuery = new SimpleFacetQuery();

        // TODO: implement search query
        Criteria criteria = new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD);
        facetQuery.addCriteria(criteria);

        if (facets != null) {

            FacetOptions facetOptions = new FacetOptions();
            for (String facet : facets) {
                FieldWithFacetParameters fieldWithFacetParameters = new FieldWithFacetParameters(facet);

                String limit = params.getFirst(String.format("%s.limit", facet));
                if (limit != null) {
                    fieldWithFacetParameters.setLimit(Integer.parseInt(limit));
                }

                String method = params.getFirst(String.format("%s.method", facet));
                if (method != null) {
                    fieldWithFacetParameters.setMethod(method);
                }

                String minCount = params.getFirst(String.format("%s.mincount", facet));
                if (minCount != null) {
                    fieldWithFacetParameters.setMinCount(Integer.parseInt(minCount));
                }

                String missing = params.getFirst(String.format("%s.missing", facet));
                if (missing != null) {
                    fieldWithFacetParameters.setMissing(Boolean.parseBoolean(missing));
                }

                String offset = params.getFirst(String.format("%s.offset", facet));
                if (offset != null) {
                    fieldWithFacetParameters.setOffset(Integer.parseInt(offset));
                }

                String prefix = params.getFirst(String.format("%s.prefix", facet));
                if (prefix != null) {
                    fieldWithFacetParameters.setPrefix(prefix);
                }

                // COUNT, INDEX
                String sort = params.getFirst(String.format("%s.sort", facet));
                if (sort != null) {
                    fieldWithFacetParameters.setSort(FacetOptions.FacetSort.valueOf(sort));
                }

                facetOptions.addFacetOnField(fieldWithFacetParameters);

            }

            facetQuery.setFacetOptions(facetOptions);
        }

        // AND, NONE, OR
        String op = params.getFirst("op");
        facetQuery.setDefaultOperator(Operator.valueOf(op != null ? op.toUpperCase() : "OR"));

        facetQuery.setPageRequest(page);

        return solrTemplate.queryForFacetPage(collection(), facetQuery, type());
    }

    public abstract String collection();

    public abstract Class<D> type();

}

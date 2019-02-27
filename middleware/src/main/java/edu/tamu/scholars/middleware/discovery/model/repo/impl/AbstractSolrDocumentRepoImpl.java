package edu.tamu.scholars.middleware.discovery.model.repo.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FieldWithFacetParameters;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.Query.Operator;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.util.MultiValueMap;

import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.custom.SolrDocumentRepoCustom;

public abstract class AbstractSolrDocumentRepoImpl<D extends AbstractSolrDocument> implements SolrDocumentRepoCustom<D> {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private SolrClient solrClient;

    @Override
    public FacetPage<D> search(String query, String[] facets, MultiValueMap<String, String> params, Pageable page) {
        FacetQuery facetQuery = new SimpleFacetQuery();

        Criteria criteria = new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD);

        if (query != null) {

            criteria = new Criteria();

            LukeRequest luke = new LukeRequest();
            luke.setPath(String.format("/%s/admin/luke", collection()));
            luke.setNumTerms(0);
            LukeResponse lr = null;

            try {
                lr = luke.process(solrClient);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<String, FieldInfo> map = lr.getFieldInfo();

            for (Entry<String, FieldInfo> entry : map.entrySet()) {

                String field = entry.getKey();
                FieldInfo fieldInfo = entry.getValue();

                int docs = fieldInfo.getDocs();

                String type = fieldInfo.getType();

                if (docs > 0 && type.equals("string")) {
                    criteria = criteria.or(new Criteria(field).contains(query));

                }

            }

        }

        facetQuery.addCriteria(criteria);

        if (facets != null) {

            FacetOptions facetOptions = new FacetOptions();
            for (String facet : facets) {
                FieldWithFacetParameters fieldWithFacetParameters = new FieldWithFacetParameters(facet);

                String limit = params.getFirst(String.format("%s.limit", facet));
                if (limit != null) {
                    fieldWithFacetParameters.setLimit(Integer.parseInt(limit));
                }

                String offset = params.getFirst(String.format("%s.offset", facet));
                if (offset != null) {
                    fieldWithFacetParameters.setOffset(Integer.parseInt(offset));
                }

                // COUNT, INDEX
                String sort = params.getFirst(String.format("%s.sort", facet));
                if (sort != null) {
                    fieldWithFacetParameters.setSort(FacetOptions.FacetSort.valueOf(sort.toUpperCase()));
                }

                // NOTE: other possible; method, minCount, missing, and prefix

                facetOptions.addFacetOnField(fieldWithFacetParameters);

                List<String> filters = params.get(String.format("%s.filter", facet));
                if (filters != null) {
                    for (String filter : filters) {
                        FilterQuery filterQuery = new SimpleFilterQuery(new Criteria(facet).expression(filter));
                        facetQuery.addFilterQuery(filterQuery);
                    }
                }

            }

            facetQuery.setFacetOptions(facetOptions);
        }

        facetQuery.setDefaultOperator(Operator.AND);

        facetQuery.setDefType("edismax");

        facetQuery.setPageRequest(page);

        return solrTemplate.queryForFacetPage(collection(), facetQuery, type());
    }

    public abstract String collection();

    public abstract Class<D> type();

}

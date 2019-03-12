package edu.tamu.scholars.middleware.view.assembler;

import static org.springframework.data.solr.core.query.Criteria.WILDCARD;
import static org.springframework.data.solr.core.query.Query.Operator.AND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetOptions.FieldWithFacetParameters;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import edu.tamu.scholars.middleware.discovery.model.SdrFacetEntry;
import edu.tamu.scholars.middleware.view.model.CollectionView;
import edu.tamu.scholars.middleware.view.model.Facet;
import edu.tamu.scholars.middleware.view.resource.AbstractCollectionViewResource;

public abstract class AbstractCollectionViewResourceAssembler<V extends CollectionView, R extends AbstractCollectionViewResource<V>> extends ResourceAssemblerSupport<V, R> {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private RepositoryEntityLinks repositoryEntityLinks;

    public AbstractCollectionViewResourceAssembler(Class<?> controllerClass, Class<R> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public R toResource(V view) {
        Link viewLink = repositoryEntityLinks.linkToSingleResource(view.getClass(), view.getId());
        Link selfLink = new Link(viewLink.getHref(), Link.REL_SELF);
        return createResource(view, Arrays.asList(selfLink, viewLink));
    }

    protected abstract R createResource(V view, Iterable<Link> links);

    protected V enrich(V view) {
        FacetQuery facetQuery = new SimpleFacetQuery();
        facetQuery.addCriteria(new Criteria(WILDCARD).expression(WILDCARD));

        FacetOptions facetOptions = new FacetOptions();
        view.getFacets().forEach(facet -> {
            FieldWithFacetParameters fieldWithFacetParameters = new FieldWithFacetParameters(facet.getField());

            // TODO: need better solution for limit and paging facets, unfortunately totalElements is not accurate on FacetPage response
            fieldWithFacetParameters.setLimit(1000);

            fieldWithFacetParameters.setOffset(0);

            fieldWithFacetParameters.setSort(facet.getSort());

            facetOptions.addFacetOnField(fieldWithFacetParameters);

        });

        facetQuery.setFacetOptions(facetOptions);

        facetQuery.setDefaultOperator(AND);

        facetQuery.setDefType("edismax");

        facetQuery.setPageRequest(PageRequest.of(1, 1));

        FacetPage<?> facetPage = solrTemplate.queryForFacetPage(view.getCollection(), facetQuery, getSolrDocumentType(view.getCollection()));

        facetPage.getFacetResultPages().forEach(facetFieldEntryPage -> {

            Optional<String> field = Optional.empty();

            List<SdrFacetEntry> entries = new ArrayList<SdrFacetEntry>();
            Pageable page = facetFieldEntryPage.getPageable();

            for (FacetFieldEntry facetFieldEntry : facetFieldEntryPage.getContent()) {
                if (!field.isPresent()) {
                    field = Optional.of(facetFieldEntry.getField().getName());
                }
                entries.add(new SdrFacetEntry(facetFieldEntry.getValue(), facetFieldEntry.getValueCount()));
            }

            if (field.isPresent()) {
                for (Facet facet : view.getFacets()) {
                    if (facet.getField().equals(field.get())) {
                        facet.setEntries(entries);
                        facet.setPage(page);
                        break;
                    }
                }
            }

        });

        return view;
    }

    // Move to static utility or cache using @Cached
    private Class<?> getSolrDocumentType(String collection) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(SolrDocument.class));
        for (BeanDefinition beanDefinition : provider.findCandidateComponents("edu.tamu.scholars.middleware.discovery.model")) {
            try {
                Class<?> type = Class.forName(beanDefinition.getBeanClassName());
                SolrDocument solrDocument = type.getAnnotation(SolrDocument.class);
                if (solrDocument.collection().equals(collection)) {
                    return type;
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException(String.format("Unable to find solr document type for collection %s!", collection));
    }

}

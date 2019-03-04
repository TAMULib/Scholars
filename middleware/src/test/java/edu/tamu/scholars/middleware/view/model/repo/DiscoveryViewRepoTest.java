package edu.tamu.scholars.middleware.view.model.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.Facet;
import edu.tamu.scholars.middleware.view.model.Filter;
import edu.tamu.scholars.middleware.view.model.Layout;
import edu.tamu.scholars.middleware.view.model.ResultView;

public class DiscoveryViewRepoTest extends CollectionViewRepoTest<DiscoveryView, DiscoveryViewRepo> {

    @TestConfiguration
    static class DiscoveryViewRepoTestContextConfiguration {

        @Bean
        public MiddlewareConfig middlewareConfig() {
            return new MiddlewareConfig();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }

    @Override
    protected DiscoveryView getMockView() {
        DiscoveryView discoveryView = new DiscoveryView();

        discoveryView.setName("People");
        discoveryView.setCollection("persons");
        discoveryView.setLayout(Layout.GRID);

        ResultView resultView = new ResultView();

        resultView.setName("People");
        resultView.setTemplate("<h1>Person template from WSYWIG</h1>");

        discoveryView.setResultView(resultView);

        List<Facet> facets = new ArrayList<Facet>();

        Facet facet = new Facet();

        facet.setName("Name");
        facet.setField("name");
        facet.setLimit(20);
        facet.setSort(FacetSort.INDEX);

        facets.add(facet);

        discoveryView.setFacets(facets);

        List<Filter> filters = new ArrayList<Filter>();

        Filter filter = new Filter();

        filter.setField("type");
        filter.setValue("FacultyMember");

        filters.add(filter);

        discoveryView.setFilters(filters);

        return discoveryView;
    }

}

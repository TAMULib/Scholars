package edu.tamu.scholars.middleware.view.model.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.parser.Part.Type;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.tamu.scholars.middleware.config.MiddlewareConfig;
import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.Facet;
import edu.tamu.scholars.middleware.view.model.Filter;
import edu.tamu.scholars.middleware.view.model.Index;
import edu.tamu.scholars.middleware.view.model.Layout;

public class DirectoryViewRepoTest extends ViewRepoTest<DirectoryView, DirectoryViewRepo> {

    @TestConfiguration
    static class DirectoryViewRepoTestContextConfiguration {

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
    protected DirectoryView getMockView() {
        DirectoryView directoryView = new DirectoryView();

        directoryView.setName("People");
        directoryView.setCollection("persons");
        directoryView.setLayout(Layout.LIST);

        List<Facet> facets = new ArrayList<Facet>();

        Facet facet = new Facet();

        facet.setName("Name");
        facet.setField("name");
        facet.setLimit(20);
        facet.setSort(FacetSort.INDEX);

        facets.add(facet);

        directoryView.setFacets(facets);

        List<Filter> filters = new ArrayList<Filter>();

        Filter filter = new Filter();

        filter.setField("type");
        filter.setValue("FacultyMember");

        filters.add(filter);

        directoryView.setFilters(filters);

        Index index = new Index();

        index.setField("name");
        index.setType(Type.STARTING_WITH);

        directoryView.setIndex(index);

        return directoryView;
    }

}

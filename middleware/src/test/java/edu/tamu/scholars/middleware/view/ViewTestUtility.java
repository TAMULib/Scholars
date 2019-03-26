package edu.tamu.scholars.middleware.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.Criteria.OperationKey;
import org.springframework.data.solr.core.query.FacetOptions;

import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.Facet;
import edu.tamu.scholars.middleware.view.model.Filter;
import edu.tamu.scholars.middleware.view.model.Index;
import edu.tamu.scholars.middleware.view.model.Layout;

public class ViewTestUtility {

    public static DirectoryView getMockDirectoryView() {
        DirectoryView directoryView = new DirectoryView();

        directoryView.setName("People");
        directoryView.setCollection("persons");
        directoryView.setLayout(Layout.LIST);

        Map<String, String> templates = new HashMap<String, String>();
        templates.put("default", "<h1>Person template from WSYWIG</h1>");

        directoryView.setTemplates(templates);

        List<String> styles = new ArrayList<String>();

        styles.add("color: maroon;");

        directoryView.setStyles(styles);

        List<Facet> facets = new ArrayList<Facet>();

        Facet facet = new Facet();

        facet.setName("Name");
        facet.setField("name");
        facet.setLimit(20);
        facet.setSort(FacetOptions.FacetSort.COUNT);
        facet.setDirection(Sort.Direction.DESC);

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
        index.setOperationKey(OperationKey.ENDS_WITH);

        directoryView.setIndex(index);

        return directoryView;
    }

    public static DiscoveryView getMockDiscoveryView() {
        DiscoveryView discoveryView = new DiscoveryView();

        discoveryView.setName("People");
        discoveryView.setCollection("persons");
        discoveryView.setLayout(Layout.GRID);

        Map<String, String> templates = new HashMap<String, String>();
        templates.put("default", "<h1>Person template from WSYWIG</h1>");

        discoveryView.setTemplates(templates);

        List<String> styles = new ArrayList<String>();

        styles.add("color: maroon;");

        discoveryView.setStyles(styles);

        List<Facet> facets = new ArrayList<Facet>();

        Facet facet = new Facet();

        facet.setName("Name");
        facet.setField("name");
        facet.setLimit(20);
        facet.setSort(FacetOptions.FacetSort.COUNT);
        facet.setDirection(Sort.Direction.DESC);

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

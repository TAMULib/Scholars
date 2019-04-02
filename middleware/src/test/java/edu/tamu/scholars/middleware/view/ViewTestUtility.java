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
import edu.tamu.scholars.middleware.view.model.DisplaySection;
import edu.tamu.scholars.middleware.view.model.DisplayView;
import edu.tamu.scholars.middleware.view.model.Facet;
import edu.tamu.scholars.middleware.view.model.Filter;
import edu.tamu.scholars.middleware.view.model.Index;
import edu.tamu.scholars.middleware.view.model.Layout;
import edu.tamu.scholars.middleware.view.model.TabView;

public class ViewTestUtility {

    public static String MOCK_VIEW_NAME = "People";

    public static DirectoryView getMockDirectoryView() {
        DirectoryView directoryView = new DirectoryView();

        directoryView.setName(MOCK_VIEW_NAME);
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

        discoveryView.setName(MOCK_VIEW_NAME);
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

    public static DisplayView getMockDisplayView() {
        DisplayView displayView = new DisplayView();

        displayView.setName(MOCK_VIEW_NAME);
        displayView.setType("FacultyMember");
        displayView.setCollection("persons");
        displayView.setMainContentTemplate("<div>Main</div>");
        displayView.setLeftScanTemplate("<div>Left Scan</div>");
        displayView.setRightScanTemplate("<div>Right Scan</div>");

        List<TabView> tabs = new ArrayList<TabView>();

        tabs.add(getMockTabView());

        displayView.setTabs(tabs);

        return displayView;
    }

    public static TabView getMockTabView() {
        TabView tabView = new TabView();
        tabView.setName("Test");

        List<DisplaySection> sections = new ArrayList<DisplaySection>();

        DisplaySection displaySection = new DisplaySection();
        displaySection.setName("Test");
        displaySection.setTemplate("<span>Hello, World!</span>");

        sections.add(displaySection);

        tabView.setSections(sections);

        return tabView;
    }

}

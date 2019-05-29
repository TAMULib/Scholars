package edu.tamu.scholars.middleware.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.solr.core.query.Criteria.OperationKey;
import org.springframework.data.solr.core.query.FacetOptions;

import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.DisplayTabSectionSubsectionView;
import edu.tamu.scholars.middleware.view.model.DisplayTabSectionView;
import edu.tamu.scholars.middleware.view.model.DisplayTabView;
import edu.tamu.scholars.middleware.view.model.DisplayView;
import edu.tamu.scholars.middleware.view.model.Facet;
import edu.tamu.scholars.middleware.view.model.FacetType;
import edu.tamu.scholars.middleware.view.model.Filter;
import edu.tamu.scholars.middleware.view.model.Index;
import edu.tamu.scholars.middleware.view.model.Layout;
import edu.tamu.scholars.middleware.view.model.LazyReference;
import edu.tamu.scholars.middleware.view.model.Side;
import edu.tamu.scholars.middleware.view.model.Sort;

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
        facet.setType(FacetType.STRING);
        facet.setSort(FacetOptions.FacetSort.COUNT);
        facet.setDirection(Direction.DESC);
        facet.setLimit(20);

        facets.add(facet);

        directoryView.setFacets(facets);

        List<Filter> filters = new ArrayList<Filter>();

        Filter filter = new Filter();

        filter.setField("type");
        filter.setValue("FacultyMember");

        filters.add(filter);

        directoryView.setFilters(filters);

        List<Sort> sorting = new ArrayList<Sort>();

        Sort sort = new Sort();
        sort.setField("name");
        sort.setDirection(Direction.ASC);

        sorting.add(sort);

        directoryView.setSort(sorting);

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
        facet.setType(FacetType.STRING);
        facet.setSort(FacetOptions.FacetSort.COUNT);
        facet.setDirection(Direction.DESC);
        facet.setLimit(20);

        facets.add(facet);

        discoveryView.setFacets(facets);

        List<Filter> filters = new ArrayList<Filter>();

        Filter filter = new Filter();

        filter.setField("type");
        filter.setValue("FacultyMember");

        filters.add(filter);

        discoveryView.setFilters(filters);

        List<Sort> sorting = new ArrayList<Sort>();

        Sort sort = new Sort();
        sort.setField("name");
        sort.setDirection(Direction.ASC);

        sorting.add(sort);

        discoveryView.setSort(sorting);

        return discoveryView;
    }

    public static DisplayView getMockDisplayView() {
        DisplayView displayView = new DisplayView();

        displayView.setName(MOCK_VIEW_NAME);
        displayView.setMainContentTemplate("<div>Main</div>");
        displayView.setLeftScanTemplate("<div>Left Scan</div>");
        displayView.setRightScanTemplate("<div>Right Scan</div>");
        displayView.setAsideTemplate("<div>Aside</div>");
        displayView.setAsideLocation(Side.RIGHT);

        List<String> types = new ArrayList<String>();
        types.add("FacultyMember");

        displayView.setTypes(types);

        Map<String, String> metaTemplates = new HashMap<String, String>();
        metaTemplates.put("default", "Meta tag template");

        displayView.setMetaTemplates(metaTemplates);

        List<DisplayTabView> tabs = new ArrayList<DisplayTabView>();

        tabs.add(getMockDisplayTabView());

        displayView.setTabs(tabs);

        return displayView;
    }

    public static DisplayTabView getMockDisplayTabView() {
        DisplayTabView tab = new DisplayTabView();
        tab.setName("Test");

        List<DisplayTabSectionView> sections = new ArrayList<DisplayTabSectionView>();

        DisplayTabSectionView section = new DisplayTabSectionView();
        section.setName("Test");
        section.setTemplate("<span>Hello, World!</span>");

        List<String> requiredFields = new ArrayList<String>();
        requiredFields.add("type");

        section.setRequiredFields(requiredFields);

        LazyReference lazyReference = new LazyReference();

        lazyReference.setField("publications");
        lazyReference.setCollection("documents");

        List<LazyReference> lazyReferences = new ArrayList<LazyReference>();
        lazyReferences.add(lazyReference);

        section.setLazyReferences(lazyReferences);

        DisplayTabSectionSubsectionView subsection = new DisplayTabSectionSubsectionView();

        subsection.setName("Test");
        subsection.setField("publications");

        Filter filter = new Filter();
        filter.setField("type");
        filter.setValue("Test");

        List<Filter> filters = new ArrayList<Filter>();
        filters.add(filter);

        subsection.setFilters(filters);

        Sort sort = new Sort();
        sort.setField("date");
        sort.setDirection(Direction.DESC);

        List<Sort> sorting = new ArrayList<Sort>();
        sorting.add(sort);

        subsection.setSort(sorting);

        subsection.setTemplate("<div>Subsection</div>");

        List<DisplayTabSectionSubsectionView> subsections = new ArrayList<DisplayTabSectionSubsectionView>();
        subsections.add(subsection);

        section.setSubsections(subsections);

        sections.add(section);

        tab.setSections(sections);

        return tab;
    }

}

package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DiscoveryViewTest {

    @Test
    public void testDefaultConstructor() {
        DiscoveryView discoveryView = new DiscoveryView();
        assertNotNull(discoveryView);
        assertNotNull(discoveryView.getFacets());
        assertNotNull(discoveryView.getFilters());
        assertTrue(discoveryView.getFacets().isEmpty());
        assertTrue(discoveryView.getFilters().isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        DiscoveryView discoveryView = new DiscoveryView();

        discoveryView.setId(1L);
        discoveryView.setName("People");
        discoveryView.setCollection("persons");
        discoveryView.setLayout(Layout.LIST);

        assertEquals(1L, discoveryView.getId(), 1);
        assertEquals("People", discoveryView.getName());
        assertEquals("persons", discoveryView.getCollection());
        assertEquals(Layout.LIST, discoveryView.getLayout());

        List<Facet> facets = new ArrayList<Facet>();

        Facet facet = new Facet();

        facet.setName("Name");
        facet.setField("name");
        facet.setLimit(20);
        facet.setSort(FacetSort.INDEX);

        facets.add(facet);

        discoveryView.setFacets(facets);

        assertEquals(1, discoveryView.getFacets().size());
        assertEquals("Name", discoveryView.getFacets().get(0).getName());
        assertEquals("name", discoveryView.getFacets().get(0).getField());
        assertEquals(20, discoveryView.getFacets().get(0).getLimit());
        assertEquals(FacetSort.INDEX, discoveryView.getFacets().get(0).getSort());

        List<Filter> filters = new ArrayList<Filter>();

        Filter filter = new Filter();

        filter.setField("type");
        filter.setValue("FacultyMember");

        filters.add(filter);

        discoveryView.setFilters(filters);

        assertEquals(1, discoveryView.getFilters().size());
        assertEquals("type", discoveryView.getFilters().get(0).getField());
        assertEquals("FacultyMember", discoveryView.getFilters().get(0).getValue());
    }

}

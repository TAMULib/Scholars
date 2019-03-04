package edu.tamu.scholars.middleware.view.model;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDiscoveryView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        DiscoveryView discoveryView = getMockDiscoveryView();
        discoveryView.setId(1L);
        
        discoveryView.getResultView().setId(1L);

        assertEquals(1L, discoveryView.getId(), 1);
        assertEquals("People", discoveryView.getName());
        assertEquals("persons", discoveryView.getCollection());
        assertEquals(Layout.GRID, discoveryView.getLayout());

        assertEquals(1L, discoveryView.getResultView().getId(), 1);
        assertEquals("People", discoveryView.getResultView().getName());
        assertEquals("<h1>Person template from WSYWIG</h1>", discoveryView.getResultView().getTemplate());

        assertEquals(1, discoveryView.getFacets().size());
        assertEquals("Name", discoveryView.getFacets().get(0).getName());
        assertEquals("name", discoveryView.getFacets().get(0).getField());
        assertEquals(20, discoveryView.getFacets().get(0).getLimit());
        assertEquals(FacetSort.INDEX, discoveryView.getFacets().get(0).getSort());

        assertEquals(1, discoveryView.getFilters().size());
        assertEquals("type", discoveryView.getFilters().get(0).getField());
        assertEquals("FacultyMember", discoveryView.getFilters().get(0).getValue());
    }

}

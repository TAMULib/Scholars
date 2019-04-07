package edu.tamu.scholars.middleware.view.model;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.MOCK_VIEW_NAME;
import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDiscoveryView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.solr.core.query.FacetOptions;
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

        assertEquals(1L, discoveryView.getId(), 1);
        assertEquals(MOCK_VIEW_NAME, discoveryView.getName());
        assertEquals("persons", discoveryView.getCollection());
        assertEquals(Layout.GRID, discoveryView.getLayout());

        assertTrue(discoveryView.getTemplates().containsKey("default"));
        assertEquals("<h1>Person template from WSYWIG</h1>", discoveryView.getTemplates().get("default"));

        assertEquals(1, discoveryView.getStyles().size());
        assertEquals("color: maroon;", discoveryView.getStyles().get(0));

        assertEquals(1, discoveryView.getFacets().size());
        assertEquals("Name", discoveryView.getFacets().get(0).getName());
        assertEquals("name", discoveryView.getFacets().get(0).getField());
        assertEquals(FacetType.STRING, discoveryView.getFacets().get(0).getType());
        assertEquals(20, discoveryView.getFacets().get(0).getLimit());
        assertEquals(FacetOptions.FacetSort.COUNT, discoveryView.getFacets().get(0).getSort());
        assertEquals(Sort.Direction.DESC, discoveryView.getFacets().get(0).getDirection());

        assertEquals(1, discoveryView.getFilters().size());
        assertEquals("type", discoveryView.getFilters().get(0).getField());
        assertEquals("FacultyMember", discoveryView.getFilters().get(0).getValue());

        assertEquals(1, discoveryView.getSort().size());
        assertEquals("name", discoveryView.getSort().get(0).getField());
        assertEquals(Direction.ASC, discoveryView.getSort().get(0).getDirection());
    }

}

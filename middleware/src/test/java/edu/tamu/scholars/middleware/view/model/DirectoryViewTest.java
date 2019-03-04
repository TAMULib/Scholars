package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.repository.query.parser.Part.Type;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class DirectoryViewTest {

    @Test
    public void testDefaultConstructor() {
        DirectoryView directoryView = new DirectoryView();
        assertNotNull(directoryView);
        assertNotNull(directoryView.getFacets());
        assertNotNull(directoryView.getFilters());
        assertTrue(directoryView.getFacets().isEmpty());
        assertTrue(directoryView.getFilters().isEmpty());
    }

    @Test
    public void testGettersAndSetters() {
        DirectoryView directoryView = new DirectoryView();

        directoryView.setId(1L);
        directoryView.setName("People");
        directoryView.setCollection("persons");
        directoryView.setLayout(Layout.LIST);

        assertEquals(1L, directoryView.getId(), 1);
        assertEquals("People", directoryView.getName());
        assertEquals("persons", directoryView.getCollection());
        assertEquals(Layout.LIST, directoryView.getLayout());

        List<Facet> facets = new ArrayList<Facet>();

        Facet facet = new Facet();

        facet.setName("Name");
        facet.setField("name");
        facet.setLimit(20);
        facet.setSort(FacetSort.INDEX);

        facets.add(facet);

        directoryView.setFacets(facets);

        assertEquals(1, directoryView.getFacets().size());
        assertEquals("Name", directoryView.getFacets().get(0).getName());
        assertEquals("name", directoryView.getFacets().get(0).getField());
        assertEquals(20, directoryView.getFacets().get(0).getLimit());
        assertEquals(FacetSort.INDEX, directoryView.getFacets().get(0).getSort());

        List<Filter> filters = new ArrayList<Filter>();

        Filter filter = new Filter();

        filter.setField("type");
        filter.setValue("FacultyMember");

        filters.add(filter);

        directoryView.setFilters(filters);

        assertEquals(1, directoryView.getFilters().size());
        assertEquals("type", directoryView.getFilters().get(0).getField());
        assertEquals("FacultyMember", directoryView.getFilters().get(0).getValue());

        Index index = new Index();

        index.setField("name");
        index.setType(Type.ENDING_WITH);

        directoryView.setIndex(index);

        assertNotNull(directoryView.getIndex());
        assertEquals("name", directoryView.getIndex().getField());
        assertEquals(Type.ENDING_WITH, directoryView.getIndex().getType());
    }

}

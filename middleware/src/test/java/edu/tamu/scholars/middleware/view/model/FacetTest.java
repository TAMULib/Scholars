package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.solr.core.query.FacetOptions.FacetSort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FacetTest {

    @Test
    public void testDefaultConstructor() {
        Facet facet = new Facet();
        assertNotNull(facet);
        assertEquals(10, facet.getLimit());
        assertEquals(FacetSort.COUNT, facet.getSort());
    }

    @Test
    public void testGettersAndSetters() {
        Facet facet = new Facet();

        facet.setName("Test");
        facet.setField("test");
        facet.setLimit(20);
        facet.setSort(FacetSort.INDEX);

        assertEquals("Test", facet.getName());
        assertEquals("test", facet.getField());
        assertEquals(20, facet.getLimit());
        assertEquals(FacetSort.INDEX, facet.getSort());
    }

}

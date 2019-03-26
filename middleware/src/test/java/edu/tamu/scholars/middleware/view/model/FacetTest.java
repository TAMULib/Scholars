package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FacetTest {

    @Test
    public void testDefaultConstructor() {
        Facet facet = new Facet();
        assertNotNull(facet);
        assertEquals(10, facet.getLimit());
    }

    @Test
    public void testGettersAndSetters() {
        Facet facet = new Facet();

        facet.setName("Test");
        facet.setField("test");
        facet.setLimit(20);
        facet.setSort(FacetOptions.FacetSort.INDEX);
        facet.setDirection(Sort.Direction.ASC);

        assertEquals("Test", facet.getName());
        assertEquals("test", facet.getField());
        assertEquals(20, facet.getLimit());
        assertEquals(FacetOptions.FacetSort.INDEX, facet.getSort());
        assertEquals(Sort.Direction.ASC, facet.getDirection());
    }

}

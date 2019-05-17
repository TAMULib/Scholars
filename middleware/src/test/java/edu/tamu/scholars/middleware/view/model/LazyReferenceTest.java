package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class LazyReferenceTest {

    @Test
    public void testDefaultConstructor() {
        LazyReference lazyReference = new LazyReference();
        assertNotNull(lazyReference);
    }

    @Test
    public void testGettersAndSetters() {
        LazyReference lazyReference = new LazyReference();

        lazyReference.setField("publications");
        lazyReference.setCollection("documents");

        assertEquals("publications", lazyReference.getField());
        assertEquals("documents", lazyReference.getCollection());
    }

}

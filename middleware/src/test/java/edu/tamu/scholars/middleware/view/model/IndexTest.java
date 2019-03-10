package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.solr.core.query.Criteria.OperationKey;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class IndexTest {

    @Test
    public void testDefaultConstructor() {
        Index index = new Index();
        assertNotNull(index);
    }

    @Test
    public void testGettersAndSetters() {
        Index index = new Index();

        index.setField("test");
        index.setOperationKey(OperationKey.ENDS_WITH);

        assertEquals("test", index.getField());
        assertEquals(OperationKey.ENDS_WITH, index.getOperationKey());
    }

}

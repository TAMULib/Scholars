package edu.tamu.scholars.middleware.view.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

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

        List<String> options = new ArrayList<String>();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            options.add(String.valueOf(letter));
        }

        index.setOptions(options);

        assertEquals("test", index.getField());
        assertEquals(OperationKey.ENDS_WITH, index.getOperationKey());

        assertEquals(26, index.getOptions().size());

        int i = 0;
        for (char letter = 'A'; letter <= 'Z'; letter++, i++) {
            assertEquals(String.valueOf(letter), index.getOptions().get(i));
        }
    }

}

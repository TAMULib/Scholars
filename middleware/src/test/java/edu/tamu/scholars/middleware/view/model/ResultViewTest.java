package edu.tamu.scholars.middleware.view.model;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockResultView;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ResultViewTest {

    @Test
    public void testDefaultConstructor() {
        ResultView resultView = new ResultView();
        assertNotNull(resultView);
    }

    @Test
    public void testGettersAndSetters() {
        ResultView resultView = getMockResultView();
        resultView.setId(1L);

        assertEquals(1L, resultView.getId(), 1);
        assertEquals("People", resultView.getName());
        assertEquals("<h1>Person template from WSYWIG</h1>", resultView.getTemplate());
    }

}

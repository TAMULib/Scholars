package edu.tamu.scholars.middleware.view.model;

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
        ResultView resultView = new ResultView();

        resultView.setId(1L);
        resultView.setName("People");
        resultView.setTemplate("<h1>Fancy templated html from WSYWIG</h1>");

        assertEquals(1L, resultView.getId(), 1);
        assertEquals("People", resultView.getName());
        assertEquals("<h1>Fancy templated html from WSYWIG</h1>", resultView.getTemplate());
    }

}

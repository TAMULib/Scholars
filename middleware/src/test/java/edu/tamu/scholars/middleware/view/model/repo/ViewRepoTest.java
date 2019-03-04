package edu.tamu.scholars.middleware.view.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.view.ViewIntegrationTest;
import edu.tamu.scholars.middleware.view.model.View;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public abstract class ViewRepoTest<V extends View, R extends ViewRepo<V>> extends ViewIntegrationTest<V, R> {

    @Test
    public void testCreate() {
        assertEquals(0, viewRepo.count());
        V view = getMockView();
        viewRepo.save(view);
        assertEquals(1, viewRepo.count());
    }

    @Test
    public void testRead() {
        testCreate();
        Optional<V> view = viewRepo.findByName("People");
        assertTrue(view.isPresent());
    }

    @Test
    public void testUpdate() {
        testCreate();
        Optional<V> view = viewRepo.findByName("People");
        view.get().setName("Scholars");

        viewRepo.save(view.get());

        assertEquals(1, viewRepo.count());

        view = viewRepo.findByName("Scholars");
        assertEquals("Scholars", view.get().getName());
    }

    @Test
    public void testDelete() {
        testCreate();
        assertEquals(1, viewRepo.count());
        Optional<V> view = viewRepo.findByName("People");
        viewRepo.delete(view.get());
        assertEquals(0, viewRepo.count());
    }

}

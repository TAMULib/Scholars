package edu.tamu.scholars.middleware.view.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.view.model.CollectionView;
import edu.tamu.scholars.middleware.view.model.Layout;
import edu.tamu.scholars.middleware.view.model.ResultView;

public abstract class CollectionViewRepoTest<V extends CollectionView, R extends ViewRepo<V>> extends ViewRepoTest<V, R> {

    @Autowired
    private ResultViewRepo resultViewRepo;

    @Test
    public void testCreateCascade() {
        assertEquals(0, viewRepo.count());
        V view = getMockView();
        viewRepo.save(view);
        assertEquals(1, viewRepo.count());
        assertEquals(1, resultViewRepo.count());
    }

    @Test
    public void testUpdateCascade() {
        testCreate();
        Optional<V> view = viewRepo.findByName("People");
        view.get().setName("Scholars");
        view.get().setLayout(Layout.LIST);

        ResultView resultView = new ResultView();

        resultView.setName("Organizations");
        resultView.setTemplate("<h1>Organization templated from WSYWIG</h1>");

        view.get().setResultView(resultView);

        viewRepo.save(view.get());

        assertEquals(1, viewRepo.count());

        view = viewRepo.findByName("Scholars");
        assertEquals("Scholars", view.get().getName());
        assertEquals(Layout.LIST, view.get().getLayout());

        assertEquals(2, resultViewRepo.count());

        assertEquals("Organizations", view.get().getResultView().getName());
        assertEquals("<h1>Organization templated from WSYWIG</h1>", view.get().getResultView().getTemplate());
    }

    @Test
    public void testDeleteCascade() {
        testCreate();
        assertEquals(1, viewRepo.count());
        Optional<V> view = viewRepo.findByName("People");
        viewRepo.delete(view.get());
        assertEquals(0, viewRepo.count());
        assertEquals(1, resultViewRepo.count());
    }

    @AfterEach
    public void deleteAllResultViews() {
        resultViewRepo.deleteAll();
    }

}

package edu.tamu.scholars.middleware.view;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.view.model.View;
import edu.tamu.scholars.middleware.view.model.repo.ViewRepo;

public abstract class ViewIntegrationTest<V extends View, R extends ViewRepo<V>> extends UserIntegrationTest {

    @Autowired
    protected R viewRepo;

    protected abstract V getMockView();

    @AfterEach
    public void deleteAllViews() {
        viewRepo.deleteAll();
    }

}

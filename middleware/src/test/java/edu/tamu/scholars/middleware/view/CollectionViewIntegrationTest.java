package edu.tamu.scholars.middleware.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.view.model.View;
import edu.tamu.scholars.middleware.view.model.repo.ResultViewRepo;
import edu.tamu.scholars.middleware.view.model.repo.ViewRepo;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(SpringExtension.class)
public abstract class CollectionViewIntegrationTest<V extends View, R extends ViewRepo<V>> extends ViewIntegrationTest<V, R> {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected ResultViewRepo resultViewRepo;

    @Override
    @AfterEach
    public void deleteAllViews() {
        // NOTE: must delete collection views before joined result view
        viewRepo.deleteAll();
        resultViewRepo.deleteAll();
    }

}

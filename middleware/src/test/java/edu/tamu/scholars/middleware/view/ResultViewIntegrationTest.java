package edu.tamu.scholars.middleware.view;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.view.model.ResultView;
import edu.tamu.scholars.middleware.view.model.repo.ResultViewRepo;

public abstract class ResultViewIntegrationTest extends UserIntegrationTest {

    @Autowired
    protected ResultViewRepo directoryViewRepo;

    protected ResultView getMockResultView() {
        ResultView resultView = new ResultView();

        return resultView;
    }

}

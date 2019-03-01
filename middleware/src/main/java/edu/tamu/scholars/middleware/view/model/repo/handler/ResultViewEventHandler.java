package edu.tamu.scholars.middleware.view.model.repo.handler;

import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import edu.tamu.scholars.middleware.view.model.ResultView;

@RepositoryEventHandler(ResultView.class)
public class ResultViewEventHandler extends ViewEventHandler<ResultView> {

    public static final String RESULT_VIEWS_CHANNEL = "/queue/result-views";

    @Override
    protected String getChannel() {
        return RESULT_VIEWS_CHANNEL;
    }

}

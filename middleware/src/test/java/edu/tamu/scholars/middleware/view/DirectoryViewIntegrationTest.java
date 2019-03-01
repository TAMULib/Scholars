package edu.tamu.scholars.middleware.view;

import org.springframework.beans.factory.annotation.Autowired;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.repo.DirectoryViewRepo;

public abstract class DirectoryViewIntegrationTest extends UserIntegrationTest {

    @Autowired
    protected DirectoryViewRepo directoryViewRepo;

    protected DirectoryView getMockDirectoryView() {
        DirectoryView directoryView = new DirectoryView();

        return directoryView;
    }

}

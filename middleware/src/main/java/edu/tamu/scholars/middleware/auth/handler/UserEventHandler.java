package edu.tamu.scholars.middleware.auth.handler;

import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import edu.tamu.scholars.middleware.auth.model.User;

@RepositoryEventHandler(User.class)
public class UserEventHandler {

}

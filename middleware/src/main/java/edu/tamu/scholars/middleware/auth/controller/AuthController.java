package edu.tamu.scholars.middleware.auth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.scholars.middleware.auth.model.User;

@RestController
public class AuthController {

    @RequestMapping("/user")
    public User user(@AuthenticationPrincipal User user) {
        return user;
    }

}

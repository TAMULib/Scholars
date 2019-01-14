package edu.tamu.scholars.middleware.auth;

import static edu.tamu.scholars.middleware.auth.RegistrationTestUtility.getMockRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.auth.controller.request.Registration;

public abstract class RegistrationIntegrationTest extends UserIntegrationTest {

    @Autowired
    protected TokenService tokenService;

    @Autowired
    protected ObjectMapper objectMapper;

    protected Token getMockToken(String firstName, String lastName, String email) throws JsonProcessingException {
        Registration registration = getMockRegistration(firstName, lastName, email);
        String registrationJson = objectMapper.writeValueAsString(registration);
        return tokenService.allocateToken(registrationJson);
    }

}

package edu.tamu.scholars.middleware.auth;

import static edu.tamu.scholars.middleware.auth.RegistrationTestUtility.getMockRegistration;
import static org.mockito.Mockito.doReturn;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.auth.controller.request.Registration;

public abstract class RegistrationIntegrationTest extends UserIntegrationTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    public void setupTokens() throws JsonProcessingException {
        Token bobsToken = getMockToken("Bob", "Boring", "bboring@mailinator.com");
        Token eddiesToken = getMockToken("Eddie", "Exciting", "eexciting@mailinator.com");
        Token carlsToken = getMockToken("Carl", "Calamitous", "ccalamitous@mailinator.com");
        doReturn(bobsToken).when(tokenService).allocateToken(getMockRegistrationJson("Bob", "Boring", "bboring@mailinator.com"));
        doReturn(eddiesToken).when(tokenService).allocateToken(getMockRegistrationJson("Eddie", "Exciting", "eexciting@mailinator.com"));
        doReturn(carlsToken).when(tokenService).allocateToken(getMockRegistrationJson("Carl", "Calamitous", "ccalamitous@mailinator.com"));
        doReturn(bobsToken).when(tokenService).verifyToken(bobsToken.getKey());
        doReturn(eddiesToken).when(tokenService).verifyToken(eddiesToken.getKey());
        doReturn(carlsToken).when(tokenService).verifyToken(carlsToken.getKey());
    }

    protected Token getMockToken(String firstName, String lastName, String email) throws JsonProcessingException {
        String registrationJson = getMockRegistrationJson(firstName, lastName, email);
        return new DefaultToken(email, new Date().getTime(), registrationJson);
    }

    protected String getMockRegistrationJson(String firstName, String lastName, String email) throws JsonProcessingException {
        Registration registration = getMockRegistration(firstName, lastName, email);
        return objectMapper.writeValueAsString(registration);
    }

}

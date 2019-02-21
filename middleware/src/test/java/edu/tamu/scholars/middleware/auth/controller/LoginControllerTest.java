package edu.tamu.scholars.middleware.auth.controller;

import static edu.tamu.scholars.middleware.auth.AuthConstants.PASSWORD_DURATION_IN_DAYS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.auth.model.User;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(SpringExtension.class)
public class LoginControllerTest extends UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        User user = createMockUser();
        // @formatter:off
        mockMvc.perform(post("/login")
            .param("username", user.getEmail())
            .param("password", "HelloWorld123!"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("active", equalTo(user.isActive())))
                .andExpect(jsonPath("enabled", equalTo(user.isEnabled())))
                .andExpect(jsonPath("email", equalTo(user.getEmail())))
                .andExpect(jsonPath("role", equalTo(user.getRole().toString())))
                .andDo(document("login"));
        // @formatter:on
    }

    @Test
    public void testLoginCredentialsExpired() throws Exception {
        User user = createMockUser();

        PASSWORD_DURATION_IN_DAYS = 0;

        // @formatter:off
        mockMvc.perform(post("/login")
            .param("username", user.getEmail())
            .param("password", "HelloWorld123!"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("User credentials have expired")));
        // @formatter:on

        PASSWORD_DURATION_IN_DAYS = 180;
    }

    @Test
    public void testLoginInactive() throws Exception {
        User user = getMockUser();

        user.setActive(false);

        user = userRepo.save(user);

        // @formatter:off
        mockMvc.perform(post("/login")
            .param("username", user.getEmail())
            .param("password", "HelloWorld123!"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("User account has expired")));
        // @formatter:on
    }

    @Test
    public void testLoginDisabled() throws Exception {
        User user = getMockUser();

        user.setEnabled(false);

        user = userRepo.save(user);

        // @formatter:off
        mockMvc.perform(post("/login")
            .param("username", user.getEmail())
            .param("password", "HelloWorld123!"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("User account is locked")));
        // @formatter:on
    }

    @Test
    public void testLoginNotConfirmed() throws Exception {
        User user = getMockUser();

        user.setConfirmed(false);

        user = userRepo.save(user);

        // @formatter:off
        mockMvc.perform(post("/login")
            .param("username", user.getEmail())
            .param("password", "HelloWorld123!"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("User account is locked")));
        // @formatter:on
    }

    @Test
    public void testLoginFailure() throws Exception {
        // @formatter:off
        mockMvc.perform(post("/login")
            .param("username", "admin@test.com")
            .param("password", "incorrect"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("Bad credentials")));
        // @formatter:on
    }

    @Test
    public void testLogout() throws Exception {
        User user = createMockUser();
        MvcResult result = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        Cookie cookie = result.getResponse().getCookie("SESSION");

        // @formatter:off
        mockMvc.perform(post("/logout")
            .cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Success")));
        // @formatter:on
    }

}

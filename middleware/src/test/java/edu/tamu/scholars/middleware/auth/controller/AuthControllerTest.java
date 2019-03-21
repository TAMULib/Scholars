package edu.tamu.scholars.middleware.auth.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.auth.model.Role;
import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.utility.ConstraintDescriptionsHelper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(SpringExtension.class)
public class AuthControllerTest extends UserIntegrationTest {
    private static final ConstraintDescriptionsHelper describeUser = new ConstraintDescriptionsHelper(User.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUser() throws Exception {
        User user = getMockUser();

        user.setRole(Role.ROLE_ADMIN);

        user = userRepo.save(user);

        MvcResult result = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        Cookie cookie = result.getResponse().getCookie("SESSION");

        // @formatter:off
        mockMvc.perform(get("/user")
            .cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(
                    document(
                        "user",
                        responseFields(
                            describeUser.withSubsection("id", "The ID of the user."),
                            describeUser.withSubsection("firstName", "The first name of the user."),
                            describeUser.withSubsection("lastName", "The last name of the user."),
                            describeUser.withSubsection("email", "The e-mail address of the user."),
                            describeUser.withSubsection("role", "The authorization role of the user."),
                            describeUser.withSubsection("active", "The expired/unexpired status of the user."),
                            describeUser.withSubsection("enabled", "The locked/unlocked status of the user.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testUserUnauthorized() throws Exception {
        // @formatter:off
        mockMvc.perform(get("/user"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Full authentication is required to access this resource")));
        // @formatter:on
    }

}

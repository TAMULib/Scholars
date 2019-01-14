package edu.tamu.scholars.middleware.auth.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.auth.model.Role;
import edu.tamu.scholars.middleware.auth.model.User;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthControllerTest extends UserIntegrationTest {

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
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
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

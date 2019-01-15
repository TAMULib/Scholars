package edu.tamu.scholars.middleware.auth.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import edu.tamu.scholars.middleware.auth.UserIntegrationTest;
import edu.tamu.scholars.middleware.auth.model.User;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerTest extends UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsers() throws Exception {
        User admin = createMockAdmin();
        // @formatter:off
        mockMvc.perform(get("/users").cookie(login(admin)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(HAL_JSON_UTF8_VALUE));
        // @formatter:on
    }

    @Test
    public void testPatchUser() throws Exception {
        User admin = createMockAdmin();
        // @formatter:off
        mockMvc.perform(patch(String.format("/users/%d", admin.getId())).cookie(login(admin)).content("{\"role\": \"ROLE_USER\", \"active\": false}"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
            .andExpect(jsonPath("active", equalTo(false)))
            .andExpect(jsonPath("role", equalTo("ROLE_USER")));
        // @formatter:on
    }

    @Test
    public void testPatchUserUnauthorized() throws Exception {
        User user = createMockUser();
        // @formatter:off
        mockMvc.perform(patch(String.format("/users/%d", user.getId())).content("{\"role\": \"ROLE_USER\", \"active\": false}"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Full authentication is required to access this resource")));
        // @formatter:on
    }

    @Test
    public void testPatchUserForbidden() throws Exception {
        User user = createMockUser();
        // @formatter:off
        mockMvc.perform(patch(String.format("/users/%d", user.getId())).cookie(login(user)).content("{\"role\": \"ROLE_USER\", \"active\": false}"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Access is denied")));
        // @formatter:on
    }

    @Test
    public void testDeleteUser() throws Exception {
        User admin = createMockAdmin();
        User superAdmin = createMockSuperAdmin();
        // @formatter:off
        mockMvc.perform(delete(String.format("/users/%d", admin.getId())).cookie(login(superAdmin)).content("{\"role\": \"ROLE_USER\", \"active\": false}"))
            .andExpect(status().isNoContent());
        // @formatter:on
    }

    @Test
    public void testDeleteUserUnauthorized() throws Exception {
        User user = createMockUser();
        // @formatter:off
        mockMvc.perform(delete(String.format("/users/%d", user.getId())))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Full authentication is required to access this resource")));
        // @formatter:on
    }

    @Test
    public void testDeleteUserForbidden() throws Exception {
        User user = createMockUser();
        User admin = createMockAdmin();
        // @formatter:off
        mockMvc.perform(delete(String.format("/users/%d", user.getId())).cookie(login(admin)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Access is denied")));
        // @formatter:on
    }

    @Test
    public void testGetUsersUnauthorized() throws Exception {
        // @formatter:off
        mockMvc.perform(get("/users"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Full authentication is required to access this resource")));
        // @formatter:on
    }

    @Test
    public void testGetUsersForbidden() throws Exception {
        User user = createMockUser();
        // @formatter:off
        mockMvc.perform(get("/users").cookie(login(user)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Access is denied")));
        // @formatter:on
    }

    @Test
    public void testGetUser() throws Exception {
        User admin = createMockAdmin();
        // @formatter:off
        mockMvc.perform(get(String.format("/users/%s", admin.getId().toString())).cookie(login(admin)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(HAL_JSON_UTF8_VALUE));
        // @formatter:on
    }

    @Test
    public void testGetUserUnauthorized() throws Exception {
        User user = createMockUser();
        // @formatter:off
        mockMvc.perform(get(String.format("/users/%s", user.getId().toString())))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Full authentication is required to access this resource")));
        // @formatter:on
    }

    @Test
    public void testGetUserForbidden() throws Exception {
        User user = createMockUser();
        // @formatter:off
        mockMvc.perform(get(String.format("/users/%s", user.getId().toString())).cookie(login(user)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string(equalTo("Access is denied")));
        // @formatter:on
    }

    private Cookie login(User user) throws Exception {
        MvcResult result = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        return result.getResponse().getCookie("SESSION");
    }

}

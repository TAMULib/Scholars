package edu.tamu.scholars.middleware.auth.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
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
public class UserControllerTest extends UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsers() throws Exception {
        User admin = createMockAdmin();
        // @formatter:off
        mockMvc.perform(
            get("/users").param("page", "0").param("size", "20").param("sort", "id")
                .cookie(login(admin)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                    .andExpect(jsonPath("page.size", equalTo(20)))
                    .andExpect(jsonPath("page.totalElements", equalTo(1)))
                    .andExpect(jsonPath("page.totalPages", equalTo(1)))
                    .andExpect(jsonPath("page.number", equalTo(0)))
                    .andDo(
                        document(
                            "users",
                            requestParameters(
                                parameterWithName("page").description("The page number"),
                                parameterWithName("size").description("The page size"),
                                parameterWithName("sort").description("The page sort")
                            ),
                            links(
                                linkWithRel("self").description("Canonical link for this resource"),
                                linkWithRel("profile").description("The ALPS profile for this resource")
                            ),
                            responseFields(
                                subsectionWithPath("_embedded.users").description("An array of <<resources-user, User resources>>"),
                                subsectionWithPath("_links").description("<<resources-user-list-links, Links>> to other resources"),
                                subsectionWithPath("page").description("Page details for <<resources-user, User resources>>")
                            )
                        )
                    );
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
            .andExpect(jsonPath("role", equalTo("ROLE_USER")))
            .andDo(
                document(
                    "users/patch",
                    links(
                        linkWithRel("self").description("Canonical link for this resource"),
                        linkWithRel("user").description("Canonical link for the referenced user")
                    ),
                    responseFields(
                        subsectionWithPath("firstName").description("The first name of the user"),
                        subsectionWithPath("lastName").description("The last name of the user"),
                        subsectionWithPath("email").description("The e-mail address of the user"),
                        subsectionWithPath("role").description("The authorization role of the user"),
                        subsectionWithPath("active").description("The expired/unexpired status of the user"),
                        subsectionWithPath("enabled").description("The locked/unlocked status of the user"),
                        subsectionWithPath("_links").description("<<resources-user-list-links, Links>> to other resources")
                    )
                )
            );
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
            .andExpect(status().isNoContent())
            .andDo(document("users/delete"));
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
            .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
            .andDo(
                document(
                    "users/find-by-id",
                    links(
                        linkWithRel("self").description("Canonical link for this resource"),
                        linkWithRel("user").description("Canonical link for the referenced user")
                    ),
                    responseFields(
                        subsectionWithPath("firstName").description("The first name of the user"),
                        subsectionWithPath("lastName").description("The last name of the user"),
                        subsectionWithPath("email").description("The e-mail address of the user"),
                        subsectionWithPath("role").description("The authorization role of the user"),
                        subsectionWithPath("active").description("The expired/unexpired status of the user"),
                        subsectionWithPath("enabled").description("The locked/unlocked status of the user"),
                        subsectionWithPath("_links").description("<<resources-user-list-links, Links>> to other resources")
                    )
                )
            );
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

package edu.tamu.scholars.middleware.view.controller;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.MOCK_VIEW_NAME;
import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDirectoryView;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.utility.ConstraintDescriptionsHelper;
import edu.tamu.scholars.middleware.view.ResourceViewIntegrationTest;
import edu.tamu.scholars.middleware.view.model.DirectoryView;
import edu.tamu.scholars.middleware.view.model.repo.DirectoryViewRepo;

public class DirectoryViewControllerTest extends ResourceViewIntegrationTest<DirectoryView, DirectoryViewRepo> {

    private static final ConstraintDescriptionsHelper describeDirectoryView = new ConstraintDescriptionsHelper(DirectoryView.class);

    @Test
    public void testCreateDirectoryView() throws JsonProcessingException, Exception {
        // @formatter:off
        performCreateDirectoryView()
            .andDo(
                document(
                    "directoryViews/create",
                    requestFields(
                        describeDirectoryView.withField("name", "The name of the Directory View."),
                        // describeDirectoryView.withField("collection", "The collection of the Directory View."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the Directory View."),
                        describeDirectoryView.withField("layout", "The layout of the Directory View."),
                        describeDirectoryView.withSubsection("templates", "The result templates of the Directory View."),
                        describeDirectoryView.withField("styles", "An array of result style strings of the Directory View."),
                        describeDirectoryView.withSubsection("index", "A <<resources-index, Index resource>>."),
                        describeDirectoryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDirectoryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("directoryView").description("The Directory View link for this resource.")
                    ),
                    responseFields(
                        describeDirectoryView.withField("id", "The Directory View id."),
                        describeDirectoryView.withField("name", "The name of the Directory View."),
                        // describeDirectoryView.withField("collection", "The collection of the Directory View."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the Directory View."),
                        describeDirectoryView.withField("layout", "The layout of the Directory View."),
                        describeDirectoryView.withSubsection("templates", "The result templates of the Directory View."),
                        describeDirectoryView.withField("styles", "An array of result style strings of the Directory View."),
                        describeDirectoryView.withSubsection("index", "A <<resources-index, Index resource>>."),
                        describeDirectoryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDirectoryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                        subsectionWithPath("_links").description("<<resources-directory-views-list-links, Links>> to other resources.")
                    )
                )
            );
       // @formatter:on
    }

    @Test
    public void testUpdateDirectoryView() throws JsonProcessingException, Exception {
        performCreateDirectoryView();

        // @formatter:off
        performUpdateDirectoryView()
            .andDo(
                document(
                    "directoryViews/update",
                    pathParameters(
                        describeDirectoryView.withParameter("id", "The Directory View id.")
                    ),
                    requestFields(
                        describeDirectoryView.withField("id", "The Directory View id."),
                        describeDirectoryView.withField("name", "The name of the Directory View."),
                        // describeDirectoryView.withField("collection", "The collection of the Directory View."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the Directory View."),
                        describeDirectoryView.withField("layout", "The layout of the Directory View."),
                        describeDirectoryView.withSubsection("templates", "The result templates of the Directory View."),
                        describeDirectoryView.withField("styles", "An array of result style strings of the Directory View."),
                        describeDirectoryView.withSubsection("index", "A <<resources-index, Index resource>>."),
                        describeDirectoryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDirectoryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("directoryView").description("The Directory View link for this resource.")
                    ),
                    responseFields(
                        describeDirectoryView.withField("id", "The Directory View id."),
                        describeDirectoryView.withField("name", "The name of the Directory View."),
                        // describeDirectoryView.withField("collection", "The collection of the Directory View."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the Directory View."),
                        describeDirectoryView.withField("layout", "The layout of the Directory View."),
                        describeDirectoryView.withSubsection("templates", "The result templates of the Directory View."),
                        describeDirectoryView.withField("styles", "An array of result style strings of the Directory View."),
                        describeDirectoryView.withSubsection("index", "A <<resources-index, Index resource>>."),
                        describeDirectoryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDirectoryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                        subsectionWithPath("_links").description("<<resources-directory-views-list-links, Links>> to other resources.")
                    )
                )
            );
        // @formatter:on
    }

    @Test
    public void testPatchTheme() throws JsonProcessingException, Exception {
        performCreateDirectoryView();
        DirectoryView directoryView = viewRepo.findByName(MOCK_VIEW_NAME).get();

        // @formatter:off
        mockMvc.perform(
            patch("/directoryViews/{id}", directoryView.getId())
                .content("{\"name\": \"Organizations\", \"collection\": \"organizations\"}")
                .cookie(loginAdmin()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                    .andExpect(jsonPath("name", equalTo("Organizations")))
                    .andExpect(jsonPath("collection", equalTo("organizations")))
                    .andDo(
                        document(
                            "directoryViews/patch",
                            pathParameters(
                                describeDirectoryView.withParameter("id", "The Directory View id.")
                            ),
                            requestParameters(
                                describeDirectoryView.withParameter("id", "The Directory View id.").optional(),
                                describeDirectoryView.withParameter("name", "The name of the Directory View.").optional(),
                                // describeDirectoryView.withParameter("collection", "The collection of the Directory View.").optional(),
                                // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                                parameterWithName("collection").description("The collection of the Directory View.").optional(),
                                describeDirectoryView.withParameter("layout", "The layout of the Directory View.").optional(),
                                describeDirectoryView.withParameter("index", "A <<resources-index, Index resource>>.").optional(),
                                describeDirectoryView.withParameter("templates", "The result templates of the Directory View.").optional(),
                                describeDirectoryView.withParameter("styles", "An array of result style strings of the Directory View.").optional(),
                                describeDirectoryView.withParameter("facets", "An array of <<resources-facets, Facet resources>>.").optional(),
                                describeDirectoryView.withParameter("filters", "An array of <<resources-filters, Filters resources>>.").optional()
                            ),
                            links(
                                linkWithRel("self").description("Canonical link for this resource."),
                                linkWithRel("directoryView").description("The Directory View link for this resource.")
                            ),
                            responseFields(
                                describeDirectoryView.withField("id", "The Directory View id."),
                                describeDirectoryView.withField("name", "The name of the Directory View."),
                                // describeDirectoryView.withField("collection", "The collection of the Directory View."),
                                // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                                fieldWithPath("collection").description("The collection of the Directory View."),
                                describeDirectoryView.withField("layout", "The layout of the Directory View."),
                                describeDirectoryView.withSubsection("templates", "The result templates of the Directory View."),
                                describeDirectoryView.withField("styles", "An array of result style strings of the Directory View."),
                                describeDirectoryView.withSubsection("index", "A <<resources-index, Index resource>>."),
                                describeDirectoryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                                describeDirectoryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                                subsectionWithPath("_links").description("<<resources-directory-views-list-links, Links>> to other resources.")
                            )
                        )
                    );
        // @formatter:on
    }

    @Test
    public void testGetDirectoryView() throws JsonProcessingException, Exception {
        performCreateDirectoryView();
        DirectoryView directoryView = viewRepo.findByName(MOCK_VIEW_NAME).get();
        // @formatter:off
        mockMvc.perform(
            get("/directoryViews/{id}", directoryView.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name", equalTo(MOCK_VIEW_NAME)))
                .andDo(
                    document(
                        "directoryViews/find-by-id",
                        pathParameters(
                            describeDirectoryView.withParameter("id", "The Theme id.")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("directoryView").description("The Directory View link for this resource.")
                        ),
                        responseFields(
                            describeDirectoryView.withField("id", "The Directory View id."),
                            describeDirectoryView.withField("name", "The name of the Directory View."),
                            // describeDirectoryView.withField("collection", "The collection of the Directory View."),
                            // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                            fieldWithPath("collection").description("The collection of the Directory View."),
                            describeDirectoryView.withField("layout", "The layout of the Directory View."),
                            describeDirectoryView.withSubsection("templates", "The result templates of the Directory View."),
                            describeDirectoryView.withField("styles", "An array of result style strings of the Directory View."),
                            describeDirectoryView.withSubsection("index", "A <<resources-index, Index resource>>."),
                            describeDirectoryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                            describeDirectoryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                            subsectionWithPath("_links").description("<<resources-directory-view-list-links, Links>> to other resources.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testGetDirectoryViews() throws JsonProcessingException, Exception {
        performCreateDirectoryView();
        // @formatter:off
        mockMvc.perform(
            get("/directoryViews").param("page", "0").param("size", "20").param("sort", "name"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("page.size", equalTo(20)))
                .andExpect(jsonPath("page.totalElements", equalTo(1)))
                .andExpect(jsonPath("page.totalPages", equalTo(1)))
                .andExpect(jsonPath("page.number", equalTo(1)))
                .andExpect(jsonPath("_embedded.directoryViews[0].name", equalTo(MOCK_VIEW_NAME)))
                .andDo(
                    document(
                        "directoryViews/directory",
                        requestParameters(
                            parameterWithName("page").description("The page number."),
                            parameterWithName("size").description("The page size."),
                            parameterWithName("sort").description("The page sort.")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("profile").description("The ALPS profile for this resource.")
                        ),
                        responseFields(
                            subsectionWithPath("_embedded.directoryViews").description("An array of <<resources-directory-views, Directory View resources>>."),
                            subsectionWithPath("_links").description("<<resources-directory-view-list-links, Links>> to other resources."),
                            subsectionWithPath("page").description("Page details for <<resources-directory-views, Directory View resources>>.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testDeleteTheme() throws JsonProcessingException, Exception {
        performCreateDirectoryView();
        DirectoryView directoryView = viewRepo.findByName(MOCK_VIEW_NAME).get();
        // @formatter:off
        mockMvc.perform(
            delete("/directoryViews/{id}", directoryView.getId())
                .cookie(loginAdmin()))
                    .andExpect(status().isNoContent())
                    .andDo(
                        document(
                            "directoryViews/delete",
                            pathParameters(
                                parameterWithName("id").description("The Directory View id")
                            )
                        )
                    );
        // @formatter:on
    }

    protected Cookie loginAdmin() throws Exception {
        User user = getMockAdmin();
        MvcResult directory = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        return directory.getResponse().getCookie("SESSION");
    }

    private ResultActions performCreateDirectoryView() throws JsonProcessingException, Exception {
        createMockAdmin();
        DirectoryView directoryView = getMockView();

        // @formatter:off
        return mockMvc.perform(
            post("/directoryViews")
                .content(objectMapper.writeValueAsString(directoryView))
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(loginAdmin()))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
            );
        // @formatter:on
    }

    private ResultActions performUpdateDirectoryView() throws JsonProcessingException, Exception {
        DirectoryView directoryView = viewRepo.findByName(MOCK_VIEW_NAME).get();
        directoryView.setName("Organizations");
        directoryView.setCollection("organizations");

        // @formatter:off
        return mockMvc.perform(
            put("/directoryViews/{id}", directoryView.getId())
                .content(objectMapper.writeValueAsString(directoryView))
                .cookie(loginAdmin()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
            );
        // @formatter:on
    }

    @Override
    protected DirectoryView getMockView() {
        return getMockDirectoryView();
    }

}

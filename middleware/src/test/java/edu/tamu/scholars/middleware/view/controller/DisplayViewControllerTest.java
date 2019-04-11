package edu.tamu.scholars.middleware.view.controller;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.MOCK_VIEW_NAME;
import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDisplayView;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.utility.ConstraintDescriptionsHelper;
import edu.tamu.scholars.middleware.view.ResourceViewIntegrationTest;
import edu.tamu.scholars.middleware.view.model.DisplayView;
import edu.tamu.scholars.middleware.view.model.repo.DisplayViewRepo;

public class DisplayViewControllerTest extends ResourceViewIntegrationTest<DisplayView, DisplayViewRepo> {

    private static final ConstraintDescriptionsHelper describeDisplayView = new ConstraintDescriptionsHelper(DisplayView.class);

    @Test
    public void testCreateDisplayView() throws JsonProcessingException, Exception {
        // @formatter:off
        performCreateDisplayView()
            .andDo(
                document(
                    "displayViews/create",
                    requestFields(
                        describeDisplayView.withField("name", "The name of the Display View."),
                        describeDisplayView.withField("types", "An array of types."),
                        describeDisplayView.withField("mainContentTemplate", "The main content template of the Display View."),
                        describeDisplayView.withField("leftScanTemplate", "The left scan template of the Display View."),
                        describeDisplayView.withField("rightScanTemplate", "The right scan template of the Display View."),
                        describeDisplayView.withSubsection("metaTemplates", "The meta tag templates of the Display View."),
                        describeDisplayView.withSubsection("tabs", "An array of <<resources-display-tabs, Display tab view resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("displayView").description("The Display View link for this resource.")
                    ),
                    responseFields(
                        describeDisplayView.withField("name", "The name of the Display View."),
                        describeDisplayView.withField("types", "An array of types."),
                        describeDisplayView.withField("mainContentTemplate", "The main content template of the Display View."),
                        describeDisplayView.withField("leftScanTemplate", "The left scan template of the Display View."),
                        describeDisplayView.withField("rightScanTemplate", "The right scan template of the Display View."),
                        describeDisplayView.withSubsection("metaTemplates", "The meta tag templates of the Display View."),
                        describeDisplayView.withSubsection("tabs", "An array of <<resources-display-tabs, Display tab view resources>>."),
                        subsectionWithPath("_links").description("<<resources-display-views-list-links, Links>> to other resources.")
                    )
                )
            );
       // @formatter:on
    }

    @Test
    public void testUpdateDisplayView() throws JsonProcessingException, Exception {
        performCreateDisplayView();

        // @formatter:off
        performUpdateDisplayView()
            .andDo(
                document(
                    "displayViews/update",
                    pathParameters(
                        describeDisplayView.withParameter("id", "The Display View id.")
                    ),
                    requestFields(
                        describeDisplayView.withField("id", "The Display View id."),
                        describeDisplayView.withField("name", "The name of the Display View."),
                        describeDisplayView.withField("types", "An array of types."),
                        describeDisplayView.withField("mainContentTemplate", "The main content template of the Display View."),
                        describeDisplayView.withField("leftScanTemplate", "The left scan template of the Display View."),
                        describeDisplayView.withField("rightScanTemplate", "The right scan template of the Display View."),
                        describeDisplayView.withSubsection("metaTemplates", "The meta tag templates of the Display View."),
                        describeDisplayView.withSubsection("tabs", "An array of <<resources-display-tabs, Display tab view resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("displayView").description("The Display View link for this resource.")
                    ),
                    responseFields(
                        describeDisplayView.withField("name", "The name of the Display View."),
                        describeDisplayView.withField("types", "An array of types."),
                        describeDisplayView.withField("mainContentTemplate", "The main content template of the Display View."),
                        describeDisplayView.withField("leftScanTemplate", "The left scan template of the Display View."),
                        describeDisplayView.withField("rightScanTemplate", "The right scan template of the Display View."),
                        describeDisplayView.withSubsection("metaTemplates", "The meta tag templates of the Display View."),
                        describeDisplayView.withSubsection("tabs", "An array of <<resources-display-tabs, Display tab view resources>>."),
                        subsectionWithPath("_links").description("<<resources-display-views-list-links, Links>> to other resources.")
                    )
                )
            );
        // @formatter:on
    }

    @Test
    public void testPatchTheme() throws JsonProcessingException, Exception {
        performCreateDisplayView();
        DisplayView displayView = viewRepo.findByName(MOCK_VIEW_NAME).get();

        // @formatter:off
        mockMvc.perform(
            patch("/displayViews/{id}", displayView.getId())
                .content("{\"name\": \"Organizations\"}")
                .cookie(loginAdmin()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                    .andExpect(jsonPath("name", equalTo("Organizations")))
                    .andDo(
                        document(
                            "displayViews/patch",
                            pathParameters(
                                describeDisplayView.withParameter("id", "The Display View id.")
                            ),
                            requestParameters(
                                describeDisplayView.withParameter("id", "The Display View id.").optional(),
                                describeDisplayView.withParameter("name", "The name of the Display View.").optional(),
                                describeDisplayView.withParameter("types", "An array of types.").optional(),
                                describeDisplayView.withParameter("mainContentTemplate", "The main content template of the Display View.").optional(),
                                describeDisplayView.withParameter("leftScanTemplate", "The left scan template of the Display View.").optional(),
                                describeDisplayView.withParameter("rightScanTemplate", "The right scan template of the Display View.").optional(),
                                describeDisplayView.withParameter("metaTemplates", "The meta tag templates of the Display View.").optional(),
                                describeDisplayView.withParameter("tabs", "An array of <<resources-display-tabs, Display tab view resources>>.").optional()
                            ),
                            links(
                                linkWithRel("self").description("Canonical link for this resource."),
                                linkWithRel("displayView").description("The Display View link for this resource.")
                            ),
                            responseFields(
                                describeDisplayView.withField("name", "The name of the Display View."),
                                describeDisplayView.withField("types", "An array of types."),
                                describeDisplayView.withField("mainContentTemplate", "The main content template of the Display View."),
                                describeDisplayView.withField("leftScanTemplate", "The left scan template of the Display View."),
                                describeDisplayView.withField("rightScanTemplate", "The right scan template of the Display View."),
                                describeDisplayView.withSubsection("metaTemplates", "The meta tag templates of the Display View."),
                                describeDisplayView.withSubsection("tabs", "An array of <<resources-display-tabs, Display tab view resources>>."),
                                subsectionWithPath("_links").description("<<resources-display-views-list-links, Links>> to other resources.")
                            )
                        )
                    );
        // @formatter:on
    }

    @Test
    public void testGetDisplayView() throws JsonProcessingException, Exception {
        performCreateDisplayView();
        DisplayView displayView = viewRepo.findByName(MOCK_VIEW_NAME).get();
        // @formatter:off
        mockMvc.perform(
            get("/displayViews/{id}", displayView.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name", equalTo(MOCK_VIEW_NAME)))
                .andDo(
                    document(
                        "displayViews/find-by-id",
                        pathParameters(
                                describeDisplayView.withParameter("id", "The Theme id.")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("displayView").description("The Display View link for this resource.")
                        ),
                        responseFields(
                            describeDisplayView.withField("name", "The name of the Display View."),
                            describeDisplayView.withField("types", "An array of types."),
                            describeDisplayView.withField("mainContentTemplate", "The main content template of the Display View."),
                            describeDisplayView.withField("leftScanTemplate", "The left scan template of the Display View."),
                            describeDisplayView.withField("rightScanTemplate", "The right scan template of the Display View."),
                            describeDisplayView.withSubsection("metaTemplates", "The meta tag templates of the Display View."),
                            describeDisplayView.withSubsection("tabs", "An array of <<resources-display-tabs, Display tab view resources>>."),
                            subsectionWithPath("_links").description("<<resources-display-view-list-links, Links>> to other resources.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testGetDisplayViews() throws JsonProcessingException, Exception {
        performCreateDisplayView();
        // @formatter:off
        mockMvc.perform(
            get("/displayViews").param("page", "0").param("size", "20").param("sort", "name"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("page.size", equalTo(20)))
                .andExpect(jsonPath("page.totalElements", equalTo(1)))
                .andExpect(jsonPath("page.totalPages", equalTo(1)))
                .andExpect(jsonPath("page.number", equalTo(1)))
                .andExpect(jsonPath("_embedded.displayViews[0].name", equalTo(MOCK_VIEW_NAME)))
                .andDo(
                    document(
                        "displayViews/directory",
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
                            subsectionWithPath("_embedded.displayViews").description("An array of <<resources-display-views, Display View resources>>."),
                            subsectionWithPath("_links").description("<<resources-display-view-list-links, Links>> to other resources."),
                            subsectionWithPath("page").description("Page details for <<resources-display-views, Display View resources>>.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testDeleteTheme() throws JsonProcessingException, Exception {
        performCreateDisplayView();
        DisplayView displayView = viewRepo.findByName(MOCK_VIEW_NAME).get();
        // @formatter:off
        mockMvc.perform(
            delete("/displayViews/{id}", displayView.getId())
                .cookie(loginAdmin()))
                    .andExpect(status().isNoContent())
                    .andDo(
                        document(
                            "displayViews/delete",
                            pathParameters(
                                parameterWithName("id").description("The Display View id")
                            )
                        )
                    );
        // @formatter:on
    }

    protected Cookie loginAdmin() throws Exception {
        User user = getMockAdmin();
        MvcResult display = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        return display.getResponse().getCookie("SESSION");
    }

    private ResultActions performCreateDisplayView() throws JsonProcessingException, Exception {
        createMockAdmin();
        DisplayView displayView = getMockView();

        // @formatter:off
        return mockMvc.perform(
            post("/displayViews")
                .content(objectMapper.writeValueAsString(displayView))
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(loginAdmin()))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
                );
        // @formatter:on
    }

    private ResultActions performUpdateDisplayView() throws JsonProcessingException, Exception {
        DisplayView displayView = viewRepo.findByName(MOCK_VIEW_NAME).get();
        displayView.setName("Organizations");

        List<String> types = displayView.getTypes();
        types.add("NonFacultyMember");

        displayView.setTypes(types);

        // @formatter:off
        return mockMvc.perform(
            put("/displayViews/{id}", displayView.getId())
                .content(objectMapper.writeValueAsString(displayView))
                .cookie(loginAdmin()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
                );
        // @formatter:on
    }

    @Override
    protected DisplayView getMockView() {
        return getMockDisplayView();
    }

}

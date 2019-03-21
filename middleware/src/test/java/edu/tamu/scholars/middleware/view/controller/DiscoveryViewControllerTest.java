package edu.tamu.scholars.middleware.view.controller;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockDiscoveryView;
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
import edu.tamu.scholars.middleware.view.CollectionViewIntegrationTest;
import edu.tamu.scholars.middleware.view.model.DiscoveryView;
import edu.tamu.scholars.middleware.view.model.repo.DiscoveryViewRepo;

public class DiscoveryViewControllerTest extends CollectionViewIntegrationTest<DiscoveryView, DiscoveryViewRepo> {

    private static final ConstraintDescriptionsHelper describeDiscoveryView = new ConstraintDescriptionsHelper(DiscoveryView.class);

    @Test
    public void testCreateDiscoveryView() throws JsonProcessingException, Exception {
        // @formatter:off
        performCreateDiscoveryView()
            .andDo(
                document(
                    "discoveryViews/create",
                    requestFields(
                        describeDiscoveryView.withField("name", "The name of the discovery view."),
                        // describeDiscoveryView.withField("collection", "The collection of the discovery view."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the discovery view."),
                        describeDiscoveryView.withField("layout", "The layout of the discovery view."),
                        describeDiscoveryView.withField("template", "The result template of the discovery view."),
                        describeDiscoveryView.withField("styles", "An array of result style strings of the discovery view."),
                        describeDiscoveryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDiscoveryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("discoveryView").description("The discovery view link for this resource.")
                    ),
                    responseFields(
                        describeDiscoveryView.withField("name", "The name of the discovery view."),
                        // describeDiscoveryView.withField("collection", "The collection of the discovery view."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the discovery view."),
                        describeDiscoveryView.withField("layout", "The layout of the discovery view."),
                        describeDiscoveryView.withField("template", "The result template of the discovery view."),
                        describeDiscoveryView.withField("styles", "An array of result style strings of the discovery view."),
                        describeDiscoveryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDiscoveryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                        subsectionWithPath("_links").description("<<resources-discovery-views-list-links, Links>> to other resources.")
                    )
                )
            );
       // @formatter:on
    }

    @Test
    public void testUpdateDiscoveryView() throws JsonProcessingException, Exception {
        performCreateDiscoveryView();

        // @formatter:off
        performUpdateDiscoveryView()
            .andDo(
                document(
                    "discoveryViews/update",
                    pathParameters(
                        describeDiscoveryView.withParameter("id", "The Discovery View id.")
                    ),
                    requestFields(
                        describeDiscoveryView.withField("id", "The Discovery View id."),
                        describeDiscoveryView.withField("name", "The name of the Discovery View."),
                        // describeDiscoveryView.withField("collection", "The collection of the discovery view."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the discovery view."),
                        describeDiscoveryView.withField("layout", "The layout of the discovery view."),
                        describeDiscoveryView.withField("template", "The result template of the discovery view."),
                        describeDiscoveryView.withField("styles", "An array of result style strings of the discovery view."),
                        describeDiscoveryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDiscoveryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("discoveryView").description("The Discovery View link for this resource.")
                    ),
                    responseFields(
                        describeDiscoveryView.withField("name", "The name of the Discovery View."),
                        // describeDiscoveryView.withField("collection", "The collection of the discovery view."),
                        // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                        fieldWithPath("collection").description("The collection of the discovery view."),
                        describeDiscoveryView.withField("layout", "The layout of the discovery view."),
                        describeDiscoveryView.withField("template", "The result template of the discovery view."),
                        describeDiscoveryView.withField("styles", "An array of result style strings of the discovery view."),
                        describeDiscoveryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                        describeDiscoveryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                        subsectionWithPath("_links").description("<<resources-discovery-views-list-links, Links>> to other resources.")
                    )
                )
            );
        // @formatter:on
    }

    @Test
    public void testPatchTheme() throws JsonProcessingException, Exception {
        performCreateDiscoveryView();
        DiscoveryView discoveryView = viewRepo.findByName("People").get();

        // @formatter:off
        mockMvc.perform(
            patch("/discoveryViews/{id}", discoveryView.getId())
                .content("{\"name\": \"Organizations\", \"collection\": \"organizations\"}")
                .cookie(loginAdmin()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                    .andExpect(jsonPath("name", equalTo("Organizations")))
                    .andExpect(jsonPath("collection", equalTo("organizations")))
                    .andDo(
                        document(
                            "discoveryViews/patch",
                            pathParameters(
                                describeDiscoveryView.withParameter("id", "The Discovery View id.")
                            ),
                            requestParameters(
                                describeDiscoveryView.withParameter("id", "The Discovery View id.").optional(),
                                describeDiscoveryView.withParameter("name", "The name of the Discovery View.").optional(),
                                // describeDiscoveryView.withParameter("collection", "The collection of the Discovery View.").optional(),
                                // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                                parameterWithName("collection").description("The collection of the discovery view.").optional(),
                                describeDiscoveryView.withParameter("layout", "The layout of the discovery view.").optional(),
                                describeDiscoveryView.withParameter("template", "The result template of the discovery view.").optional(),
                                describeDiscoveryView.withParameter("styles", "An array of result style strings of the discovery view.").optional(),
                                describeDiscoveryView.withParameter("facets", "An array of <<resources-facets, Facet resources>>.").optional(),
                                describeDiscoveryView.withParameter("filters", "An array of <<resources-filters, Filters resources>>.").optional()
                            ),
                            links(
                                linkWithRel("self").description("Canonical link for this resource."),
                                linkWithRel("discoveryView").description("The Discovery View link for this resource.")
                            ),
                            responseFields(
                                describeDiscoveryView.withField("name", "The name of the Discovery View."),
                                // describeDiscoveryView.withField("collection", "The collection of the Discovery View."),
                                // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                                fieldWithPath("collection").description("The collection of the discovery view."),
                                describeDiscoveryView.withField("layout", "The layout of the discovery view."),
                                describeDiscoveryView.withField("template", "The result template of the discovery view."),
                                describeDiscoveryView.withField("styles", "An array of result style strings of the discovery view."),
                                describeDiscoveryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                                describeDiscoveryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                                subsectionWithPath("_links").description("<<resources-discovery-views-list-links, Links>> to other resources.")
                            )
                        )
                    );
        // @formatter:on
    }

    @Test
    public void testGetDiscoveryView() throws JsonProcessingException, Exception {
        performCreateDiscoveryView();
        DiscoveryView discoveryView = viewRepo.findByName("People").get();
        // @formatter:off
        mockMvc.perform(
            get("/discoveryViews/{id}", discoveryView.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name", equalTo("People")))
                .andDo(
                    document(
                        "discoveryViews/find-by-id",
                        pathParameters(
                                describeDiscoveryView.withParameter("id", "The Theme id.")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("discoveryView").description("The Discovery View link for this resource.")
                        ),
                        responseFields(
                            describeDiscoveryView.withField("name", "The name of the Discovery View."),
                            // describeDiscoveryView.withField("collection", "The collection of the discovery view."),
                            // NOTE: Can't find resource for bundle java.util.PropertyResourceBundle, key edu.tamu.scholars.middleware.view.annotation.ValidDiscoveryCollection.description
                            fieldWithPath("collection").description("The collection of the discovery view."),
                            describeDiscoveryView.withField("layout", "The layout of the discovery view."),
                            describeDiscoveryView.withField("template", "The result template of the discovery view."),
                            describeDiscoveryView.withField("styles", "An array of result style strings of the discovery view."),
                            describeDiscoveryView.withSubsection("facets", "An array of <<resources-facets, Facet resources>>."),
                            describeDiscoveryView.withSubsection("filters", "An array of <<resources-filters, Filters resources>>."),
                            subsectionWithPath("_links").description("<<resources-discovery-view-list-links, Links>> to other resources.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testGetDiscoveryViews() throws JsonProcessingException, Exception {
        performCreateDiscoveryView();
        // @formatter:off
        mockMvc.perform(
            get("/discoveryViews").param("page", "0").param("size", "20").param("sort", "name"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("page.size", equalTo(20)))
                .andExpect(jsonPath("page.totalElements", equalTo(1)))
                .andExpect(jsonPath("page.totalPages", equalTo(1)))
                .andExpect(jsonPath("page.number", equalTo(1)))
                .andExpect(jsonPath("_embedded.discoveryViews[0].name", equalTo("People")))
                .andDo(
                    document(
                        "discoveryViews/directory",
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
                            subsectionWithPath("_embedded.discoveryViews").description("An array of <<resources-discovery-views, Discovery View resources>>."),
                            subsectionWithPath("_links").description("<<resources-discovery-view-list-links, Links>> to other resources."),
                            subsectionWithPath("page").description("Page details for <<resources-discovery-views, Discovery View resources>>.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testDeleteTheme() throws JsonProcessingException, Exception {
        performCreateDiscoveryView();
        DiscoveryView discoveryView = viewRepo.findByName("People").get();
        // @formatter:off
        mockMvc.perform(
            delete("/discoveryViews/{id}", discoveryView.getId())
                .cookie(loginAdmin()))
                    .andExpect(status().isNoContent())
                    .andDo(
                        document(
                            "discoveryViews/delete",
                            pathParameters(
                                parameterWithName("id").description("The Discovery View id")
                            )
                        )
                    );
        // @formatter:on
    }

    protected Cookie loginAdmin() throws Exception {
        User user = getMockAdmin();
        MvcResult discovery = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        return discovery.getResponse().getCookie("SESSION");
    }

    private ResultActions performCreateDiscoveryView() throws JsonProcessingException, Exception {
        createMockAdmin();
        DiscoveryView discoveryView = getMockView();

        // @formatter:off
        return mockMvc.perform(
            post("/discoveryViews")
                .content(objectMapper.writeValueAsString(discoveryView))
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(loginAdmin()))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
                );
        // @formatter:on
    }

    private ResultActions performUpdateDiscoveryView() throws JsonProcessingException, Exception {
        DiscoveryView discoveryView = viewRepo.findByName("People").get();
        discoveryView.setName("Organizations");
        discoveryView.setCollection("organizations");

        // @formatter:off
        return mockMvc.perform(
            put("/discoveryViews/{id}", discoveryView.getId())
                .content(objectMapper.writeValueAsString(discoveryView))
                .cookie(loginAdmin()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
                );
        // @formatter:on
    }

    @Override
    protected DiscoveryView getMockView() {
        return getMockDiscoveryView();
    }

}

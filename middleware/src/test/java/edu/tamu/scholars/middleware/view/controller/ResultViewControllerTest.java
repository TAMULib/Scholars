package edu.tamu.scholars.middleware.view.controller;

import static edu.tamu.scholars.middleware.view.ViewTestUtility.getMockResultView;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
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
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.utility.ConstraintDescriptionsHelper;
import edu.tamu.scholars.middleware.view.ViewIntegrationTest;
import edu.tamu.scholars.middleware.view.model.ResultView;
import edu.tamu.scholars.middleware.view.model.repo.ResultViewRepo;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(SpringExtension.class)
public class ResultViewControllerTest extends ViewIntegrationTest<ResultView, ResultViewRepo> {

    private static final ConstraintDescriptionsHelper describeResultView = new ConstraintDescriptionsHelper(ResultView.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateResultView() throws JsonProcessingException, Exception {
        // @formatter:off
        performCreateResultView()
            .andDo(
                document(
                    "resultViews/create",
                    requestFields(
                        describeResultView.withField("name", "The name of the result view."),
                        describeResultView.withField("template", "The template of the result view.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("resultView").description("The result view link for this resource.")
                    ),
                    responseFields(
                        describeResultView.withField("name", "The name of the result view."),
                        describeResultView.withField("template", "The template of the result view."),
                        subsectionWithPath("_links").description("<<resources-result-views-list-links, Links>> to other resources.")
                    )
                )
            );
       // @formatter:on
    }

    @Test
    public void testUpdateResultView() throws JsonProcessingException, Exception {
        performCreateResultView();

        // @formatter:off
        performUpdateResultView()
            .andDo(
                document(
                    "resultViews/update",
                    pathParameters(
                        describeResultView.withParameter("id", "The Result View id.")
                    ),
                    requestFields(
                        describeResultView.withField("id", "The Result View id."),
                        describeResultView.withField("name", "The name of the Result View."),
                        describeResultView.withField("template", "The template of the Result View.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("resultView").description("The Result View link for this resource.")
                    ),
                    responseFields(
                        describeResultView.withField("name", "The name of the Result View."),
                        describeResultView.withField("template", "The template of the Result View."),
                        subsectionWithPath("_links").description("<<resources-result-views-list-links, Links>> to other resources.")
                    )
                )
            );
        // @formatter:on
    }

    @Test
    public void testGetResultView() throws JsonProcessingException, Exception {
        performCreateResultView();
        ResultView resultView = viewRepo.findByName("People").get();
        // @formatter:off
        mockMvc.perform(get("/resultViews/{id}", resultView.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("name", equalTo("People")))
                .andDo(
                    document(
                        "resultViews/find-by-id",
                        pathParameters(
                                describeResultView.withParameter("id", "The Theme id.")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("resultView").description("The Result View link for this resource.")
                        ),
                        responseFields(
                            describeResultView.withField("name", "The name of the Result View."),
                            describeResultView.withField("template", "The template of the Result View."),
                            subsectionWithPath("_links").description("<<resources-result-views-list-links, Links>> to other resources.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testGetResultViews() throws JsonProcessingException, Exception {
        performCreateResultView();
        // @formatter:off
        mockMvc.perform(
            get("/resultViews").param("page", "0").param("size", "20").param("sort", "name"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("page.size", equalTo(20)))
                .andExpect(jsonPath("page.totalElements", equalTo(1)))
                .andExpect(jsonPath("page.totalPages", equalTo(1)))
                .andExpect(jsonPath("page.number", equalTo(0)))
                .andExpect(jsonPath("_embedded.resultViews[0].name", equalTo("People")))
                .andDo(
                    document(
                        "resultViews/directory",
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
                            subsectionWithPath("_embedded.resultViews").description("An array of <<resources-resultViews, Result View resources>>."),
                            subsectionWithPath("_links").description("<<resources-result-view-list-links, Links>> to other resources."),
                            subsectionWithPath("page").description("Page details for <<resources-result-views, Result View resources>>.")
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testDeleteTheme() throws JsonProcessingException, Exception {
        performCreateResultView();
        ResultView resultView = viewRepo.findByName("People").get();
        // @formatter:off
        mockMvc.perform(delete("/resultViews/{id}", resultView.getId()).cookie(loginAdmin()))
            .andExpect(status().isNoContent())
            .andDo(
                document(
                    "resultViews/delete",
                    pathParameters(
                        parameterWithName("id").description("The Result View id")
                    )
                )
            );
        // @formatter:on
    }

    protected Cookie loginAdmin() throws Exception {
        User user = getMockAdmin();
        MvcResult result = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        return result.getResponse().getCookie("SESSION");
    }

    private ResultActions performCreateResultView() throws JsonProcessingException, Exception {
        createMockAdmin();
        ResultView resultView = getMockView();
        // @formatter:off
        return mockMvc.perform(
            post("/resultViews")
                .content(objectMapper.writeValueAsString(resultView))
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(loginAdmin()))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
                );
        // @formatter:on
    }

    private ResultActions performUpdateResultView() throws JsonProcessingException, Exception {
        ResultView resultView = viewRepo.findByName("People").get();
        resultView.setName("Organizations");
        resultView.setTemplate("<h1>Organization templated from WSYWIG</h1>");
        // @formatter:off
        return mockMvc.perform(
            put("/resultViews/{id}", resultView.getId())
                .content(objectMapper.writeValueAsString(resultView))
                .cookie(loginAdmin()))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(HAL_JSON_UTF8_VALUE)
                );
        // @formatter:on
    }

    @Override
    protected ResultView getMockView() {
        return getMockResultView();
    }

}

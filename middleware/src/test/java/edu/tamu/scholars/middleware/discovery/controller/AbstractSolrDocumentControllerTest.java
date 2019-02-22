package edu.tamu.scholars.middleware.discovery.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import edu.tamu.scholars.middleware.discovery.AbstractSolrDocumentIntegrationTest;
import edu.tamu.scholars.middleware.discovery.model.AbstractSolrDocument;
import edu.tamu.scholars.middleware.discovery.model.repo.SolrDocumentRepo;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(SpringExtension.class)
public abstract class AbstractSolrDocumentControllerTest<D extends AbstractSolrDocument, R extends SolrDocumentRepo<D>> extends AbstractSolrDocumentIntegrationTest<D, R> {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDocumentsPage() throws Exception {
        createDocuments();
        // @formatter:off
        mockMvc.perform(
            get(getPath()).param("page", "0").param("size", "20").param("sort", "id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("page.size", equalTo(20)))
                .andExpect(jsonPath("page.totalElements", equalTo(mockDocuments.size())))
                .andExpect(jsonPath("page.totalPages", equalTo(1)))
                .andExpect(jsonPath("page.number", equalTo(0)))
                .andDo(
                    document(
                        getPath().substring(1),
                        requestParameters(
                            parameterWithName("page").description("The page number"),
                            parameterWithName("size").description("The page size"),
                            parameterWithName("sort").description("The page sort [field,asc/desc]")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource"),
                            linkWithRel("profile").description("The ALPS profile for this resource")
                        ),
                        responseFields(
                            subsectionWithPath("_embedded." + getPath().substring(1)).description(String.format("An array of <<resources-%s, %s resources>>", getPath().substring(1, getPath().length() - 1), getType().getSimpleName())),
                            subsectionWithPath("_links").description(String.format("<<resources-%s-list-links, Links>> to other resources", getPath().substring(1, getPath().length() - 1))),
                            subsectionWithPath("page").description(String.format("Page details for <<resources-%s, %s resources>>", getPath().substring(1, getPath().length() - 1), getType().getSimpleName()))
                        )
                    )
                );
        // @formatter:on
    }

    @Test
    public void testGetDocument() throws Exception {
        createDocuments();
        for (D mockDocument : mockDocuments) {
            // @formatter:off
            mockMvc.perform(get(getPath() + "/{id}", mockDocument.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andDo(
                    document(
                        getPath().substring(1) + "/find-by-id",
                        pathParameters( 
                            parameterWithName("id").description(String.format("The %s id", getType().getSimpleName()))
                        )
                    )
                );
            // @formatter:on
        }
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostDocument() throws Exception {
        mockMvc.perform(post(getPath()).content("{}")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPutDocument() throws Exception {
        mockMvc.perform(put(getPath() + "/" + mockDocuments.get(0).getId()).content("{}")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPatchDocument() throws Exception {
        mockMvc.perform(put(getPath() + "/" + mockDocuments.get(0).getId()).content("{}")).andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteDocument() throws Exception {
        mockMvc.perform(delete(getPath() + "/" + mockDocuments.get(0).getId())).andExpect(status().isMethodNotAllowed());
    }

    protected abstract String getPath();

}

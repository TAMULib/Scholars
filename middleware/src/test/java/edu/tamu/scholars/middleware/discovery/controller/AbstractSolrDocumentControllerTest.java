package edu.tamu.scholars.middleware.discovery.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
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
        mockMvc.perform(get(getPath()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
            .andExpect(jsonPath("page.size", equalTo(20)))
            .andExpect(jsonPath("page.totalElements", equalTo(mockDocuments.size())))
            .andExpect(jsonPath("page.totalPages", equalTo(1)))
            .andExpect(jsonPath("page.number", equalTo(0)))
            .andDo(document(getPath().substring(1) + "/page"));
        // @formatter:on
    }

    @Test
    public void testGetDocument() throws Exception {
        createDocuments();
        for (D mockDocument : mockDocuments) {
            // @formatter:off
            mockMvc.perform(get(getPath() + "/" + mockDocument.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andDo(document(getPath().substring(1) + "/find-by-id"));
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

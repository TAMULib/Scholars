package edu.tamu.scholars.middleware.theme.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import java.util.ArrayList;
import java.util.List;

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
import edu.tamu.scholars.middleware.theme.ThemeIntegrationTest;
import edu.tamu.scholars.middleware.theme.model.Style;
import edu.tamu.scholars.middleware.theme.model.Theme;
import edu.tamu.scholars.middleware.utility.ConstraintDescriptionsHelper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(SpringExtension.class)
public class ThemeControllerTest extends ThemeIntegrationTest {
    private static final ConstraintDescriptionsHelper describeTheme = new ConstraintDescriptionsHelper(Theme.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateTheme() throws JsonProcessingException, Exception {
        // @formatter:off
        performCreateTheme()
            .andDo(
                document(
                    "themes/create",
                    requestFields(
                        describeTheme.withField("active", "Designates the theme currently in use."),
                        describeTheme.withField("name", "The name of the theme."),
                        describeTheme.withField("organization", "An organization the theme belongs to."),
                        describeTheme.withSubsection("home", "A <<resources-home, Home resource>>."),
                        describeTheme.withSubsection("header", "A <<resources-header, Header resource>>."),
                        describeTheme.withSubsection("footer", "A <<resources-header, Footer resource>>."),
                        describeTheme.withSubsection("colors", "An array of <<resources-color, Color resources>>."),
                        describeTheme.withSubsection("variants", "An array of <<resources-variants, Variants resources>>."),
                        describeTheme.withSubsection("variables", "An array of <<resources-variables, Variables resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("theme").description("The theme link for this resource.")
                    ),
                    responseFields(
                        describeTheme.withField("active", "Designates the theme currently in use."),
                        describeTheme.withField("name", "The name of the theme."),
                        describeTheme.withField("organization", "An organization the theme belongs to."),
                        describeTheme.withSubsection("home", "A <<resources-home, Home resource>>."),
                        describeTheme.withSubsection("header", "A <<resources-header, Header resource>>."),
                        describeTheme.withSubsection("footer", "A <<resources-header, Footer resource>>."),
                        describeTheme.withSubsection("colors", "An array of <<resources-color, Color resources>>."),
                        describeTheme.withSubsection("variants", "An array of <<resources-variants, Variants resources>>."),
                        describeTheme.withSubsection("variables", "An array of <<resources-variables, Variables resources>>."),
                        subsectionWithPath("_links").description("<<resources-user-list-links, Links>> to other resources.")
                    )
                )
            );
       // @formatter:on
    }

    @Test
    public void testUpdateTheme() throws JsonProcessingException, Exception {
        performCreateTheme();

        // @formatter:off
        performUpdateTheme()
            .andDo(
                document(
                    "themes/update",
                    pathParameters(
                        describeTheme.withParameter("id", "The Theme id.")
                    ),
                    requestFields(
                        describeTheme.withField("id", "The Theme id."),
                        describeTheme.withField("active", "Designates the theme currently in use."),
                        describeTheme.withField("name", "The name of the theme."),
                        describeTheme.withField("organization", "An organization the theme belongs to."),
                        describeTheme.withSubsection("home", "A <<resources-home, Home resource>>."),
                        describeTheme.withSubsection("header", "A <<resources-header, Header resource>>."),
                        describeTheme.withSubsection("footer", "A <<resources-header, Footer resource>>."),
                        describeTheme.withSubsection("colors", "An array of <<resources-color, Color resources>>."),
                        describeTheme.withSubsection("variants", "An array of <<resources-variants, Variants resources>>."),
                        describeTheme.withSubsection("variables", "An array of <<resources-variables, Variables resources>>.")
                    ),
                    links(
                        linkWithRel("self").description("Canonical link for this resource."),
                        linkWithRel("theme").description("The theme link for this resource.")
                    ),
                    responseFields(
                        describeTheme.withField("active", "Designates the theme currently in use."),
                        describeTheme.withField("name", "The name of the theme."),
                        describeTheme.withField("organization", "An organization the theme belongs to."),
                        describeTheme.withSubsection("home", "A <<resources-home, Home resource>>."),
                        describeTheme.withSubsection("header", "A <<resources-header, Header resource>>."),
                        describeTheme.withSubsection("footer", "A <<resources-header, Footer resource>>."),
                        describeTheme.withSubsection("colors", "An array of <<resources-color, Color resources>>."),
                        describeTheme.withSubsection("variants", "An array of <<resources-variants, Variants resources>>."),
                        describeTheme.withSubsection("variables", "An array of <<resources-variables, Variables resources>>."),
                        subsectionWithPath("_links").description("<<resources-user-list-links, Links>> to other resources.")
                    )
                )
            );
		// @formatter:on
    }

    @Test
    public void testPatchTheme() throws JsonProcessingException, Exception {
        performCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        // @formatter:off
		mockMvc.perform(patch("/themes/{id}", theme.getId())
	        .content("{\"active\": true, \"header\": { \"navbar\": { \"brandText\": \"Hello, Scholars!\"}}}")
			.cookie(loginAdmin()))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
    			.andExpect(jsonPath("active", equalTo(true)))
    			.andExpect(jsonPath("name", equalTo("Test")))
    			.andExpect(jsonPath("organization", equalTo("Testing Unlimited")))
    			.andExpect(jsonPath("header.navbar.brandText", equalTo("Hello, Scholars!")))
    			.andDo(
                    document(
                        "themes/patch",
                        pathParameters(
                            describeTheme.withParameter("id", "The Theme id.")
                        ),
                        requestFields(
                            describeTheme.withField("active", "Designates the theme currently in use.").optional(),
                            describeTheme.withField("name", "The name of the theme.").optional().ignored(),
                            describeTheme.withField("organization", "An organization the theme belongs to.").optional().ignored(),
                            describeTheme.withSubsection("home", "A <<resources-home, Home resource>>.").optional().ignored(),
                            describeTheme.withSubsection("header", "A <<resources-header, Header resource>>.").optional(),
                            describeTheme.withSubsection("footer", "A <<resources-header, Footer resource>>.").optional().ignored(),
                            describeTheme.withSubsection("colors", "An array of <<resources-color, Color resources>>.").optional().ignored(),
                            describeTheme.withSubsection("variants", "An array of <<resources-variants, Variants resources>>.").optional().ignored(),
                            describeTheme.withSubsection("variables", "An array of <<resources-variables, Variables resources>>.").optional().ignored()
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("theme").description("The theme link for this resource.")
                        ),
                        responseFields(
                            describeTheme.withField("active", "Designates the theme currently in use."),
                            describeTheme.withField("name", "The name of the theme."),
                            describeTheme.withField("organization", "An organization the theme belongs to."),
                            describeTheme.withSubsection("home", "A <<resources-home, Home resource>>."),
                            describeTheme.withSubsection("header", "A <<resources-header, Header resource>>."),
                            describeTheme.withSubsection("footer", "A <<resources-header, Footer resource>>."),
                            describeTheme.withSubsection("colors", "An array of <<resources-color, Color resources>>."),
                            describeTheme.withSubsection("variants", "An array of <<resources-variants, Variants resources>>."),
                            describeTheme.withSubsection("variables", "An array of <<resources-variables, Variables resources>>."),
                            subsectionWithPath("_links").description("<<resources-user-list-links, Links>> to other resources.")
                        )
                    )
                );
		// @formatter:on
    }

    @Test
    public void testPatchThemeColors() throws JsonProcessingException, Exception {
        performCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();

        List<Style> colors = new ArrayList<Style>();
        colors.add(new Style("--red", "#dc3545"));
        colors.add(new Style("--green", "#28a745"));
        colors.add(new Style("--blue", "#007bff"));

        // @formatter:off
		mockMvc.perform(patch("/themes/" + theme.getId())
	        .content("{\"colors\": " + objectMapper.writeValueAsString(colors) + "}")
	        .cookie(loginAdmin()))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
    			.andExpect(jsonPath("colors[0].key", equalTo("--red")))
    			.andExpect(jsonPath("colors[0].value", equalTo("#dc3545")))
    			.andExpect(jsonPath("colors[1].key", equalTo("--green")))
    			.andExpect(jsonPath("colors[1].value", equalTo("#28a745")))
    			.andExpect(jsonPath("colors[2].key", equalTo("--blue")))
    			.andExpect(jsonPath("colors[2].value", equalTo("#007bff")));
		// @formatter:on
    }

    @Test
    public void testPatchThemeVariants() throws JsonProcessingException, Exception {
        performCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();

        List<Style> variants = new ArrayList<Style>();
        variants.add(new Style("--primary", "#500000"));
        variants.add(new Style("--secondary", "#65a6d1"));
        variants.add(new Style("--success", "#28a745"));

        // @formatter:off
		mockMvc.perform(patch("/themes/" + theme.getId())
	        .content("{\"variants\": " + objectMapper.writeValueAsString(variants) + "}")
	        .cookie(loginAdmin()))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
    			.andExpect(jsonPath("variants[0].key", equalTo("--primary")))
    			.andExpect(jsonPath("variants[0].value", equalTo("#500000")))
    			.andExpect(jsonPath("variants[1].key", equalTo("--secondary")))
    			.andExpect(jsonPath("variants[1].value", equalTo("#65a6d1")))
    			.andExpect(jsonPath("variants[2].key", equalTo("--success")))
    			.andExpect(jsonPath("variants[2].value", equalTo("#28a745")));
		// @formatter:on
    }

    @Test
    public void testPatchThemeVariables() throws JsonProcessingException, Exception {
        performCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();

        List<Style> variables = new ArrayList<Style>();
        variables.add(new Style("--accent", "#00ff00"));
        variables.add(new Style("--navigation-color", "#ff0000"));
        variables.add(new Style("--navbar-color", "#0000ff"));

        // @formatter:off
		mockMvc.perform(patch("/themes/" + theme.getId())
	        .content("{\"variables\": " + objectMapper.writeValueAsString(variables) + "}")
	        .cookie(loginAdmin()))
    			.andExpect(status().isOk())
    			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
    			.andExpect(jsonPath("variables[0].key", equalTo("--accent")))
    			.andExpect(jsonPath("variables[0].value", equalTo("#00ff00")))
    			.andExpect(jsonPath("variables[1].key", equalTo("--navigation-color")))
    			.andExpect(jsonPath("variables[1].value", equalTo("#ff0000")))
    			.andExpect(jsonPath("variables[2].key", equalTo("--navbar-color")))
    			.andExpect(jsonPath("variables[2].value", equalTo("#0000ff")));
		// @formatter:on
    }

    @Test
    public void testGetTheme() throws JsonProcessingException, Exception {
        performCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        // @formatter:off
		mockMvc.perform(get("/themes/{id}", theme.getId())
	        .cookie(loginAdmin()))
    		    .andExpect(status().isOk())
    			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE)).andExpect(jsonPath("active", equalTo(false)))
    			.andExpect(jsonPath("name", equalTo("Test")))
    			.andExpect(jsonPath("organization", equalTo("Testing Unlimited")))
    			.andDo(
                    document(
                        "themes/find-by-id",
                        pathParameters(
                            describeTheme.withParameter("id", "The Theme id.")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("theme").description("The theme link for this resource.")
                        ),
                        responseFields(
                            describeTheme.withField("active", "Designates the theme currently in use."),
                            describeTheme.withField("name", "The name of the theme."),
                            describeTheme.withField("organization", "An organization the theme belongs to."),
                            describeTheme.withSubsection("home", "A <<resources-home, Home resource>>."),
                            describeTheme.withSubsection("header", "A <<resources-header, Header resource>>."),
                            describeTheme.withSubsection("footer", "A <<resources-header, Footer resource>>."),
                            describeTheme.withSubsection("colors", "An array of <<resources-color, Color resources>>."),
                            describeTheme.withSubsection("variants", "An array of <<resources-variants, Variants resources>>."),
                            describeTheme.withSubsection("variables", "An array of <<resources-variables, Variables resources>>."),
                            subsectionWithPath("_links").description("<<resources-user-list-links, Links>> to other resources.")
                        )
                    )
                );
		// @formatter:on
    }

    @Test
    public void testGetThemes() throws JsonProcessingException, Exception {
        performCreateTheme();
        // @formatter:off
		mockMvc.perform(
	        get("/themes").param("page", "0").param("size", "20").param("sort", "name")
	        .cookie(loginAdmin()))
    		    .andExpect(status().isOk())
    			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
    			.andExpect(jsonPath("page.size", equalTo(20)))
    			.andExpect(jsonPath("page.totalElements", equalTo(1)))
    			.andExpect(jsonPath("page.totalPages", equalTo(1)))
    			.andExpect(jsonPath("page.number", equalTo(0)))
    			.andExpect(jsonPath("_embedded.themes[0].active", equalTo(false)))
    			.andExpect(jsonPath("_embedded.themes[0].name", equalTo("Test")))
    			.andExpect(jsonPath("_embedded.themes[0].organization", equalTo("Testing Unlimited")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroesNavigable", equalTo(false)))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].imageUri", equalTo("/assets/images/hero.png")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].imageAlt", equalTo("Hero")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].watermarkImageUri", equalTo("/assets/images/watermark.png")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].watermarkText", equalTo("Watermark")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].baseText", equalTo("This is only a test!")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].fontColor", equalTo("#ffffff")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].linkColor", equalTo("#000000")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].linkHoverColor", equalTo("#ffc222")))
    			.andExpect(jsonPath("_embedded.themes[0].home.heroes[0].interval", equalTo(5000)))
    			.andExpect(jsonPath("_embedded.themes[0].home.variables[0].key", equalTo("--test")))
    			.andExpect(jsonPath("_embedded.themes[0].home.variables[0].value", equalTo("home")))
    			.andExpect(jsonPath("_embedded.themes[0].header.navbar.brandText", equalTo("Hello, World!")))
    			.andExpect(jsonPath("_embedded.themes[0].header.navbar.brandUri", equalTo("http://localhost:4200")))
    			.andExpect(jsonPath("_embedded.themes[0].header.navbar.logoUri", equalTo("/assets/images/logo.png")))
    			.andExpect(jsonPath("_embedded.themes[0].header.navbar.links[0].label", equalTo("Home")))
    			.andExpect(jsonPath("_embedded.themes[0].header.navbar.links[0].uri", equalTo("http://localhost:4200")))
    			.andExpect(jsonPath("_embedded.themes[0].header.navbar.variables[0].key", equalTo("--test")))
    			.andExpect(jsonPath("_embedded.themes[0].header.navbar.variables[0].value", equalTo("navbar")))
    			.andExpect(jsonPath("_embedded.themes[0].header.banner.imageUri", equalTo("/assets/images/banner.png")))
    			.andExpect(jsonPath("_embedded.themes[0].header.banner.altText", equalTo("Test")))
    			.andExpect(jsonPath("_embedded.themes[0].header.banner.variables[0].key", equalTo("--test")))
    			.andExpect(jsonPath("_embedded.themes[0].header.banner.variables[0].value", equalTo("banner")))
    			.andExpect(jsonPath("_embedded.themes[0].header.variables[0].key", equalTo("--test")))
    			.andExpect(jsonPath("_embedded.themes[0].header.variables[0].value", equalTo("header")))
    			.andExpect(jsonPath("_embedded.themes[0].footer.links[0].label", equalTo("About")))
    			.andExpect(jsonPath("_embedded.themes[0].footer.links[0].uri", equalTo("http://localhost:4200/about")))
    			.andExpect(jsonPath("_embedded.themes[0].footer.variables[0].key", equalTo("--test")))
    			.andExpect(jsonPath("_embedded.themes[0].footer.variables[0].value", equalTo("footer")))
    			.andExpect(jsonPath("_embedded.themes[0].colors[0].key", equalTo("--red")))
    			.andExpect(jsonPath("_embedded.themes[0].colors[0].value", equalTo("#ff0000")))
    			.andExpect(jsonPath("_embedded.themes[0].colors[1].key", equalTo("--green")))
    			.andExpect(jsonPath("_embedded.themes[0].colors[1].value", equalTo("#00ff00")))
    			.andExpect(jsonPath("_embedded.themes[0].colors[2].key", equalTo("--blue")))
    			.andExpect(jsonPath("_embedded.themes[0].colors[2].value", equalTo("#0000ff")))
    			.andExpect(jsonPath("_embedded.themes[0].variants[0].key", equalTo("--primary")))
    			.andExpect(jsonPath("_embedded.themes[0].variants[0].value", equalTo("#007bff")))
    			.andExpect(jsonPath("_embedded.themes[0].variants[1].key", equalTo("--secondary")))
    			.andExpect(jsonPath("_embedded.themes[0].variants[1].value", equalTo("#6c757d")))
    			.andExpect(jsonPath("_embedded.themes[0].variants[2].key", equalTo("--success")))
    			.andExpect(jsonPath("_embedded.themes[0].variants[2].value", equalTo("#28a745")))
    			.andExpect(jsonPath("_embedded.themes[0].variables[0].key", equalTo("--accent")))
    			.andExpect(jsonPath("_embedded.themes[0].variables[0].value", equalTo("#ffc222")))
    			.andExpect(jsonPath("_embedded.themes[0].variables[1].key", equalTo("--navigation-color")))
    			.andExpect(jsonPath("_embedded.themes[0].variables[1].value", equalTo("#3c0000")))
    			.andExpect(jsonPath("_embedded.themes[0].variables[2].key", equalTo("--navbar-color")))
    			.andExpect(jsonPath("_embedded.themes[0].variables[2].value", equalTo("#ffffff")))
                .andDo(
                    document(
                        "themes/directory",
                        requestParameters(
                            parameterWithName("page").description("The page number."),
                            parameterWithName("size").description("The page size."),
                            parameterWithName("sort").description("The page sort.")
                        ),
                        links(
                            linkWithRel("self").description("Canonical link for this resource."),
                            linkWithRel("profile").description("The ALPS profile for this resource."),
                            linkWithRel("search").description("Search link for this resource.")
                        ),
                        responseFields(
                            subsectionWithPath("_embedded.themes").description("An array of <<resources-theme, Theme resources>>."),
                            subsectionWithPath("_links").description("<<resources-theme-list-links, Links>> to other resources."),
                            subsectionWithPath("page").description("Page details for <<resources-theme, Theme resources>>.")
                        )
                    )
                );
		// @formatter:on
    }

    @Test
    public void testGetActiveTheme() throws JsonProcessingException, Exception {
        performCreateTheme();
        performUpdateTheme();
        // @formatter:off
		mockMvc.perform(get("/themes/search/active"))
		    .andExpect(status().isOk())
			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
			.andExpect(jsonPath("active", equalTo(true)))
			.andExpect(jsonPath("name", equalTo("Test")))
			.andExpect(jsonPath("organization", equalTo("Testing Limited")))
            .andDo(document("themes/active"));
		// @formatter:on
    }

    @Test
    public void testDeleteTheme() throws JsonProcessingException, Exception {
        performCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        // @formatter:off
        mockMvc.perform(delete("/themes/{id}", theme.getId()).cookie(loginAdmin()))
            .andExpect(status().isNoContent())
            .andDo(
                document(
                    "themes/delete",
                    pathParameters(
                        parameterWithName("id").description("The Theme id")
                    )
                )
            );
        // @formatter:on
    }

    @Test
    public void testChangeActiveTheme() throws JsonProcessingException, Exception {
        performCreateTheme();
        performUpdateTheme();
        Theme theme = getMockTheme();
        theme.setName("Foo");
        mockMvc.perform(post("/themes").content(objectMapper.writeValueAsString(theme)).cookie(loginAdmin())).andExpect(status().isCreated());
        theme = themeRepo.findByName("Foo").get();
        mockMvc.perform(patch("/themes/" + theme.getId()).content("{\"active\": true}").cookie(loginAdmin())).andExpect(status().isOk());
        Theme initialTheme = themeRepo.findByName("Test").get();
        assertFalse(initialTheme.isActive());
        Theme activeTheme = themeRepo.findByActiveTrue();
        assertEquals(theme.getName(), activeTheme.getName());
    }

    @Test
    public void testCreateActiveTheme() throws JsonProcessingException, Exception {
        createMockAdmin();
        Theme theme = getMockTheme();
        theme.setActive(true);
        // @formatter:off
        mockMvc.perform(post("/themes")
            .content(objectMapper.writeValueAsString(theme))
            .cookie(loginAdmin()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("You cannot create an active theme!")));
        // @formatter:on
    }

    @Test
    public void testDeleteActiveTheme() throws JsonProcessingException, Exception {
        performCreateTheme();
        performUpdateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        // @formatter:off
        mockMvc.perform(delete("/themes/" + theme.getId())
            .cookie(loginAdmin()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(equalTo("You cannot delete the active theme!")));
        // @formatter:on
    }

    private Cookie loginAdmin() throws Exception {
        User user = getMockAdmin();
        MvcResult result = mockMvc.perform(post("/login").param("username", user.getEmail()).param("password", "HelloWorld123!")).andReturn();
        return result.getResponse().getCookie("SESSION");
    }

    private ResultActions performCreateTheme() throws JsonProcessingException, Exception {
        createMockAdmin();
        Theme theme = getMockTheme();
        // @formatter:off
        return mockMvc.perform(post("/themes")
            .content(objectMapper.writeValueAsString(theme)).contentType(MediaType.APPLICATION_JSON).cookie(loginAdmin()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("active", equalTo(false)))
                .andExpect(jsonPath("name", equalTo("Test")))
                .andExpect(jsonPath("organization", equalTo("Testing Unlimited")))
                .andExpect(jsonPath("home.heroesNavigable", equalTo(false)))
                .andExpect(jsonPath("home.heroes[0].imageUri", equalTo("/assets/images/hero.png")))
                .andExpect(jsonPath("home.heroes[0].imageAlt", equalTo("Hero")))
                .andExpect(jsonPath("home.heroes[0].watermarkImageUri", equalTo("/assets/images/watermark.png")))
                .andExpect(jsonPath("home.heroes[0].watermarkText", equalTo("Watermark")))
                .andExpect(jsonPath("home.heroes[0].baseText", equalTo("This is only a test!")))
                .andExpect(jsonPath("home.heroes[0].fontColor", equalTo("#ffffff")))
                .andExpect(jsonPath("home.heroes[0].linkColor", equalTo("#000000")))
                .andExpect(jsonPath("home.heroes[0].linkHoverColor", equalTo("#ffc222")))
                .andExpect(jsonPath("home.heroes[0].interval", equalTo(5000)))
                .andExpect(jsonPath("home.variables[0].key", equalTo("--test")))
                .andExpect(jsonPath("home.variables[0].value", equalTo("home")))
                .andExpect(jsonPath("header.navbar.brandText", equalTo("Hello, World!")))
                .andExpect(jsonPath("header.navbar.brandUri", equalTo("http://localhost:4200")))
                .andExpect(jsonPath("header.navbar.logoUri", equalTo("/assets/images/logo.png")))
                .andExpect(jsonPath("header.navbar.links[0].label", equalTo("Home")))
                .andExpect(jsonPath("header.navbar.links[0].uri", equalTo("http://localhost:4200")))
                .andExpect(jsonPath("header.navbar.variables[0].key", equalTo("--test")))
                .andExpect(jsonPath("header.navbar.variables[0].value", equalTo("navbar")))
                .andExpect(jsonPath("header.banner.imageUri", equalTo("/assets/images/banner.png")))
                .andExpect(jsonPath("header.banner.altText", equalTo("Test")))
                .andExpect(jsonPath("header.banner.variables[0].key", equalTo("--test")))
                .andExpect(jsonPath("header.banner.variables[0].value", equalTo("banner")))
                .andExpect(jsonPath("header.variables[0].key", equalTo("--test")))
                .andExpect(jsonPath("header.variables[0].value", equalTo("header")))
                .andExpect(jsonPath("footer.links[0].label", equalTo("About")))
                .andExpect(jsonPath("footer.links[0].uri", equalTo("http://localhost:4200/about")))
                .andExpect(jsonPath("footer.variables[0].key", equalTo("--test")))
                .andExpect(jsonPath("footer.variables[0].value", equalTo("footer")))
                .andExpect(jsonPath("colors[0].key", equalTo("--red")))
                .andExpect(jsonPath("colors[0].value", equalTo("#ff0000")))
                .andExpect(jsonPath("colors[1].key", equalTo("--green")))
                .andExpect(jsonPath("colors[1].value", equalTo("#00ff00")))
                .andExpect(jsonPath("colors[2].key", equalTo("--blue")))
                .andExpect(jsonPath("colors[2].value", equalTo("#0000ff")))
                .andExpect(jsonPath("variants[0].key", equalTo("--primary")))
                .andExpect(jsonPath("variants[0].value", equalTo("#007bff")))
                .andExpect(jsonPath("variants[1].key", equalTo("--secondary")))
                .andExpect(jsonPath("variants[1].value", equalTo("#6c757d")))
                .andExpect(jsonPath("variants[2].key", equalTo("--success")))
                .andExpect(jsonPath("variants[2].value", equalTo("#28a745")))
                .andExpect(jsonPath("variables[0].key", equalTo("--accent")))
                .andExpect(jsonPath("variables[0].value", equalTo("#ffc222")))
                .andExpect(jsonPath("variables[1].key", equalTo("--navigation-color")))
                .andExpect(jsonPath("variables[1].value", equalTo("#3c0000")))
                .andExpect(jsonPath("variables[2].key", equalTo("--navbar-color")))
                .andExpect(jsonPath("variables[2].value", equalTo("#ffffff")));
        // @formatter:on
    }

    private ResultActions performUpdateTheme() throws JsonProcessingException, Exception {
        Theme theme = themeRepo.findByName("Test").get();
        theme.setActive(true);
        theme.setOrganization("Testing Limited");
        theme.getHeader().getBanner().setAltText("Tested");
        // @formatter:off
        return mockMvc.perform(put("/themes/{id}", theme.getId())
            .content(objectMapper.writeValueAsString(theme))
            .cookie(loginAdmin()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("active", equalTo(true)))
                .andExpect(jsonPath("name", equalTo("Test")))
                .andExpect(jsonPath("organization", equalTo("Testing Limited")))
                .andExpect(jsonPath("header.banner.altText", equalTo("Tested")));
        // @formatter:on
    }

}

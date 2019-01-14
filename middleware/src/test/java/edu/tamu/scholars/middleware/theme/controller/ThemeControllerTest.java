package edu.tamu.scholars.middleware.theme.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.hateoas.MediaTypes.HAL_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.tamu.scholars.middleware.auth.model.User;
import edu.tamu.scholars.middleware.theme.ThemeIntegrationTest;
import edu.tamu.scholars.middleware.theme.model.Style;
import edu.tamu.scholars.middleware.theme.model.Theme;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ThemeControllerTest extends ThemeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateTheme() throws JsonProcessingException, Exception {
        createMockAdmin();
        Theme theme = getMockTheme();
        // @formatter:off
		mockMvc.perform(post("/themes")
	        .content(objectMapper.writeValueAsString(theme)).cookie(loginAdmin()))
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

    @Test
    public void testUpdateTheme() throws JsonProcessingException, Exception {
        testCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        theme.setActive(true);
        theme.setOrganization("Testing Limited");
        theme.getHeader().getBanner().setAltText("Tested");
        // @formatter:off
		mockMvc.perform(put("/themes/" + theme.getId())
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

    @Test
    public void testPatchTheme() throws JsonProcessingException, Exception {
        testCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        // @formatter:off
		mockMvc.perform(patch("/themes/" + theme.getId())
	        .content("{\"active\": true, \"header\": { \"navbar\": { \"brandText\": \"Hello, Scholars!\"}}}")
			.cookie(loginAdmin()))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
    			.andExpect(jsonPath("active", equalTo(true)))
    			.andExpect(jsonPath("name", equalTo("Test")))
    			.andExpect(jsonPath("organization", equalTo("Testing Unlimited")))
    			.andExpect(jsonPath("header.navbar.brandText", equalTo("Hello, Scholars!")));
		// @formatter:on
    }

    @Test
    public void testPatchThemeColors() throws JsonProcessingException, Exception {
        testCreateTheme();
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
        testCreateTheme();
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
        testCreateTheme();
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
        testCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        // @formatter:off
		mockMvc.perform(get("/themes/" + theme.getId())
	        .cookie(loginAdmin()))
    		    .andExpect(status().isOk())
    			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE)).andExpect(jsonPath("active", equalTo(false)))
    			.andExpect(jsonPath("name", equalTo("Test")))
    			.andExpect(jsonPath("organization", equalTo("Testing Unlimited")));
		// @formatter:on
    }

    @Test
    public void testGetThemes() throws JsonProcessingException, Exception {
        testCreateTheme();
        // @formatter:off
		mockMvc.perform(get("/themes")
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
    			.andExpect(jsonPath("_embedded.themes[0].variables[2].value", equalTo("#ffffff")));
		// @formatter:on
    }

    @Test
    public void testGetActiveTheme() throws JsonProcessingException, Exception {
        testUpdateTheme();
        // @formatter:off
		mockMvc.perform(get("/themes/search/active"))
		    .andExpect(status().isOk())
			.andExpect(content().contentType(HAL_JSON_UTF8_VALUE))
			.andExpect(jsonPath("active", equalTo(true)))
			.andExpect(jsonPath("name", equalTo("Test")))
			.andExpect(jsonPath("organization", equalTo("Testing Limited")));
		// @formatter:on
    }

    @Test
    public void testDeleteTheme() throws JsonProcessingException, Exception {
        testCreateTheme();
        Theme theme = themeRepo.findByName("Test").get();
        mockMvc.perform(delete("/themes/" + theme.getId()).cookie(loginAdmin())).andExpect(status().isNoContent());
    }

    @Test
    public void testChangeActiveTheme() throws JsonProcessingException, Exception {
        testUpdateTheme();
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
        testUpdateTheme();
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

}

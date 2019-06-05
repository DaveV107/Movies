package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.DirectorEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  // PROBLEM MAY BE HERE
@TestPropertySource(properties = "local.management.port=0")
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DirectorsControllerTest {
    
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Autowired
    private WebApplicationContext context;
    
    @Value(value = "${movies.url.paths.base}")
    private String baseUrl;

    @LocalServerPort
    private int port;

    @Value(value = "${local.management.port}")
    private int mgt;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        // not required when using MockitoJUnitRunner or the MockitoJUnit.rule()
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void oneReturnsOkUsingMockMvc() throws Exception {
        mockMvc.perform(get("/directors/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status()
                        .isOk())
                .andExpect(content()
                        .contentType( MediaType.APPLICATION_JSON_UTF8 ))
                .andExpect(content()
                        .string(containsString("Favreau")));
    }

    @Test
    public void allReturnsOkUsingMockMvc() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/directors").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jon")))
                .andReturn();

        ObjectMapper objectMapper = jackson2ObjectMapperBuilder.build();
        JsonNode node = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        DirectorEntity[] directors = objectMapper.readValue(node.toString(), DirectorEntity[].class);
        assertTrue("number of directors incorrect", directors.length > 0);
    }
    
    @Test
    public void allReturnsOKStatus() throws IOException {
        ResponseEntity<String> directorEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/directors", String.class);
        ObjectMapper jsonObjectMapper = jackson2ObjectMapperBuilder.build();
        JsonNode node = jsonObjectMapper.readTree(directorEntityResponse.getBody());
        DirectorEntity[] directors = jsonObjectMapper.readValue(node.toString(), DirectorEntity[].class);
        // test the call
        assertEquals(HttpStatus.OK, directorEntityResponse.getStatusCode());
        // test the test data
        assertTrue(directors.length > 0);
    }
    
    @Test
    public void oneReturnsOKStatus() throws IOException {
        ResponseEntity<DirectorEntity> directorEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/directors/1", DirectorEntity.class);
        DirectorEntity director = directorEntityResponse.getBody();
        // test the call
        assertEquals(HttpStatus.OK, directorEntityResponse.getStatusCode());
        // test the test data
        assertTrue(director.getDirector()==1);
    }

    @Test
    public void addUpdateDeleteStatusTests() throws URISyntaxException {
        DirectorEntity director = this.add(new DirectorEntity(Long.valueOf(999), "Test", "new director added"));
        assertEquals("Test", director.getFirstName());
        director.setLastName("director updated");
        director = this.update(director);
        assertEquals("director updated", director.getLastName());
        this.delete(director.getDirector());
    }

    public DirectorEntity update(DirectorEntity director) {
        HttpEntity<DirectorEntity> directorHttpEntity = new HttpEntity<>(director);
        HttpEntity<DirectorEntity> directorResponse = this.testRestTemplate.exchange(
                baseUrl + ":" + this.port + "/api/v1/directors",
                HttpMethod.PUT,
                directorHttpEntity,
                DirectorEntity.class,
                new HashMap<>());
        DirectorEntity returnDirector = directorResponse.getBody();
        return returnDirector;
    }

    public DirectorEntity add(DirectorEntity director) {
        HttpEntity<DirectorEntity> directorHttpEntity = new HttpEntity<>(director);
        HttpEntity<DirectorEntity> directorResponse = this.testRestTemplate.exchange(
                baseUrl + ":" + this.port + "/api/v1/directors",
                HttpMethod.POST,
                directorHttpEntity,
                DirectorEntity.class,
                new HashMap<>());
        DirectorEntity returnDirector = directorResponse.getBody();

        assertEquals(HttpStatus.CREATED, ((ResponseEntity<DirectorEntity>) directorResponse).getStatusCode());

        ResponseEntity<DirectorEntity> directorEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/directors/1", DirectorEntity.class);

        assertEquals(HttpStatus.OK, directorEntityResponse.getStatusCode());

        return returnDirector;

    }

    public void delete(Long director) throws URISyntaxException {
        URI uri = new URI(baseUrl + ":" + this.port + "/api/v1/directors/" + director);
        this.testRestTemplate.delete(uri);
        ResponseEntity<DirectorEntity> directorEntityResponse = this.testRestTemplate.getForEntity(
                uri, DirectorEntity.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, directorEntityResponse.getStatusCode());
    }

}


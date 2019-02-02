package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.DirectorEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.URIException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "local.management.port=0")
public class DirectorsControllerTest {

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
        assertTrue(directors.length>0);
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


package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.RatingEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "local.management.port=0")
public class RatingsControllerTest {

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
        ResponseEntity<String> ratingsEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/ratings", String.class);
        ObjectMapper jsonObjectMapper = jackson2ObjectMapperBuilder.build();
        JsonNode node = jsonObjectMapper.readTree(ratingsEntityResponse.getBody());
        RatingEntity[] ratings = jsonObjectMapper.readValue(node.toString(), RatingEntity[].class);
        // test the call
        assertEquals(HttpStatus.OK, ratingsEntityResponse.getStatusCode());
        // test the test data
        assertTrue(ratings.length>0);
    }

    @Test
    public void oneReturnsOKStatus() throws IOException {
        ResponseEntity<RatingEntity> ratingsEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/ratings/1", RatingEntity.class);
        RatingEntity rating = ratingsEntityResponse.getBody();
        // test the call
        assertEquals(HttpStatus.OK, ratingsEntityResponse.getStatusCode());
        // test the test data
        assertTrue(rating.getRating()==1);
    }

}
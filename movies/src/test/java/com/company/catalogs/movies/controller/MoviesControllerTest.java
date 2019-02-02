package com.company.catalogs.movies.controller;

import com.company.catalogs.movies.entity.DirectorEntity;
import com.company.catalogs.movies.entity.MovieEntity;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "local.management.port=0")
public class MoviesControllerTest {

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
    public void findMoviesByDirector() throws IOException {
        ResponseEntity<String> moviesEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/movies/director/1", String.class);
        ObjectMapper jsonObjectMapper = jackson2ObjectMapperBuilder.build();
        JsonNode node = jsonObjectMapper.readTree(moviesEntityResponse.getBody());
        MovieEntity[] movies = jsonObjectMapper.readValue(node.toString(), MovieEntity[].class);
        assertEquals(HttpStatus.OK, moviesEntityResponse.getStatusCode());
        assertTrue(movies.length>0);
        assertEquals(Long.valueOf(1), movies[0].getMovie());
    }

    @Test
    public void findMoviesAboveRating() throws IOException {
        Long rating = Long.valueOf(3);
        ResponseEntity<String> moviesEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/movies/rating/" + rating, String.class);
        ObjectMapper jsonObjectMapper = jackson2ObjectMapperBuilder.build();
        JsonNode node = jsonObjectMapper.readTree(moviesEntityResponse.getBody());
        MovieEntity[] movies = jsonObjectMapper.readValue(node.toString(), MovieEntity[].class);
        assertEquals(HttpStatus.OK, moviesEntityResponse.getStatusCode());
        assertTrue(movies.length>0);
        assertTrue(movies[0].getRating().getRating() > rating);
    }

    @Test
    public void allReturnsOKStatus() throws IOException {
        ResponseEntity<String> moviesEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/movies", String.class);
        ObjectMapper jsonObjectMapper = jackson2ObjectMapperBuilder.build();
        JsonNode node = jsonObjectMapper.readTree(moviesEntityResponse.getBody());
        MovieEntity[] movies = jsonObjectMapper.readValue(node.toString(), MovieEntity[].class);
        // test the call
        assertEquals(HttpStatus.OK, moviesEntityResponse.getStatusCode());
        // test the test data
        assertTrue(movies.length>0);
    }

    @Test
    public void oneReturnsOKStatus() throws IOException {
        ResponseEntity<MovieEntity> moviesEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/movies/1", MovieEntity.class);
        MovieEntity movie = moviesEntityResponse.getBody();
        // test the call
        assertEquals(HttpStatus.OK, moviesEntityResponse.getStatusCode());
        // test the test data
        assertTrue(movie.getMovie()==1);
    }

    @Test
    public void addUpdateDeleteStatusTests() throws URISyntaxException {
        RatingEntity rating = getRating(Long.valueOf(1));
        DirectorEntity director = getDirector(Long.valueOf(1));
        MovieEntity movie = this.add(new MovieEntity(Long.valueOf(999), rating, director, "Test Film", "Test film"));

        assertEquals("Test Film", movie.getName());
        movie.setName("movie updated");
        movie = this.update(movie);
        assertEquals("movie updated", movie.getName());
        this.delete(movie.getMovie());
    }

    public MovieEntity update(MovieEntity movie) {
        HttpEntity<MovieEntity> movieHttpEntity = new HttpEntity<>(movie);
        HttpEntity<MovieEntity> movieResponse = this.testRestTemplate.exchange(
                baseUrl + ":" + this.port + "/api/v1/movies",
                HttpMethod.PUT,
                movieHttpEntity,
                MovieEntity.class,
                new HashMap<>());
        MovieEntity returnMovie = movieResponse.getBody();
        return returnMovie;
    }

    public MovieEntity add(MovieEntity movie) {
        HttpEntity<MovieEntity> movieHttpEntity = new HttpEntity<>(movie);
        HttpEntity<MovieEntity> movieResponse = this.testRestTemplate.exchange(
                baseUrl + ":" + this.port + "/api/v1/movies",
                HttpMethod.POST,
                movieHttpEntity,
                MovieEntity.class,
                new HashMap<>());
        MovieEntity returnMovie = movieResponse.getBody();
        assertEquals(HttpStatus.CREATED, ((ResponseEntity<MovieEntity>) movieResponse).getStatusCode());

        ResponseEntity<MovieEntity> movieEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/movies/1", MovieEntity.class);
        // test the call
        assertEquals(HttpStatus.OK, movieEntityResponse.getStatusCode());

        return returnMovie;

    }

    public void delete(Long movie) throws URISyntaxException {
        URI uri = new URI(baseUrl + ":" + this.port + "/api/v1/movies/" + movie);
        this.testRestTemplate.delete(uri);
        ResponseEntity<MovieEntity> movieEntityResponse = this.testRestTemplate.getForEntity(
                uri, MovieEntity.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, movieEntityResponse.getStatusCode());
    }


    public DirectorEntity getDirector(Long director) {
        ResponseEntity<DirectorEntity> directorEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/directors/" + director, DirectorEntity.class);
        return directorEntityResponse.getBody();
    }

    public RatingEntity getRating(Long rating){
        ResponseEntity<RatingEntity> ratingsEntityResponse = this.testRestTemplate.getForEntity(
                baseUrl + ":" + this.port + "/api/v1/ratings/" + rating, RatingEntity.class);
        return ratingsEntityResponse.getBody();
    }
}
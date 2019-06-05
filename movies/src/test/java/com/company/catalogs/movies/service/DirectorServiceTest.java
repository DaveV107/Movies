package com.company.catalogs.movies.service;

import com.company.catalogs.movies.entity.DirectorEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DirectorServiceTest {
	
	private List<DirectorEntity> directorsList;
	
//	@Mock
	@Autowired
	private DirectorService service; // system under test
	
	@Before
	public void setUp() throws Exception {
		generateDirectorsList();
	}
	
	@Test
	public void allReturnsMultipleDirectors() {
//		when(service.all()).thenReturn(directorsList);
		List<DirectorEntity> directors = service.all();
		assertNotNull(directors);
		assertFalse(directors.isEmpty());
	}
	
	@Test
	public void oneReturnsOneDirector() {
		DirectorEntity directorEntity = service.one(1l);
		assertNotNull(directorEntity);
		assertTrue(1l == directorEntity.getDirector());
	}
	
	@Test
	public void updateModifiesDirectorRecord() throws Exception {
		String firstName = "modified-name-001";
		DirectorEntity director = service.one(1l);
		director.setFirstName(firstName);
		ResponseEntity<?> updatedDirectorResponse = service.update(director);
		assertTrue(updatedDirectorResponse.getStatusCode() == HttpStatus.CREATED);
		assertTrue(updatedDirectorResponse.hasBody());
		DirectorEntity updatedDirector = (DirectorEntity)updatedDirectorResponse.getBody();
		assertNotNull(updatedDirector);
		assertTrue("First name equals: ", updatedDirector.getFirstName().equals(firstName));
	}
	
	@Test
	public void addIncreasesAllCountByOne() throws Exception {
		Integer directorCount = 0;
		List<DirectorEntity> directors = service.all();
		directorCount = directors.size();
		DirectorEntity newDirector = DirectorEntity.builder()
				.firstName("new-first-name")
				.lastName("new-last-name")
				.build();
		ResponseEntity<?> directorResponse = service.add(newDirector);
		assertNotNull(directorResponse);
		assertTrue("director response status is created",directorResponse.getStatusCode() == HttpStatus.CREATED);
		assertTrue("response has body", directorResponse.hasBody());
		DirectorEntity director = (DirectorEntity)directorResponse.getBody();
		assertTrue("director first name correct", director.getFirstName().equals("new-first-name"));
		directors = service.all();
		assertTrue("count increased by one", directors.size()==(directorCount+1));
	}
	
	@Test
	public void deleteDecreasesAllCountByOne() throws Exception {
		Integer directorCount = 0;
		DirectorEntity newDirector = DirectorEntity.builder()
				.firstName("delete-me")
				.lastName("delete-me")
				.build();
		ResponseEntity<?> newDirectorResponse = service.add(newDirector);
		newDirector = (DirectorEntity)newDirectorResponse.getBody();
		List<DirectorEntity> directors = service.all();
		directorCount = directors.size();
		ResponseEntity<?> response = service.delete(newDirector.getDirector());
		assertTrue("director deleted", response.getStatusCode() == HttpStatus.NO_CONTENT);
		directors = service.all();
		assertTrue("director count decreased by one", directors.size() == (directorCount-1));
	}
	
	@Test
	public void deleteUnknownDirectorReturnsStatusGone() {
		ResponseEntity<?> response = service.delete(10000000l);
		assertTrue("director deleted", response.getStatusCode() == HttpStatus.GONE);
	}

	private void generateDirectorsList(){
		directorsList = new ArrayList<>();
		directorsList.add(DirectorEntity.builder()
				.director(100l)
				.firstName("firstname-100")
				.lastName("lastname-100")
				.build());
		directorsList.add(DirectorEntity.builder()
				.director(101l)
				.firstName("firstname-101")
				.lastName("lastname-101")
				.build());
	}
}

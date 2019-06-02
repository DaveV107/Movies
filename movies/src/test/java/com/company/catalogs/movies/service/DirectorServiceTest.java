package com.company.catalogs.movies.service;

import com.company.catalogs.movies.entity.DirectorEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
	public void updateModifiesDirectorRecord() {
	}
	
	@Test
	public void addIncreasesAllCountByOne() {
	}
	
	@Test
	public void deleteDecreasesAllCountByOne() {
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

package com.airline;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.airline.model.Airport;
import com.airline.repository.AirportRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AirportRepositoryTest {

	@Autowired
	private AirportRepository airRepo;
	
	@Autowired 
	private TestEntityManager entityManager;
	
	@Test
	public void testAddAirport() {
		Airport airport = new Airport();
		airport.setAirportCode(53056L);
		airport.setAirportName("Istanbul Airport");
		airport.setCountry("Turkey");
		airport.setCity("Istanbul");
		airport.setAddress("Tayakadin, Terminal Cad No:1, 34283 Arnavutkoy/Istanbul");
		
		Airport addedAirport = airRepo.save(airport);
		
		Airport findAirport = entityManager.find(Airport.class, addedAirport.getId());
		
		assertThat(findAirport.getAirportCode()).isEqualTo(airport.getAirportCode());
	}
}

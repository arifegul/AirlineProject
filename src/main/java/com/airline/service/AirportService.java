package com.airline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.model.Airport;
import com.airline.repository.AirportRepository;

@Service
public class AirportService {

	
	@Autowired
	private AirportRepository airRepo;
	

	public List<Airport> getAllAirports() {
		return airRepo.findAll();
	}


	public Airport saveAirport(Airport newAirport) {
		return airRepo.save(newAirport);
	}


	public Airport getOneAirport(Long airportId) {
		return airRepo.findById(airportId).orElse(null);
	}


	public Airport updateOneAirport(Long airportId, Airport air) {
		Optional<Airport> airport = airRepo.findById(airportId);
		if(airport.isPresent()) {
			Airport updateAir = airport.get();
			updateAir.setAirportName(air.getAirportName());
			updateAir.setAddress(air.getAddress());
			airRepo.save(updateAir);
			return updateAir;
		}
		return null;
	}


	public void deleteOneAirport(Long airportId) {
		airRepo.deleteById(airportId);
		
	}
	
	

    
}

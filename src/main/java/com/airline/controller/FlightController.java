package com.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airline.model.Flight;
import com.airline.service.FlightService;

@RestController
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping
	public List<Flight> getAllFlights() {
		return flightService.getAllFlights();
	}
	
	@PostMapping
	public Flight createNewFlight(@RequestBody Flight flight) {
		return flightService.saveNewFlight(flight);
	}
	
	@GetMapping("/{flightId}")
	public Flight getOneFlight(@PathVariable Long flightId) {
		return flightService.getOneFlight(flightId);
	}
	
	@PutMapping("/{flightId}")
	public Flight updateOneFlight(@PathVariable Long flightId, @RequestBody Flight flight) {
		return flightService.updateOneFlight(flightId, flight);
	}
	
	@DeleteMapping ("/{flightId}")
	public void deleteOeFlight(@PathVariable Long flightId) {
		flightService.deleteById(flightId);
	}
	
}

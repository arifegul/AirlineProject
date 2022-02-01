package com.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.model.Airport;
import com.airline.service.AirportService;

@RestController
@RequestMapping("/airports")
public class AirportController {

	@Autowired
	private AirportService airService;
	
	@GetMapping
	public List<Airport> getAllAirports() {
		return airService.getAllAirports();
	}
	
	
	@PostMapping
	public Airport createAirport(@RequestBody Airport newAirport) {
		return airService.saveAirport(newAirport);
	}
	
	@GetMapping("/{airportId}")
	public Airport getOneAirport(@PathVariable Long airportId) {
		return airService.getOneAirport(airportId);
	}
	
	@PutMapping("/{airportId}")
	public Airport updateAirport(@PathVariable Long airportId, @RequestBody Airport air) {
		return airService.updateOneAirport(airportId,air);
	}
	
	@DeleteMapping("/{airportId}")
	public void deleteOneAirport(@PathVariable Long airportId) {
		airService.deleteOneAirport(airportId);
	}
}

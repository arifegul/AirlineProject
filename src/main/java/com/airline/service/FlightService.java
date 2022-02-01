package com.airline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.model.Flight;
import com.airline.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepo;
	
	public List<Flight> getAllFlights() {
		return flightRepo.findAll();
	}

	public Flight saveNewFlight(Flight flight) {
		return flightRepo.save(flight);
	}

	public Flight getOneFlight(Long flightId) {
		return flightRepo.findById(flightId).orElse(null);
	}

	public Flight updateOneFlight(Long flightId, Flight flight) {

		Optional<Flight> flightOptional = flightRepo.findById(flightId);
		if(flightOptional.isPresent()) {
			Flight foundFlight = flightOptional.get();
			foundFlight.setArrivalAirportName(flight.getArrivalAirportName());
			foundFlight.setArrivalCity(flight.getArrivalCity());
			foundFlight.setArrivalDate(flight.getArrivalDate());
			foundFlight.setArrivalTime(flight.getArrivalTime());
			foundFlight.setDeperatureAirportName(flight.getDeperatureAirportName());
			foundFlight.setDeperatureCity(flight.getDeperatureCity());
			foundFlight.setDeperatureDate(flight.getDeperatureDate());
			foundFlight.setDeperatureTime(flight.getDeperatureTime());
			foundFlight.setPrice(flight.getPrice());
			flightRepo.save(foundFlight);
			return foundFlight;
		} else {
			return null;
		}
	}

	public void deleteById(Long flightId) {
		flightRepo.deleteById(flightId);
		
	}
	
	

}

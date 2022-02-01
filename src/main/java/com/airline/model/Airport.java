package com.airline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "airports")
public class Airport {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private Long airportCode;
	private String airportName;
	private String country;
	private String city;
	private String address;
	
	public Airport() {
		
	}

	public Airport(Long id, Long airportCode, String airportName, String country, String city, String address) {
		this.id = id;
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.country = country;
		this.city = city;
		this.address = address;
	}

	public Airport(Long airportCode, String airportName, String country, String city, String address) {
		this.airportCode = airportCode;
		this.airportName = airportName;
		this.country = country;
		this.city = city;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(Long airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}

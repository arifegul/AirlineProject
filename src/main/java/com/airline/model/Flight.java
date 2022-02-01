package com.airline.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String arrivalAirportName;
	private String deperatureAirportName;
	private String arrivalCity;
	private String deperatureCity;
	private String arrivalDate;
	private String deperatureDate;
	private String arrivalTime;
	private String deperatureTime;
	private Integer price;
	
	
	public Flight() {
		
	}


	public Flight(String arrivalAirportName, String deperatureAirportName, String arrivalCity, String deperatureCity,
			String arrivalDate, String deperatureDate, String arrivalTime, String deperatureTime, Integer price) {
		this.arrivalAirportName = arrivalAirportName;
		this.deperatureAirportName = deperatureAirportName;
		this.arrivalCity = arrivalCity;
		this.deperatureCity = deperatureCity;
		this.arrivalDate = arrivalDate;
		this.deperatureDate = deperatureDate;
		this.arrivalTime = arrivalTime;
		this.deperatureTime = deperatureTime;
		this.price = price;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getArrivalAirportName() {
		return arrivalAirportName;
	}


	public void setArrivalAirportName(String arrivalAirportName) {
		this.arrivalAirportName = arrivalAirportName;
	}


	public String getDeperatureAirportName() {
		return deperatureAirportName;
	}


	public void setDeperatureAirportName(String deperatureAirportName) {
		this.deperatureAirportName = deperatureAirportName;
	}


	public String getArrivalCity() {
		return arrivalCity;
	}


	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}


	public String getDeperatureCity() {
		return deperatureCity;
	}


	public void setDeperatureCity(String deperatureCity) {
		this.deperatureCity = deperatureCity;
	}


	public String getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public String getDeperatureDate() {
		return deperatureDate;
	}


	public void setDeperatureDate(String deperatureDate) {
		this.deperatureDate = deperatureDate;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public String getDeperatureTime() {
		return deperatureTime;
	}


	public void setDeperatureTime(String deperatureTime) {
		this.deperatureTime = deperatureTime;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}
	
	 
}

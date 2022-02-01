package com.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long>{

}

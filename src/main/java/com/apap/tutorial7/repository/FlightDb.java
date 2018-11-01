package com.apap.tutorial7.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial7.model.FlightModel;

/**
 * 
 * @author redhita
 * FlightDb
 */
@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long>{
	FlightModel findFlightById(Long id);
	FlightModel findFlightByFlightNumber(String flightNumber);

}

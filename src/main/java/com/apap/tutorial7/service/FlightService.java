package com.apap.tutorial7.service;

import java.util.List;

import com.apap.tutorial7.model.FlightModel;

public interface FlightService {
	FlightModel addFlight(FlightModel flight);
	//method untuk delete flight
	FlightModel deleteFlight(FlightModel flight);
	//method untuk update flight
	void updateFlight (FlightModel flight, long id);
	FlightModel findFlightByFlightNumber (String flightNumber);
	FlightModel getFlight (long id);
	//method untuk mengambil semua flight
	List<FlightModel> allFlight();
}

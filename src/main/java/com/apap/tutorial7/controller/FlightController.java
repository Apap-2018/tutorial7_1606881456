package com.apap.tutorial7.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;

@RestController
@RequestMapping("/flight")
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@PostMapping(value="/add")
	public FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
		return flightService.addFlight(flight);
	}
	
	@PutMapping(value="/update/{flightId}")
	public String updateFlightSubmit(@PathVariable("flightId") long flightId,
			@RequestParam("destination") String destination,
			@RequestParam("origin") String origin,
			@RequestParam("time") Date time) {
		FlightModel flight = flightService.getFlight(flightId);
		if(flight.equals(null)) {
			return "Couldn't find your flight";
		}
		flight.setDestination(destination);
		flight.setOrigin(origin);
		flight.setTime(time);
		flightService.updateFlight(flight, flightId);
		return "Flight update success";
	}
	
	@GetMapping(value="/view/{flightNumber}")
	public FlightModel flightView(@PathVariable("flightNumber") String flightNumber) {
		FlightModel flight = flightService.findFlightByFlightNumber(flightNumber);
		return flight;
	}
	
	@GetMapping(value="/all")
	public List<FlightModel> viewAllFlight(Model model){
		List<FlightModel> listFlight = flightService.allFlight();
		return listFlight;
	}
	
	@DeleteMapping(value="/{flightId}")
	public String deletePilot(@PathVariable("flightId") long flightId) {
		FlightModel flight = flightService.getFlight(flightId);
		flightService.deleteFlight(flight);
		return "Flight has been deleted";
	}
	
	@Autowired
	RestTemplate restTemplateAirport;
	
	@Bean 
	public RestTemplate restAirport() {
		return new RestTemplate();
	}
	
	@GetMapping(value="/airport/{cityTerm}")
	public String getAirport(@PathVariable("cityTerm") String cityTerm) throws Exception{
		String path = Setting.airportUrl + cityTerm;
		return restTemplateAirport.getForEntity(path, String.class).getBody();
	}
	/*
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		ArrayList<FlightModel> flightList =  new ArrayList<FlightModel>();
		
		flightList.add(flight);
		pilot.setPilotFlight(flightList);
		flight.setPilot(pilot);
		
		model.addAttribute("pilot", pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method=RequestMethod.POST, params= {"save"})
	private String addFlightSubmit(@ModelAttribute PilotModel pilot, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
		for (FlightModel flight : pilot.getPilotFlight()) {
			flight.setPilot(archive);
			flightService.addFlight(flight);
		}
		return "add";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value ="/flight/add/{licenseNumber}", method=RequestMethod.POST, params= {"addRow"})
	private String addRow(@ModelAttribute PilotModel pilot, BindingResult bindingResult, Model model) {
		if(pilot.getPilotFlight() == null) {
			pilot.setPilotFlight(new ArrayList<FlightModel>());
		}
		
		pilot.getPilotFlight().add(new FlightModel());
		
		model.addAttribute("pilot", pilot);
		return "addFlight";
	}
	
	@RequestMapping(value="/flight/delete/", method=RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlight(flight.getId());
		}
		return "delete";
	}
	
	@RequestMapping(value="/flight/update/{id}", method=RequestMethod.GET)
	private String updateFlight(@PathVariable(value="id") Long id, Model model) {
		FlightModel flight = flightService.getFlight(id);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(flight.getPilot().getLicenseNumber());
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "updateFlight";
	}
	
	@RequestMapping(value="/flight/update", method=RequestMethod.POST)
	private String suksesUpdate(@ModelAttribute FlightModel flight) {
		flightService.updateFlight(flight, flight.getId());
		return "suksesUpdate";
	}
	
	@RequestMapping (value = "/flight/view")
	private String viewFlight (@RequestParam ("flightNumber") String flightNumber, Model model) {
		List <FlightModel> Flights = new ArrayList();
		List <FlightModel> allFlights = flightService.allFlight();
		
		for (FlightModel fli: allFlights) {
			if (fli.getFlightNumber().equals(flightNumber)) {
				Flights.add(fli);
			}
		}
		if (Flights.size() == 0){
			return "error";
		}
		model.addAttribute("flightNumber", flightNumber);
		model.addAttribute("flights", Flights);
		return "viewFlight";		
	}
	*/
}

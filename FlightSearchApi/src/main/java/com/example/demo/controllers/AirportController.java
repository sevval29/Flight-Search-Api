package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Airports;
import com.example.demo.services.AirportService;


@RestController
@RequestMapping("/airport")
public class AirportController {
	private AirportService airportService;

	public AirportController(AirportService airportService) {
		
		this.airportService = airportService;
	}

		
		@GetMapping
		public List<Airports> getAllAirports(){
			return airportService.getAllAirports();
		}
		@PostMapping	
		public Airports createAirport(@RequestBody  Airports newAirport) {
			return airportService.saveAirport(newAirport);
		}
		
		
		@GetMapping("/{airportId}")
	    public Airports getAirportById(@PathVariable Long airportId) {      
	       return airportService.getOneAirport(airportId);
	     
	    }
		
		
	    @PutMapping("/{airportId}")
		public Airports updateFlight(@PathVariable Long airportId, @RequestBody Airports newAirport) {
		    return airportService.updateOneAirport(airportId,newAirport);
		 }
		        
		@DeleteMapping("/{airportId}")
		public void deleteAirport(@PathVariable Long airportId) {
			airportService.deleteById(airportId);
		        
		    }}
package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Flights;
import com.example.demo.repositories.FlightRepository;
import com.example.demo.services.FlightService;


@RestController
@RequestMapping("/flights")
public class FlightController {
	private FlightService flightService;

public FlightController(FlightService flightService) {
	this.flightService=flightService;
}


@GetMapping
public List<Flights> getAllFlights(){
	return flightService.getAllFlights();
}


@PostMapping	
public Flights createFlight(@RequestBody  Flights newFlight) {
	return flightService.saveFlight(newFlight);
}


@GetMapping("/{id}")
public Flights getFlightById(@PathVariable Long id) {      
   return flightService.getOneFlight(id);
 
}


@PutMapping("/{id}")
public Flights updateFlight(@PathVariable Long id, @RequestBody Flights newFlight) {
    return flightService.updateOneFlight(id,newFlight);
 }
        
@DeleteMapping("/{id}")
public void deleteFlight(@PathVariable Long id) {
   flightService.deleteById(id);
        
    }

@GetMapping("/search")


public ResponseEntity<Object> searchFlights(
        @RequestParam("departureAirport") String departureAirport,
        @RequestParam("arrival") String arrivalAirport,
        @RequestParam("departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
        @RequestParam(value = "returnDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {

    List<Flights> oneWayFlights = flightService.searchOneWayFlights(departureAirport, arrivalAirport, departureDate);
    List<Flights> roundTripFlights = null;

   // departure = departure.toLowerCase();  //Harf büyüklüğü duyarlılığını kaldırmak için
   // arrival = arrival.toLowerCase();
    
    if (returnDate != null) {
        roundTripFlights = flightService.searchRoundTripFlights(arrivalAirport, departureAirport, returnDate);
    }

    if (oneWayFlights.isEmpty() && (roundTripFlights == null || roundTripFlights.isEmpty())) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No flights found.");
    }

    if (returnDate == null) {
        return ResponseEntity.ok(oneWayFlights);
    } else {
        if (roundTripFlights == null || roundTripFlights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No round-trip flights found.");
        }

        Map<String, List<Flights>> flightMap = new HashMap<>();
        flightMap.put("oneWayFlights", oneWayFlights);
        flightMap.put("roundTripFlights", roundTripFlights);

        return ResponseEntity.ok(flightMap);
    }
}


}


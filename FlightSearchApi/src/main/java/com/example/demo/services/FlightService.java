package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Flights;
import com.example.demo.repositories.FlightRepository;

@Service

public class FlightService {
	
	FlightRepository flightRepository ;

	public FlightService(FlightRepository flightRepository) {
		
		this.flightRepository = flightRepository;
	}

	
	public List<Flights> getAllFlights() {
	
		return flightRepository.findAll();
	}

	
	public Flights saveFlight(Flights newFlight) {
		
		return flightRepository.save(newFlight);
	}

	
	public Flights getOneFlight(Long id) {
		
		return flightRepository.findById(id).orElse(null);
	}

	
	public Flights updateOneFlight(Long id, Flights newFlight) {
		Optional<Flights> flight = flightRepository.findById(id);
		if(flight.isPresent()) {
		Flights foundFlight=flight.get();
		foundFlight.setArrivalAirport(newFlight.getArrivalAirport());
		foundFlight.setDepartureAirport(newFlight.getDepartureAirport());
		foundFlight.setArrivalDateTime(newFlight.getArrivalDateTime());
		foundFlight.setPrice(newFlight.getPrice());
		return foundFlight;
		}else
			return null;
	}

	
	public void deleteById(Long id) {
		
		flightRepository.deleteById(id);
	}
	
	public List<Flights> searchOneWayFlights(String departureAirport, String arrivalAirport, LocalDate departureDate) {
        // Kalkış yeri, varış yeri ve kalkış tarihine göre tek yönlü uçuşları veritabanından sorgula
        return flightRepository.findByDepartureAndDestinationAndDepartureDate(departureAirport, arrivalAirport, departureDate);
    }

    public List<Flights> searchRoundTripFlights(String departureAirport, String arrivalAirport, LocalDate returnDate) {
       //  Varış yeri, kalkış yeri ve dönüş tarihine göre çift yönlü (gidiş-dönüş) uçuşları veritabanından sorgula
        return flightRepository.findByDepartureAndDestinationAndDepartureDateAndReturnDate(arrivalAirport, departureAirport, returnDate);
    }
}


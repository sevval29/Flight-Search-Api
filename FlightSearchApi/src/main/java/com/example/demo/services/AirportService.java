package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Airports;
import com.example.demo.repositories.AirportRepository;



@Service
public class AirportService {
	
	AirportRepository airportRepository ;

	public AirportService(AirportRepository airportRepository) {
		
		this.airportRepository = airportRepository;
	}

	public List<Airports> getAllAirports() {
		
		return airportRepository.findAll();
	}

	
	public Airports saveAirport(Airports newAirport) {
		return airportRepository.save(newAirport);
	}
	

	public Airports getOneAirport(Long airportId) {
		
		return airportRepository.findById(airportId).orElse(null);
	}

	//********************************************* BUNA DEVAM ET*************
	public Airports updateOneAirport(Long airportId, Airports newAirport) {
	
		return null;
	}

	public void deleteById(Long airportId) {
		airportRepository.deleteById(airportId);
		
	}

}
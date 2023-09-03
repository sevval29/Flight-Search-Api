package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Flights;

public interface FlightRepository extends JpaRepository<Flights, Long> {
	
	
	

	
	@Query("SELECT f FROM Flights f WHERE f.departureAirport.city = :departureAirport AND f.arrivalAirport.city = :arrival AND DATE(f.departureDateTime) = :departureDate")

	public List<Flights> findByDepartureAndDestinationAndDepartureDate(@Param("departureAirport") String departureAirport, 
		    @Param("arrival") String arrivalAirport,
		    @Param("departureDate") LocalDate departureDate);

	
	
	@Query("SELECT f FROM Flights f WHERE f.departureAirport.city = :arrival AND f.arrivalAirport.city = :departureAirport AND DATE(f.departureDateTime) = :returnDate")

	public List<Flights> findByDepartureAndDestinationAndDepartureDateAndReturnDate(@Param("departureAirport") String arrivalAirport, @Param("arrival")  String departureAirport,
			 @Param("returnDate") LocalDate returnDate);

}

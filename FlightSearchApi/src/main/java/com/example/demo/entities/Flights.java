package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="flight")
public class Flights {
	@Id
	private Long id;

	private LocalDateTime departureDateTime;          //Kalkış tarih/saat
	private LocalDateTime arrivalDateTime;			 //Dönüş tarih/saat
	private double price;					 //fiyat

@ManyToOne
@JoinColumn(name = "departure_airport_id")
private Airports departureAirport;       //Kalkış havaalanı



@ManyToOne
@JoinColumn(name = "arrival_airport_id")
private Airports arrivalAirport;  		 //Varış havaalanı

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public Airports getDepartureAirport() {
    return departureAirport;
}

public void setDepartureAirport(Airports departureAirport) {
    this.departureAirport = departureAirport;
}


public Airports getArrivalAirport() {
    return arrivalAirport;
}

public void setArrivalAirport(Airports arrivalAirport) {
    this.arrivalAirport = arrivalAirport;
}




public LocalDateTime getDepartureDateTime() {
    return departureDateTime;
}

public void setDepartureDateTime(LocalDateTime departureDateTime) {
    this.departureDateTime = departureDateTime;
}

public LocalDateTime getArrivalDateTime() {
    return arrivalDateTime;
}

public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
    this.arrivalDateTime = arrivalDateTime;
}

public double getPrice() {
    return price;
}

public void setPrice(double price) {
    this.price = price;
}
}

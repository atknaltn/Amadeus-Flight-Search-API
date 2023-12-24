package com.amadeus.flightsearchapi.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightSearchRequestDTO {
    private String departureAirportCode;
    private String arrivalAirportCode;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;

    // Constructors, getters, and setters
    public FlightSearchRequestDTO(String departureAirportCode, String arrivalAirportCode,
                                  LocalDateTime departureDate, LocalDateTime returnDate) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }
}
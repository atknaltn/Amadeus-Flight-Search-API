package com.amadeus.flightsearchapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightResponseDTO {
    private Long id;
    private String departureAirportCode;
    private String arrivalAirportCode;
    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    private double price;

}
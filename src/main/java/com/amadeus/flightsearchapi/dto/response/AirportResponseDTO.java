package com.amadeus.flightsearchapi.dto.response;

import lombok.Data;

@Data
public class AirportResponseDTO {
    private Long id;
    private String city;
    private String airportCode;
}

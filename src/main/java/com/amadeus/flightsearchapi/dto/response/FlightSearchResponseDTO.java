package com.amadeus.flightsearchapi.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class FlightSearchResponseDTO {

    private List<FlightResponseDTO> flights;

    public void setFlights(List<FlightResponseDTO> flights) {
        this.flights = flights;
    }
}
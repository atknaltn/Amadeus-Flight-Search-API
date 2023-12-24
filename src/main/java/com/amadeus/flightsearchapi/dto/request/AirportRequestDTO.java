package com.amadeus.flightsearchapi.dto.request;

import lombok.Data;

import javax.validation.constraints.*;
@Data
public class AirportRequestDTO {
    private Long id;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Airport code cannot be empty")
    private String airportCode;
}

package com.amadeus.flightsearchapi.dto.request;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class FlightRequestDTO {
    private Long id;

    @NotBlank(message = "Departure Airport Code cannot be empty")
    private String departureAirportCode;

    @NotBlank(message = "Arrival Airport Code cannot be empty")
    private String arrivalAirportCode;

    @FutureOrPresent(message = "Departure date cannot be past")
    private LocalDateTime departureDateTime;

    private LocalDateTime returnDateTime;

    @Positive(message = "Price should be positive")
    private double price;

}

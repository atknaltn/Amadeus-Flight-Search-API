package com.amadeus.flightsearchapi.controller;

import com.amadeus.flightsearchapi.dto.request.FlightRequestDTO;
import com.amadeus.flightsearchapi.dto.request.FlightSearchRequestDTO;
import com.amadeus.flightsearchapi.dto.response.FlightResponseDTO;
import com.amadeus.flightsearchapi.dto.response.FlightSearchResponseDTO;
import com.amadeus.flightsearchapi.model.Flight;
import com.amadeus.flightsearchapi.repository.FlightRepository;
import com.amadeus.flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    public ResponseEntity<Iterable<FlightResponseDTO>> getAllFlights() {
        Iterable<FlightResponseDTO> flights = flightService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightResponseDTO> getFlightById(@PathVariable Long flightId) {
        Optional<FlightResponseDTO> flight = flightService.getFlightById(flightId);
        return flight.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/flights")
    public ResponseEntity<FlightResponseDTO> addFlight(@Valid @RequestBody FlightRequestDTO flightDTO) {
        FlightResponseDTO newFlight = flightService.addFlight(flightDTO);
        return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    public ResponseEntity<FlightSearchResponseDTO> searchFlights(
            @RequestBody FlightSearchRequestDTO requestDTO) {

        FlightSearchResponseDTO responseDTO = flightService.searchFlights(requestDTO);

        if (responseDTO.getFlights().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

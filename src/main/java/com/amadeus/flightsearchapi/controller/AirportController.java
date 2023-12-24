package com.amadeus.flightsearchapi.controller;

import com.amadeus.flightsearchapi.model.Airport;
import com.amadeus.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    // Tüm havaalanlarını getir
    @GetMapping("/airports")
    public ResponseEntity<Iterable<Airport>> getAllAirports() {
        Iterable<Airport> airports = airportRepository.findAll();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @GetMapping("/airports/{airportId}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long airportId) {
        return airportRepository.findById(airportId)
                .map(airport -> new ResponseEntity<>(airport, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/airports")
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        Airport newAirport = airportRepository.save(airport);
        return new ResponseEntity<>(newAirport, HttpStatus.CREATED);
    }

    @DeleteMapping("/airports/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long airportId) {
        airportRepository.deleteById(airportId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

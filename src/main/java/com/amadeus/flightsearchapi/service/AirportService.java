package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.model.Airport;
import com.amadeus.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    AirportRepository airportRepository;

    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Long airportId) {
        return airportRepository.findById(airportId);
    }

    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public void deleteAirport(Long airportId) {
        airportRepository.deleteById(airportId);
    }
}

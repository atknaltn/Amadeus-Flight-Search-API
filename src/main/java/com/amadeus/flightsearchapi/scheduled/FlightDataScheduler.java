package com.amadeus.flightsearchapi.scheduled;

import com.amadeus.flightsearchapi.model.Flight;
import com.amadeus.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Component
public class FlightDataScheduler {

    @Autowired
    private FlightRepository flightRepository;

    @Scheduled(cron = "0 0 0 * * *") // Scheduled to run every day at midnight
    public void fetchFlightDataAndSaveToDatabase() {
        String mockApiResponse = mockFlightDataApiCall();

        if (mockApiResponse != null) {
            List<Flight> flights = parseMockApiResponse(mockApiResponse);
            flightRepository.saveAll(flights);
        }
    }

    private String mockFlightDataApiCall() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/mockFlightData.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private List<Flight> parseMockApiResponse(String apiResponse) {
        List<Flight> flights = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Flight[] flightArray = objectMapper.readValue(apiResponse, Flight[].class);
            Collections.addAll(flights, flightArray);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return flights;
    }
}


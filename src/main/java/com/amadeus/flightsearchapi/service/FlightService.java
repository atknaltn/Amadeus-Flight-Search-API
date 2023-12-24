package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.dto.request.FlightRequestDTO;
import com.amadeus.flightsearchapi.dto.request.FlightSearchRequestDTO;
import com.amadeus.flightsearchapi.dto.response.FlightResponseDTO;
import com.amadeus.flightsearchapi.dto.response.FlightSearchResponseDTO;
import com.amadeus.flightsearchapi.model.Flight;
import com.amadeus.flightsearchapi.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightResponseDTO> getAllFlights() {
        Iterable<Flight> flights = flightRepository.findAll();
        List<FlightResponseDTO> flightResponseDTOs = new ArrayList<>();

        for (Flight flight : flights) {
            flightResponseDTOs.add(convertToFlightResponseDTO(flight));
        }

        return flightResponseDTOs;
    }

    public Optional<FlightResponseDTO> getFlightById(Long flightId) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);
        return optionalFlight.map(this::convertToFlightResponseDTO);
    }

    public FlightResponseDTO addFlight(@Valid FlightRequestDTO flightDTO) {
        Flight newFlight = convertToEntity(flightDTO);
        Flight savedFlight = flightRepository.save(newFlight);
        return convertToFlightResponseDTO(savedFlight);
    }

    public void deleteFlight(Long flightId) {
        flightRepository.deleteById(flightId);
    }

    public FlightSearchResponseDTO searchFlights(FlightSearchRequestDTO requestDTO) {
        FlightSearchResponseDTO responseDTO = new FlightSearchResponseDTO();
        List<FlightResponseDTO> flights = new ArrayList<>();

        if (requestDTO.getReturnDate() == null) {
            // One-way flight search
            List<Flight> oneWayFlights = flightRepository.findFlightsOneWay(
                    requestDTO.getDepartureAirportCode(), requestDTO.getArrivalAirportCode(), requestDTO.getDepartureDate());
            flights.addAll(convertToFlightResponseDTOList(oneWayFlights));
        } else {
            // Round-trip flight search
            List<Flight> roundTripFlights = flightRepository.findFlightsRoundTrip(
                    requestDTO.getDepartureAirportCode(), requestDTO.getArrivalAirportCode(),
                    requestDTO.getDepartureDate(), requestDTO.getReturnDate());
            flights.addAll(convertToFlightResponseDTOList(roundTripFlights));
        }

        responseDTO.setFlights(flights);
        return responseDTO;
    }

    private List<FlightResponseDTO> convertToFlightResponseDTOList(List<Flight> flights) {
        List<FlightResponseDTO> flightResponseDTOs = new ArrayList<>();
        for (Flight flight : flights) {
            FlightResponseDTO responseDTO = new FlightResponseDTO();
            responseDTO.setId(flight.getId());
            responseDTO.setDepartureAirportCode(flight.getDepartureAirportCode());
            responseDTO.setArrivalAirportCode(flight.getArrivalAirportCode());
            responseDTO.setDepartureDateTime(flight.getDepartureDateTime());
            responseDTO.setReturnDateTime(flight.getReturnDateTime());
            responseDTO.setPrice(flight.getPrice());
            flightResponseDTOs.add(responseDTO);
        }
        return flightResponseDTOs;
    }

    private FlightResponseDTO convertToFlightResponseDTO(Flight flight) {
        FlightResponseDTO flightResponseDTO = new FlightResponseDTO();
        flightResponseDTO.setId(flight.getId());
        flightResponseDTO.setDepartureAirportCode(flight.getDepartureAirportCode());
        flightResponseDTO.setArrivalAirportCode(flight.getArrivalAirportCode());
        flightResponseDTO.setDepartureDateTime(flight.getDepartureDateTime());
        flightResponseDTO.setReturnDateTime(flight.getReturnDateTime());
        flightResponseDTO.setPrice(flight.getPrice());
        return flightResponseDTO;
    }

    private Flight convertToEntity(FlightRequestDTO flightDTO) {
        Flight flight = new Flight();
        flight.setDepartureAirportCode(flightDTO.getDepartureAirportCode());
        flight.setArrivalAirportCode(flightDTO.getArrivalAirportCode());
        flight.setDepartureDateTime(flightDTO.getDepartureDateTime());
        flight.setReturnDateTime(flightDTO.getReturnDateTime());
        flight.setPrice(flightDTO.getPrice());
        return flight;
    }
}


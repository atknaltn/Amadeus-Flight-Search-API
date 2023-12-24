package com.amadeus.flightsearchapi.repository;

import com.amadeus.flightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flights WHERE departure_airport_code = :departureAirportCode " +
            "AND arrival_airport_code = :arrivalAirportCode " +
            "AND departure_date_time >= :departureDateTime", nativeQuery = true)
    List<Flight> findFlightsOneWay(String departureAirportCode, String arrivalAirportCode, LocalDateTime departureDateTime);

    @Query(value = "SELECT * FROM flights WHERE departure_airport_code = :departureAirportCode " +
            "AND arrival_airport_code = :arrivalAirportCode " +
            "AND departure_date_time >= :departureDateTime " +
            "AND return_date_time <= :returnDateTime", nativeQuery = true)
    List<Flight> findFlightsRoundTrip(String departureAirportCode, String arrivalAirportCode, LocalDateTime departureDateTime, LocalDateTime returnDateTime);
}



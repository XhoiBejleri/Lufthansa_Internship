package org.example.repository;

import org.example.model.entity.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends Repository<Flight, Long> {

    Optional<Flight> findByFlightNumber(String flightNumber);

    List<Flight> findAll();
}

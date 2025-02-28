package org.example.service;

import org.example.mapper.FlightMapper;
import org.example.model.entity.Flight;
import org.example.model.resource.FlightResource;
import org.example.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public List<FlightResource> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    public FlightResource findFlightById(Long id) {
        Optional<Flight> flightOpt = flightRepository.findById(id);
        return flightOpt.map(flightMapper::toDto).orElse(null);
    }

    public List<FlightResource> findFlightsByDepartureAndAirport(LocalDate departureDate, String airport) {
        List<Flight> flights = flightRepository.findByDepartureDateAndOrigin(departureDate, airport);
        return flights.stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    public FlightResource saveFlight(FlightResource flightResource) {
        Flight flight = flightMapper.toEntity(flightResource);
        flight = flightRepository.save(flight);
        return flightMapper.toDto(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}

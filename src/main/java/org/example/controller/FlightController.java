package org.example.controller;

import org.example.model.resource.FlightResource;
import org.example.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightResource>> getAllFlights() {
        List<FlightResource> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    // Get information for a specific flight by ID
    @GetMapping("/{id}")
    public ResponseEntity<FlightResource> getFlightById(@PathVariable Long id) {
        FlightResource flight = flightService.findFlightById(id);
        return flight != null ? ResponseEntity.ok(flight) : ResponseEntity.notFound().build();
    }

    @GetMapping("/departure")
    public ResponseEntity<List<FlightResource>> getFlightsByDepartureAndAirport(
            @RequestParam("date") String dateString,
            @RequestParam("airport") String airport) {
        LocalDate departureDate = LocalDate.parse(dateString);
        List<FlightResource> flights = flightService.findFlightsByDepartureAndAirport(departureDate, airport);
        return ResponseEntity.ok(flights);
    }

    @PostMapping
    public ResponseEntity<FlightResource> createOrUpdateFlight(@RequestBody FlightResource flightResource) {
        FlightResource savedFlight = flightService.saveFlight(flightResource);
        return ResponseEntity.ok(savedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
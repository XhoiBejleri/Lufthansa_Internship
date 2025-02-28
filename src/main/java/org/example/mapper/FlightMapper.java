package org.example.mapper;

import org.example.model.entity.Flight;
import org.example.model.resource.FlightResource;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightResource toDto(Flight flight) {
        if (flight == null) {
            return null;
        }
        FlightResource resource = new FlightResource();
        resource.setId(flight.getId());
        resource.setOrigin(flight.getOrigin());
        resource.setDestination(flight.getDestination());
        resource.setAirline(flight.getAirline());
        resource.setFlightNumber(flight.getFlightNumber());
        resource.setDepartureDate(flight.getDepartureDate());
        resource.setArrivalDate(flight.getArrivalDate());
        resource.setFlightStatus(flight.getFlightStatus());
        return resource;
    }

    public Flight toEntity(FlightResource resource) {
        if (resource == null) {
            return null;
        }
        Flight flight = new Flight();
        flight.setId(resource.getId());
        flight.setOrigin(resource.getOrigin());
        flight.setDestination(resource.getDestination());
        flight.setAirline(resource.getAirline());
        flight.setFlightNumber(resource.getFlightNumber());
        flight.setDepartureDate(resource.getDepartureDate());
        flight.setArrivalDate(resource.getArrivalDate());
        flight.setFlightStatus(resource.getFlightStatus());
        return flight;
    }
}

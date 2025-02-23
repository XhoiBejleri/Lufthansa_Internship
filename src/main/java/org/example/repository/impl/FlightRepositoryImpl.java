package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.configuration.EntityManagerConfiguration;
import org.example.model.entity.Flight;
import org.example.repository.FlightRepository;

import java.util.List;
import java.util.Optional;

public class FlightRepositoryImpl implements FlightRepository {

    private final EntityManager entityManager;

    public FlightRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Flight.class, id));
    }

    @Override
    public List<Flight> findAll() {
        TypedQuery<Flight> query = entityManager.createQuery("SELECT f FROM Flight f", Flight.class);
        return query.getResultList();
    }

    @Override
    public void save(Flight flight) {
        entityManager.getTransaction().begin();
        if (flight.getId() != null) {
            Flight existingFlight = entityManager.find(Flight.class, flight.getId());
            if (existingFlight != null) {
                existingFlight.setOrigin(flight.getOrigin());
                existingFlight.setDestination(flight.getDestination());
                existingFlight.setAirline(flight.getAirline());
                existingFlight.setFlightNumber(flight.getFlightNumber());
                existingFlight.setDepartureDate(flight.getDepartureDate());
                existingFlight.setArrivalDate(flight.getArrivalDate());
                existingFlight.setFlightStatus(flight.getFlightStatus());
                entityManager.merge(existingFlight);
            }
        } else {
            entityManager.persist(flight);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Flight flight) {
        entityManager.getTransaction().begin();
        Flight managedFlight = flight;
        if (!entityManager.contains(flight)) {
            managedFlight = entityManager.merge(flight);
        }
        entityManager.remove(managedFlight);
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Flight> findByFlightNumber(String flightNumber) {
        TypedQuery<Flight> query = entityManager.createQuery(
                "SELECT f FROM Flight f WHERE f.flightNumber = :flightNumber", Flight.class);
        query.setParameter("flightNumber", flightNumber);
        Flight flight = null;
        try {
            flight = query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(flight);
    }
}

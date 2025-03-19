package com.example.flightplanner.service;

import com.example.flightplanner.model.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Teenus, mis haldab lennu andmeid.
 */
@Service
public class FlightService {
    private final List<Flight> flights = new ArrayList<>();

    // Lisame mõned näidislennud
    public FlightService() {
        flights.add(new Flight(1L, "London", LocalDate.now().plusDays(1), LocalTime.of(10, 30), 120.0));
        flights.add(new Flight(2L, "Paris", LocalDate.now().plusDays(2), LocalTime.of(12, 45), 150.0));
        flights.add(new Flight(3L, "New York", LocalDate.now().plusDays(3), LocalTime.of(14, 0), 450.0));
    }

    /**
     * Tagastab kõik lennud.
     */
    public List<Flight> getAllFlights() {
        return flights;
    }

    /**
     * Tagastab lennu vastavalt ID-le.
     */
    public Flight getFlightById(Long id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Tagastab lennud vastavalt antud filtritele.
     * Kui mõni filter on null või tühi, siis seda filtrimist ignoreeritakse.
     */
    public List<Flight> getFilteredFlights(String destination, LocalDate date, LocalTime time, Double maxPrice) {
        return flights.stream()
                .filter(flight -> destination == null || destination.isEmpty() ||
                        flight.getDestination().equalsIgnoreCase(destination))
                .filter(flight -> date == null || flight.getDate().equals(date))
                .filter(flight -> time == null || flight.getTime().equals(time))
                .filter(flight -> maxPrice == null || flight.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}

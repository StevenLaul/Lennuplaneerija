package com.example.flightplanner.service;

import com.example.flightplanner.model.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    private final List<Flight> flights = new ArrayList<>();

    // Mõned näidislennud
    public FlightService() {
        flights.add(new Flight(1L, "London", LocalDate.now().plusDays(1), LocalTime.of(10, 30), 120.0));
        flights.add(new Flight(2L, "Paris", LocalDate.now().plusDays(2), LocalTime.of(12, 45), 150.0));
        flights.add(new Flight(3L, "New York", LocalDate.now().plusDays(3), LocalTime.of(14, 0), 450.0));
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public Flight getFlightById(Long id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }
}

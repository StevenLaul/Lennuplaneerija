package com.example.flightplanner.service;

import com.example.flightplanner.model.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Haldab lennu andmeid

@Service
public class FlightService {
    private final List<Flight> flights = new ArrayList<>();

    // Näidislennud(tegin ise, sest ei ole väga kindel API kasutamis oskuses veel
    public FlightService() {
        flights.add(new Flight(1L, "London", LocalDate.now().plusDays(1), LocalTime.of(10, 30), 150.0));
        flights.add(new Flight(2L, "Pariis", LocalDate.now().plusDays(2), LocalTime.of(12, 45), 150.0));
        flights.add(new Flight(3L, "New York", LocalDate.now().plusDays(3), LocalTime.of(14, 0), 450.0));
        flights.add(new Flight(4L, "Helsinki", LocalDate.now().plusDays(4), LocalTime.of(14, 0), 120.0));
        flights.add(new Flight(5L, "Oslo", LocalDate.now().plusDays(5), LocalTime.of(11, 0), 130.0));
        flights.add(new Flight(6L, "Tartu", LocalDate.now().plusDays(4), LocalTime.of(10, 5), 50));
        flights.add(new Flight(7L, "New York", LocalDate.now().plusDays(4), LocalTime.of(14, 5), 450));
        flights.add(new Flight(8L, "Rooma", LocalDate.now().plusDays(3), LocalTime.of(13, 0), 250));
        flights.add(new Flight(9L, "Oslo", LocalDate.now().plusDays(2), LocalTime.of(17, 10), 130));
        flights.add(new Flight(10L, "Moskva", LocalDate.now().plusDays(1), LocalTime.of(16, 5), 110));
        flights.add(new Flight(11L, "Tartu", LocalDate.now().plusDays(6), LocalTime.of(16, 30), 50));
    }

    //Kuvatakse kõik lennud
    public List<Flight> getAllFlights() {
        return flights;
    }

   //Kuvatakse lennud vastavalt ID-le
    public Flight getFlightById(Long id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

   //Tagastab lennud vastavalt antud filtritele. Kui mõni filter on null ehk tühi, siis seda filtrit ignoreeritakse
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

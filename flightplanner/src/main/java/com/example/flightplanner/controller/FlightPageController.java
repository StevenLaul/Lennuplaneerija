package com.example.flightplanner.controller;

import com.example.flightplanner.model.Flight;
import com.example.flightplanner.model.Seat;
import com.example.flightplanner.service.FlightService;
import com.example.flightplanner.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Kontroller, mis haldab veebipäringuid ning tagastab Thymeleaf mallid.
 */
@Controller
public class FlightPageController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private SeatService seatService;

    /**
     * Kuvab lennuplaani. Filtreerimise parameetrid on valikulised.
     * URL: GET /flights?destination=...&date=...&time=...&maxPrice=...
     */
    @GetMapping("/flights")
    public String flightsPage(@RequestParam(required = false) String destination,
                              @RequestParam(required = false) String date,
                              @RequestParam(required = false) String time,
                              @RequestParam(required = false) Double maxPrice,
                              Model model) {
        List<Flight> flights;
        LocalDate parsedDate = null;
        LocalTime parsedTime = null;
        try {
            if (date != null && !date.isEmpty()) {
                parsedDate = LocalDate.parse(date);
            }
            if (time != null && !time.isEmpty()) {
                parsedTime = LocalTime.parse(time);
            }
        } catch (Exception e) {
            // Kui parsimine ebaõnnestub, jäta filter kasutamata või lisa error-sõnum
        }
        if ((destination == null || destination.isEmpty()) && parsedDate == null && parsedTime == null && maxPrice == null) {
            flights = flightService.getAllFlights();
        } else {
            flights = flightService.getFilteredFlights(destination, parsedDate, parsedTime, maxPrice);
        }
        model.addAttribute("flights", flights);
        return "flights";
    }

    /**
     * Kuvab üksiku lennu detailid koos istmekoha plaaniga.
     * URL: GET /flights/{id}
     */
    @GetMapping("/flights/{id}")
    public String flightDetail(@PathVariable Long id, Model model) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return "redirect:/flights";
        }
        List<Seat> seatMap = seatService.generateSeatMap();
        model.addAttribute("flight", flight);
        model.addAttribute("seatMap", seatMap);
        return "flight_detail";
    }

    /**
     * Töötleb vormi, mis soovitab istekohti kasutaja eelistustega.
     * URL: POST /flights/{id}/recommend
     */
    @PostMapping("/flights/{id}/recommend")
    public String recommendSeats(@PathVariable Long id,
                                 @RequestParam int count,
                                 @RequestParam(defaultValue = "false") boolean window,
                                 @RequestParam(defaultValue = "false") boolean extraLegroom,
                                 @RequestParam(defaultValue = "false") boolean nearExit,
                                 Model model) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return "redirect:/flights";
        }
        List<Seat> seatMap = seatService.generateSeatMap();
        List<Seat> recommendedSeats = seatService.recommendSeats(seatMap, count, window, extraLegroom, nearExit);
        model.addAttribute("flight", flight);
        model.addAttribute("seatMap", seatMap);
        model.addAttribute("recommendedSeats", recommendedSeats);
        return "flight_detail";
    }
}
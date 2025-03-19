package com.example.flightplanner.controller;

import com.example.flightplanner.model.Flight;
import com.example.flightplanner.model.Seat;
import com.example.flightplanner.service.FlightService;
import com.example.flightplanner.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * Kuvab lennuplaani.
     * URL: GET /flights
     */
    @GetMapping("/flights")
    public String flightsPage(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights";  // otsib faili flights.html
    }

    /**
     * Kuvab üksiku lennu detailid koos istmekoha plaaniga.
     * URL: GET /flights/{id}
     */
    @GetMapping("/flights/{id}")
    public String flightDetail(@PathVariable Long id, Model model) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return "redirect:/flights"; // kui lendu ei leita, suuna tagasi
        }
        List<Seat> seatMap = seatService.generateSeatMap();
        model.addAttribute("flight", flight);
        model.addAttribute("seatMap", seatMap);
        return "flight_detail";  // otsib faili flight_detail.html
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
        // Deklareerime "flight" muutuja
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

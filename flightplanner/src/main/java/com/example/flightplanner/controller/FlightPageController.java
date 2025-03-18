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

// Kontroller, mis tegeleb päringutega ning tagastab thymeleafiga seotud päringuid

public class FlightPageController {
    private FlightService flightService;
    //Tagastab leenuandmed ja istekohtade info

    private SeatService seatService;

    public String flightsPage(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights";
    }


    public String flightDetail(Long id, Model model) {
        Flight flight = flightService.getFlightById(id);
        if (flight == null) {
            return "redirect:/flights"; //Suuname kasutaja tagasi kui lendu ei leita
        }
        List<Seat> seatMap = seatService.generateSeatMap();
        model.addAttribute("flight", flight);
        model.addAttribute("seatMap", seatMap);
        return "flight_detail";
    }

    public String recommendSeats(Long id, int count, boolean window, boolean extraLegroom, boolean nearExit, Model model) {
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
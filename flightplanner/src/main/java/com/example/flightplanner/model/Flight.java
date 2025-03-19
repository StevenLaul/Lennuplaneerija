package com.example.flightplanner.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
    private Long id;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private double price;

    // Konstruktorid, getterid ja setterid
    public Flight(Long id, String destination, LocalDate date, LocalTime time, double price) {
        this.id = id;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public Flight() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

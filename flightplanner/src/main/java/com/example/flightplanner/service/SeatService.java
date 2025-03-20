package com.example.flightplanner.service;

import com.example.flightplanner.model.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SeatService {
    private final int rows = 10;
    private final char[] seatsPerRow = {'A', 'B', 'C', 'D', 'E', 'F'};
    private final Random random = new Random();

    public List<Seat> generateSeatMap() {
        List<Seat> seatMap = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            String seatClass;
            if (i <= 2) {
                seatClass = "1. klass";
            } else if (i <= 4) {
                seatClass = "Äriklass";
            } else {
                seatClass = "Turistiklass";
            }
            for (char seatChar : seatsPerRow) {
                String seatId = i + String.valueOf(seatChar);
                boolean isWindow = (seatChar == 'A' || seatChar == 'F');
                boolean extraLegroom = (i <= 2);
                boolean nearExit = (i == rows);
                Seat seat = new Seat(seatId, isWindow, extraLegroom, nearExit, seatClass);
                // Märgime juhuslikult mõningad istmed hõivatuks (30% tõenäosusega)
                seat.setTaken(random.nextDouble() < 0.3);
                seatMap.add(seat);
            }
        }
        return seatMap;
    }

    public List<Seat> recommendSeats(List<Seat> seatMap, int count, boolean requireWindow, boolean requireExtraLegroom, boolean requireNearExit) {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seatMap) {
            if (seat.isTaken()) continue;
            boolean matches = true;
            if (requireWindow) matches &= seat.isWindow();
            if (requireExtraLegroom) matches &= seat.isExtraLegroom();
            if (requireNearExit) matches &= seat.isNearExit();
            if (matches) {
                availableSeats.add(seat);
            }
        }
        // Tagastab sobiva istme(d), kui on saadaval
        return availableSeats.size() >= count ? availableSeats.subList(0, count) : availableSeats;
    }
}

package com.example.flightplanner.service;

import com.example.flightplanner.model.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class SeatService {
    private final int rows = 10;
    private final char[] seatsPerRow = {'A', 'B', 'C', 'D', 'E', 'F'};
    private final Random random = new Random();

    public List<Seat> generateSeatMap() {
        List<Seat> seatMap = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            // Määrame istmeklassi vastavalt reale:
            String seatClass;
            if (i <= 2) {
                seatClass = "1. klass";
            } else if (i <= 4) {
                seatClass = "äriklass";
            } else {
                seatClass = "turistiklass";
            }
            for (char seatChar : seatsPerRow) {
                String seatId = i + String.valueOf(seatChar);
                boolean isWindow = (seatChar == 'A' || seatChar == 'F');
                boolean extraLegroom = (i <= 2);
                boolean nearExit = (i == rows);
                // Lisame seatClass väärtuse
                Seat seat = new Seat(seatId, isWindow, extraLegroom, nearExit, seatClass);
                // Märgime juhuslikult mõningad istmed hõivatuks (30% tõenäosus)
                seat.setTaken(random.nextDouble() < 0.3);
                seatMap.add(seat);
            }
        }
        return seatMap;
    }

    //Soovitab istekohti vastavalt filtris kasutatud soovidele
    public List<Seat> recommendSeats(List<Seat> seatMap, int count, boolean requireWindow,
                                     boolean requireExtraLegroom, boolean requireNearExit, boolean requireAdjacent) {
        if (!requireAdjacent) {
            // Saab filtreerida vastavalt vajadusele
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
            return availableSeats.size() >= count ? availableSeats.subList(0, count) : availableSeats;
        } else {
            // Otsib kõrvuti istmeid samalt realt
            Map<Integer, List<Seat>> seatsByRow = new HashMap<>();
            for (Seat seat : seatMap) {
                if (seat.isTaken()) continue;
                boolean matches = true;
                if (requireWindow) matches &= seat.isWindow();
                if (requireExtraLegroom) matches &= seat.isExtraLegroom();
                if (requireNearExit) matches &= seat.isNearExit();
                if (matches) {
                    // Eeldab, et istmeID on kujul "1A": esimene osa on rea number
                    String rowStr = seat.getSeatId().replaceAll("[^0-9]", "");
                    int row = Integer.parseInt(rowStr);
                    seatsByRow.putIfAbsent(row, new ArrayList<>());
                    seatsByRow.get(row).add(seat);
                }
            }
            // Proovib leida kõrvuti istmeid count meetodiga
            for (Map.Entry<Integer, List<Seat>> entry : seatsByRow.entrySet()) {
                List<Seat> rowSeats = entry.getValue();
                // Sorteeri seatid vastavalt nende tähemärgile (eeldades, et seatId lõpus on täht)
                rowSeats.sort(Comparator.comparing(seat -> seat.getSeatId().substring(seat.getSeatId().length()-1)));
                for (int i = 0; i <= rowSeats.size() - count; i++) {
                    boolean contiguous = true;
                    for (int j = 0; j < count - 1; j++) {
                        char current = rowSeats.get(i + j).getSeatId().charAt(rowSeats.get(i + j).getSeatId().length() - 1);
                        char next = rowSeats.get(i + j + 1).getSeatId().charAt(rowSeats.get(i + j + 1).getSeatId().length() - 1);
                        if (next - current != 1) {
                            contiguous = false;
                            break;
                        }
                    }
                    if (contiguous) {
                        return rowSeats.subList(i, i + count);
                    }
                }
            }
            return new ArrayList<>(); // kui ei leita kõrvuti istmeid
        }
    }
}

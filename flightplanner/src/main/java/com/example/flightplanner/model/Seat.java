package com.example.flightplanner.model;

public class Seat {
    private String seatId;
    private boolean window;
    private boolean extraLegroom;
    private boolean nearExit;
    private boolean taken;
    private String seatClass;

    public Seat(String seatId, boolean window, boolean extraLegroom, boolean nearExit, String seatClass) {
        this.seatId = seatId;
        this.window = window;
        this.extraLegroom = extraLegroom;
        this.nearExit = nearExit;
        this.taken = false;
        this.seatClass = seatClass;
    }

    public Seat() {}

    public String getSeatId() {
        return seatId;
    }
    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }
    public boolean isWindow() {
        return window;
    }
    public void setWindow(boolean window) {
        this.window = window;
    }
    public boolean isExtraLegroom() {
        return extraLegroom;
    }
    public void setExtraLegroom(boolean extraLegroom) {
        this.extraLegroom = extraLegroom;
    }
    public boolean isNearExit() {
        return nearExit;
    }
    public void setNearExit(boolean nearExit) {
        this.nearExit = nearExit;
    }
    public boolean isTaken() {
        return taken;
    }
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    public String getSeatClass() {
        return seatClass;
    }
    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
}

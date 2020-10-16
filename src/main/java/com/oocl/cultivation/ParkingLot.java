package com.oocl.cultivation;

public class ParkingLot {
    private int capacity;
    private int carsParked;

    public ParkingLot(int capacity, int carsParked) {
        this.capacity = capacity;
        this.carsParked = carsParked;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCarsParked() {
        return carsParked;
    }
}

package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingBoy{
    private final Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    public int capacity;
    public int carsParked;

    public ParkingBoy(ParkingLot parkingLot) {
        this.carsParked = parkingLot.getCarsParked();
        this.capacity = parkingLot.getCapacity();
    }

    public ParkingTicket park(Car car) {
        if(!isParkingLotFull()){
            ParkingTicket parkingTicket = new ParkingTicket();
            ticketCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
        return null;
    }

    private boolean isParkingLotFull() {
        return capacity == carsParked;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car carFromTicket = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return carFromTicket;
    }
}

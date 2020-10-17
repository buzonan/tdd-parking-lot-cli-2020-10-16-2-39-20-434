package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private int capacity;
    private int carsParked;

    public ParkingLot() {
        this.capacity = 10;
        this.carsParked = 0;
    }

    public ParkingLot(int capacity, int carsParked) {
        this.capacity = capacity;
        this.carsParked = carsParked;
    }

    public boolean isParkingLotFull() {
        return capacity == carsParked;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car carFromTicket = ticketCarMap.get(parkingTicket);
        ticketCarMap.remove(parkingTicket);
        return carFromTicket;
    }

    public boolean isParkingTicketExist(ParkingTicket parkingTicket){
        return ticketCarMap.containsKey(parkingTicket);
    }
}

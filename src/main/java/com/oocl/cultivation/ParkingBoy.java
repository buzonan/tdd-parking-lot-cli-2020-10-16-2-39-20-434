package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {
    private final Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    public ParkingBoy(ParkingLot parkingLot) {
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
}

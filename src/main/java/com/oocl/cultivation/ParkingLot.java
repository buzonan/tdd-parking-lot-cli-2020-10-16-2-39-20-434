package com.oocl.cultivation;

import com.oocl.cultivation.parkingboy.ParkingBoyTasks;

import java.util.HashMap;
import java.util.Map;


public class ParkingLot implements ParkingBoyTasks {
    private final Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private final int capacity;

    public ParkingLot() {
        this.capacity = 10;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        addCarToParkingLot(parkingTicket, car);
        return parkingTicket;
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        Car carFromTicket = ticketCarMap.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket);
        return carFromTicket;
    }

    public boolean isParkingLotFull() {
        return capacity == ticketCarMap.size();
    }

    public boolean isParkingTicketExist(ParkingTicket parkingTicket){
        return ticketCarMap.containsKey(parkingTicket);
    }

    private void addCarToParkingLot(ParkingTicket parkingTicket, Car car) {
        ticketCarMap.put(parkingTicket, car);
    }

    private void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        ticketCarMap.remove(parkingTicket);
    }

    public int getCarsParked() {
        return ticketCarMap.size();
    }

    public int getAvailableSpace() {
        return this.capacity - ticketCarMap.size();
    }

    public double getAvailableSpaceRatio() {
        return (getAvailableSpace() / (double) capacity);
    }
}

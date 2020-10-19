package com.oocl.cultivation;

import com.oocl.cultivation.exception.OutOfPositionException;
import com.oocl.cultivation.parkingboy.ParkingBoyTasks;

import java.util.HashMap;
import java.util.Map;


public class ParkingLot implements ParkingBoyTasks {
    private Map<ParkingTicket, Automobile> ticketCarMap = new HashMap<>();
    private int capacity;
    private int carsParked;
    private int availableSpace;
    private double availableSpaceRatio;

    public ParkingLot() {
        this.capacity = 10;
        this.carsParked = 0;
    }

    public ParkingLot(int capacity, int carsParked) {
        this.capacity = capacity;
        this.carsParked = carsParked;
    }

    @Override
    public ParkingTicket park(Automobile automobile) {
        ParkingTicket parkingTicket = new ParkingTicket();
        addCarToParkingLot(parkingTicket, automobile);
        return parkingTicket;
    }

    @Override
    public Automobile fetch(ParkingTicket parkingTicket) {
        Automobile automobileFromTicket = ticketCarMap.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket);
        return automobileFromTicket;
    }

    public void validateParkingLotCapacity() {
        if(isParkingLotFull()){
            throw new OutOfPositionException("Not enough position.");
        }
    }

    public boolean isParkingLotFull() {
        return capacity == carsParked;
    }

    public boolean isParkingTicketExist(ParkingTicket parkingTicket){
        return ticketCarMap.containsKey(parkingTicket);
    }

    private void addCarToParkingLot(ParkingTicket parkingTicket, Automobile automobile) {
        ticketCarMap.put(parkingTicket, automobile);
        ++carsParked;
    }

    private void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        ticketCarMap.remove(parkingTicket);
        --carsParked;
    }

    public Map<ParkingTicket, Automobile> getTicketCarMap() {
        return ticketCarMap;
    }

    public int getCarsParked() {
        return carsParked;
    }

    public int getAvailableSpace() {
        availableSpace = this.capacity - this.carsParked;
        return availableSpace;
    }

    public double getAvailableSpaceRatio() {
        availableSpaceRatio = (getAvailableSpace() / (double) capacity);
        return availableSpaceRatio;
    }
}

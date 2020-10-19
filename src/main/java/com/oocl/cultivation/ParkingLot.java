package com.oocl.cultivation;

import com.oocl.cultivation.exception.OutOfPositionException;
import com.oocl.cultivation.parkingboy.ParkingBoyTasks;

import java.util.HashMap;
import java.util.Map;


public class ParkingLot implements ParkingBoyTasks {
    private Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
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

    public void validateParkingLotCapacity() {
        if(isParkingLotFull()){
            throw new OutOfPositionException();
        }
    }

    public boolean isParkingLotFull() {
        return capacity == carsParked;
    }

    public boolean isParkingTicketExist(ParkingTicket parkingTicket){
        return ticketCarMap.containsKey(parkingTicket);
    }

    private void addCarToParkingLot(ParkingTicket parkingTicket, Car car) {
        ticketCarMap.put(parkingTicket, car);
        ++carsParked;
    }

    private void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        ticketCarMap.remove(parkingTicket);
        --carsParked;
    }

    public Map<ParkingTicket, Car> getTicketCarMap() {
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

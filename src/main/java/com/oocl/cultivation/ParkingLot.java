package com.oocl.cultivation;

import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;
import com.oocl.cultivation.parkingboy.ParkingBoyActions;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class ParkingLot implements ParkingBoyActions {
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

    @Override
    public ParkingTicket park(Car car) {
        validateParkingLotCapacity();
        ParkingTicket parkingTicket = new ParkingTicket();
        addCarToParkingLot(parkingTicket, car);
        return parkingTicket;
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        validateParkingTicket(parkingTicket);
        Car carFromTicket = ticketCarMap.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket);
        return carFromTicket;
    }

    public void validateParkingTicket(ParkingTicket parkingTicket){
        validateNullParkingTicket(parkingTicket);
        validateUsedParkingTicket(parkingTicket);
    }

    public void validateUsedParkingTicket(ParkingTicket parkingTicket) {
        if(!isParkingTicketExist(parkingTicket)){
            throw new InvalidParkingTicketException("Unrecognized parking ticket.");
        }
    }

    private void validateNullParkingTicket(ParkingTicket parkingTicket) {
        if(isNull(parkingTicket)){
            throw new InvalidParkingTicketException("Please provide your parking ticket.");
        }
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

    private void addCarToParkingLot(ParkingTicket parkingTicket, Car car) {
        ticketCarMap.put(parkingTicket, car);
        ++carsParked;
    }

    private void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        ticketCarMap.remove(parkingTicket);
        --carsParked;
    }
}

package com.oocl.cultivation;

import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;

import static java.util.Objects.isNull;

public class ParkingBoy{
    ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if(!parkingLot.isParkingLotFull()){
            return parkingLot.park(car);
        }
        throw new OutOfPositionException("Not enough position.");
    }

    public Car fetch(ParkingTicket parkingTicket) {
        validateParkingTicket(parkingTicket);
        return parkingLot.fetch(parkingTicket);
    }

    private void validateParkingTicket(ParkingTicket parkingTicket){
        validateNullParkingTicket(parkingTicket);
        validateUsedParkingTicket(parkingTicket);
    }

    private void validateUsedParkingTicket(ParkingTicket parkingTicket) {
        if(!parkingLot.isParkingTicketExist(parkingTicket)){
            throw new InvalidParkingTicketException("Unrecognized parking ticket.");
        }
    }

    private void validateNullParkingTicket(ParkingTicket parkingTicket) {
        if(isNull(parkingTicket)){
            throw new InvalidParkingTicketException("Please provide your parking ticket.");
        }
    }
}

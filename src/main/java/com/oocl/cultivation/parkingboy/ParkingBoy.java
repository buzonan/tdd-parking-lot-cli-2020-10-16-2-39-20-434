package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;

import static java.util.Objects.isNull;

public class ParkingBoy {
    ParkingLot parkingLot;
    ParkingBoyActions parkingBoyActions;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        return parkingBoyActions.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingBoyActions.fetch(parkingTicket);
    }

    public void validateParkingTicket(ParkingTicket parkingTicket){
        validateNullParkingTicket(parkingTicket);
        validateUsedParkingTicket(parkingTicket);
    }

    public void validateUsedParkingTicket(ParkingTicket parkingTicket) {
        if(!parkingLot.isParkingTicketExist(parkingTicket)){
            throw new InvalidParkingTicketException("Unrecognized parking ticket.");
        }
    }

    private void validateNullParkingTicket(ParkingTicket parkingTicket) {
        if(isNull(parkingTicket)){
            throw new InvalidParkingTicketException("Please provide your parking ticket.");
        }
    }

    public void validateParkingLotCapacity(ParkingLot parkingLot) {
        if(parkingLot.isParkingLotFull()){
            throw new OutOfPositionException("Not enough position.");
        }
    }


}

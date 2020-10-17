package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.InvalidParkingTicketException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ParkingBoy {
    ParkingLot parkingLot;
    List<ParkingLot> parkingLotList = new ArrayList<>();
    ParkingBoyActions parkingBoyActions;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
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

    private void validateUsedParkingTicket(ParkingTicket parkingTicket) {
        boolean isTicketValid =
                parkingLotList.stream()
                        .anyMatch(parkingLot
                                -> parkingLot.isParkingTicketExist(parkingTicket)
                        );

        if(!isTicketValid){
            throw new InvalidParkingTicketException("Unrecognized parking ticket.");
        }
    }

    private void validateNullParkingTicket(ParkingTicket parkingTicket) {
        if(isNull(parkingTicket)){
            throw new InvalidParkingTicketException("Please provide your parking ticket.");
        }
    }


}

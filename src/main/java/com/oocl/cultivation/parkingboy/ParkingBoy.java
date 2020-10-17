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


}

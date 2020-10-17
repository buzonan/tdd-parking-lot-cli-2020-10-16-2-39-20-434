package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;

public class StandardParkingBoy extends ParkingBoy implements ParkingBoyActions{

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) throws InvalidParkingTicketException{
        return parkingLot.fetch(parkingTicket);
    }

    @Override
    public ParkingTicket park(Car car) throws OutOfPositionException {
        return parkingLot.park(car);
    }


}

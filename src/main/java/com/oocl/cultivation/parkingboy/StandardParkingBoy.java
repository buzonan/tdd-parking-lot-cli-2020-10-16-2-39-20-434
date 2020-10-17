package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.OutOfPositionException;

public class StandardParkingBoy extends ParkingBoy implements ParkingBoyActions{

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        validateParkingTicket(parkingTicket);
        return parkingLot.fetch(parkingTicket);
    }

    @Override
    public ParkingTicket park(Car car) {
        if(!parkingLot.isParkingLotFull()){
            return parkingLot.park(car);
        }
        throw new OutOfPositionException("Not enough position.");
    }


}

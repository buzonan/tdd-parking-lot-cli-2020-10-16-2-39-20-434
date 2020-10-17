package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy implements ParkingBoyTasks{

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public Car fetch(ParkingTicket parkingTicket){
        return super.fetch(parkingTicket);
    }

    @Override
    public ParkingTicket park(Car car){
        checkParkingLotFull();
        ParkingLot parkingLot = parkingLotList.stream()
                .filter(parkingLotLocation -> !parkingLotLocation.isParkingLotFull())
                .findFirst()
                .get();
        return parkingLot.park(car);
    }


}

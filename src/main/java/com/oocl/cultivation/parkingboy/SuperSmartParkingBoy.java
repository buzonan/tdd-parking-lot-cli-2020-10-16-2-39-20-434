package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.parkingboy.ParkingBoy;
import com.oocl.cultivation.parkingboy.ParkingBoyTasks;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy implements ParkingBoyTasks {

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingTicket park(Car car) {
        checkParkingLotFull();
        ParkingLot parkingLot = parkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailableSpaceRatio)).get();
        return parkingLot.park(car);
    }
}

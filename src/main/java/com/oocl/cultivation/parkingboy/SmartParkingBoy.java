package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy implements ParkingBoyTasks {

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingTicket park(Car car) {
        checkParkingLotFull();
        ParkingLot parkingLot = parkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailableSpace)).get();
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return super.fetch(parkingTicket);
    }
}

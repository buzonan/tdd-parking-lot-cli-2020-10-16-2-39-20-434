package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Automobile;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy implements ParkingBoyTasks {

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingTicket park(Automobile automobile) {
        checkParkingLotFull();
        ParkingLot parkingLot = parkingLotList.stream().max(Comparator.comparing(ParkingLot::getAvailableSpaceRatio)).get();
        return parkingLot.park(automobile);
    }
}

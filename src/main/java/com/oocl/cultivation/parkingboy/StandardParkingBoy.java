package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Automobile;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy{

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingTicket park(Automobile automobile){
        checkParkingLotFull();
        ParkingLot parkingLot = parkingLotList.stream()
                .filter(parkingLotLocation -> !parkingLotLocation.isParkingLotFull())
                .findFirst()
                .get();
        return parkingLot.park(automobile);
    }


}

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
        validateParkingTicket(parkingTicket);
        return parkingLot.fetch(parkingTicket);
    }

    public ParkingTicket park(Car car){
        checkParkingLotFull();
        ParkingLot parkingLot = parkingLotList.stream().filter(parkingLot1 -> !parkingLot1.isParkingLotFull()).findFirst().get();
        return parkingLot.park(car);
    }


}

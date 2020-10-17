package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.OutOfPositionException;
import java.util.List;

public class StandardParkingBoy extends ParkingBoy implements ParkingBoyActions{

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public Car fetch(ParkingTicket parkingTicket){
        validateParkingTicket(parkingTicket);
        return parkingLotList.stream()
                .filter(parkingLot -> parkingLot.getTicketCarMap().containsKey(parkingTicket))
                .map(parkingLot -> parkingLot.fetch(parkingTicket))
                .findFirst()
                .get();
    }

    public ParkingTicket park(Car car){
        return parkingLotList.stream()
                .filter(parkingLot -> !parkingLot.isParkingLotFull())
                .map(parkingLot -> parkingLot.park(car))
                .findFirst()
                .orElseThrow(() -> new OutOfPositionException("Not enough position."));
    }


}

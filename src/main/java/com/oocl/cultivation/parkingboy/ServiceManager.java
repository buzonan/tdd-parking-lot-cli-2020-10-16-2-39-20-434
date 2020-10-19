package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.ParkingLot;
import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends StandardParkingBoy implements ParkingBoyTasks {
    List<ParkingBoy> parkingBoysList = new ArrayList<>();


    public ServiceManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ServiceManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    public void addParkingBoy(ParkingBoy parkingBoy){
        parkingBoysList.add(parkingBoy);
    }

    public void addParkingBoy(ParkingBoy parkingBoy, ParkingLot parkingLot){
        parkingBoysList.add(parkingBoy);
        parkingBoy.setParkingLotAssignment(parkingLot);
    }

    public void addParkingBoy(ParkingBoy parkingBoy, List<ParkingLot> parkingLotList){
        parkingBoysList.add(parkingBoy);
        parkingBoy.setParkingLotAssignment(parkingLotList);
    }

    public List<ParkingBoy> getParkingBoysList() {
        return parkingBoysList;
    }

    public ParkingBoy getParkingBoy(ParkingBoy assignedParkingBoy) {
        return getParkingBoysList().stream()
                .filter(parkingBoy -> parkingBoy.equals(assignedParkingBoy))
                .findAny()
                .get();
    }
}

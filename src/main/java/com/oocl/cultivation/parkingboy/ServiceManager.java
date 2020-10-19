package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.InvalidParkingTicketException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ServiceManager{
    List<ParkingBoy> parkingBoysList = new ArrayList<>();
    ParkingLot parkingLot;
    List<ParkingLot> parkingLotList = new ArrayList<>();

    public ServiceManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.parkingLotList.add(parkingLot);
    }

    public ServiceManager(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        try{
            ParkingLot parkingLot = parkingLotList.stream()
                    .filter(parkingLot1 ->
                            parkingLot1.isParkingTicketExist(parkingTicket))
                    .findFirst().get();
            ParkingBoy parkingBoy = getAssignedParkingBoy(parkingLot);
            return parkingBoy.fetch(parkingTicket);
        }catch (NoSuchElementException e){
            throw new InvalidParkingTicketException();
        }
    }

    public ParkingTicket park(Car car, ParkingBoy parkingBoy) {
        try {
            return getParkingBoy(parkingBoy).park(car);
        }catch (NullPointerException e){
            throw new InvalidParkingBoy();
        }
    }

    public ParkingTicket park(Car car) {
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        this.parkingBoysList.add(parkingBoy);
        return parkingBoy.park(car);
    }

    public void addParkingBoy(ParkingBoy parkingBoy){
        parkingBoysList.add(parkingBoy);
    }

    public void addParkingBoy(List<ParkingBoy> parkingBoys){
        parkingBoysList.addAll(parkingBoys);
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
    public ParkingBoy getAssignedParkingBoy(ParkingLot parkingLot) {
        return getParkingBoysList().stream()
                .filter(parkingBoy -> parkingBoy.parkingLotList.contains(parkingLot)).findFirst().get();
    }
}

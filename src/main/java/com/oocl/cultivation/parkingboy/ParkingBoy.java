package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class ParkingBoy implements ParkingBoyTasks{
    ParkingLot parkingLot;
    List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.parkingLotList.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        checkParkingLotFull();
        ParkingLot parkingLot = parkingLotList.stream()
                .filter(parkingLotLocation -> !parkingLotLocation.isParkingLotFull())
                .findFirst()
                .get();
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        validateParkingTicket(parkingTicket);
        ParkingLot parkingLot = parkingLotList.stream()
                .filter(parkingLotLocation -> parkingLotLocation.isParkingTicketExist(parkingTicket))
                .findFirst()
                .get();
        return parkingLot.fetch(parkingTicket);    }

    public void validateParkingTicket(ParkingTicket parkingTicket){
        validateNullParkingTicket(parkingTicket);
        validateUsedParkingTicket(parkingTicket);
    }

    private void validateUsedParkingTicket(ParkingTicket parkingTicket) {
        boolean isTicketValid =
                parkingLotList.stream()
                        .anyMatch(parkingLot
                                -> parkingLot.isParkingTicketExist(parkingTicket)
                        );

        if(!isTicketValid){
            throw new InvalidParkingTicketException("Unrecognized parking ticket.");
        }
    }

    private void validateNullParkingTicket(ParkingTicket parkingTicket) {
        if(isNull(parkingTicket)){
            throw new InvalidParkingTicketException("Please provide your parking ticket.");
        }
    }

    public void checkParkingLotFull(){
        if(parkingLotList.stream().allMatch(ParkingLot::isParkingLotFull)){
            throw new OutOfPositionException();
        }
    }

    protected void setParkingLotAssignment(ParkingLot parkingLot) {
        this.parkingLotList = new ArrayList<>();
        this.parkingLotList.add(parkingLot);
    }

    protected void setParkingLotAssignment(List<ParkingLot> parkingLotList) {
        this.parkingLotList = new ArrayList<>();
        this.parkingLotList = parkingLotList;
    }

    protected void addParkingLotAssignment(ParkingLot parkingLot) {
        this.parkingLotList.add(parkingLot);
    }
}

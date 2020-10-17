package com.oocl.cultivation;

public class ParkingBoy{
    ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if(!parkingLot.isParkingLotFull()){
            return parkingLot.park(car);
        }
        return null;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket == null){
            throw new UnrecognizedParkingTicketException("Please provide your parking ticket");
        }
        if(parkingLot.fetch(parkingTicket) == null){
            throw new UnrecognizedParkingTicketException("Unrecognized parking ticket");
        }
        return parkingLot.fetch(parkingTicket);
    }
}

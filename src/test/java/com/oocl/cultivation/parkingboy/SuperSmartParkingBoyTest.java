package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest {

    @Test
    void  should_return_ticket_from_parking_lot2_when_super_smart_parking_boy_park_given_lot2_has_more_space_ratio(){
        //GIVEN
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10, 6);
        ParkingLot parkingLot2 = new ParkingLot(10,5);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //WHEN
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //THEN
        assertNotNull(parkingTicket);
        assertEquals(car, parkingLot2.fetch(parkingTicket));
    }

    @Test
    void should_return_InvalidParkingTicketException_for_SuperSmartParkingBoy_when_fetch_given_Invalid_ticket() {
        //GIVEN
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10, 5);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);

        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //WHEN
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetch(parkingTicket);

        //THEN
        Exception exception = assertThrows(InvalidParkingTicketException.class,
                () -> superSmartParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());

        exception = assertThrows(InvalidParkingTicketException.class, () -> superSmartParkingBoy.fetch(null));
        assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_OutOfPositionException_for_SuperSmartParkingBoy_when_park_given_all_parking_lots_full() {
        //GIVEN
        Car car = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10, 10);
        ParkingLot parkingLot2 = new ParkingLot(10,9);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        superSmartParkingBoy.park(car);

        //WHEN
        //THEN
        Exception exception = assertThrows(OutOfPositionException.class, () -> superSmartParkingBoy.park(car2));
        assertEquals("Not enough position.", exception.getMessage());
    }

}
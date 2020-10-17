package com.oocl.cultivation;

import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;
import com.oocl.cultivation.parkingboy.ParkingBoy;
import com.oocl.cultivation.parkingboy.StandardParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    @Test
    void should_return_parking_ticket_when_parking_given_parking_boy_park() {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot());

        //WHEN
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //THEN
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_correct_car_when_fetch_given_correct_ticket() {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //WHEN
        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        //THEN
        assertSame(car, fetchedCar);
    }

    @Test
    void should_return_two_cars_when_fetch_given_two_correct_ticket() {
        //GIVEN
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket1 = parkingBoy.park(car1);
        ParkingTicket parkingTicket2 = parkingBoy.park(car2);

        //WHEN
        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);

        //THEN
        assertSame(car1, fetchedCar1);
        assertSame(car2, fetchedCar2);
    }

    @Test
    void should_return_InvalidParkingTicketException_when_fetch_given_incorrect_ticket() {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot());
        ParkingTicket incorrectParkingTicket = new ParkingTicket();

        //WHEN
        //THEN
        Exception exception = assertThrows(InvalidParkingTicketException.class, () -> parkingBoy.fetch(incorrectParkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_InvalidParkingTicketException_when_fetch_given_no_ticket() {
        //GIVEN
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot());

        //WHEN
        //THEN
        Exception exception = assertThrows(InvalidParkingTicketException.class, () -> parkingBoy.fetch(null));
        assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_InvalidParkingTicketException_when_fetch_given_used_ticket() {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot());
        ParkingTicket parkingUsedTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingUsedTicket);

        //WHEN
        //THEN
        Exception exception = assertThrows(InvalidParkingTicketException.class, () -> parkingBoy.fetch(parkingUsedTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_OutOfPositionException_and_fail_park_when_park_given_full_parking_lot_capacity() {
        //GIVEN
        Car car = new Car();
        Car car2 = new Car();
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot(1, 0));
        parkingBoy.park(car);

        //WHEN
        //THEN
        Exception exception = assertThrows(OutOfPositionException.class, () -> parkingBoy.park(car2));
        assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    void should_return_parking_ticket_for_parking_lot_2_when_park_given_parking_lot_1_is_full() {
        //GIVEN
        Car car = new Car();
        ParkingLot fullParkingLot = new ParkingLot(1, 1);
        ParkingLot emptyParkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(fullParkingLot);
        parkingLotList.add(emptyParkingLot);

        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLotList);

        //WHEN
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //THEN
        assertNotNull(parkingTicket);
        assertEquals(1, emptyParkingLot.getCarsParked());
    }
}

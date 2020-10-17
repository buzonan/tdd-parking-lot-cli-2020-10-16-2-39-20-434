package com.oocl.cultivation;

import com.oocl.cultivation.exception.InvalidParkingTicketException;
import com.oocl.cultivation.exception.OutOfPositionException;
import com.oocl.cultivation.parkingboy.ParkingBoy;
import com.oocl.cultivation.parkingboy.SmartParkingBoy;
import com.oocl.cultivation.parkingboy.StandardParkingBoy;
import com.oocl.cultivation.parkingboy.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import javax.jnlp.ServiceManager;
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
        Exception exception = assertThrows(InvalidParkingTicketException.class,
                () -> parkingBoy.fetch(incorrectParkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_InvalidParkingTicketException_when_fetch_given_no_ticket() {
        //GIVEN
        ParkingBoy parkingBoy = new StandardParkingBoy(new ParkingLot());

        //WHEN
        //THEN
        Exception exception = assertThrows(InvalidParkingTicketException.class,
                () -> parkingBoy.fetch(null));
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
        Exception exception = assertThrows(InvalidParkingTicketException.class,
                () -> parkingBoy.fetch(parkingUsedTicket));
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
        ParkingLot parkingLot1 = new ParkingLot(1, 1);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);

        ParkingBoy parkingBoy = new StandardParkingBoy(parkingLotList);

        //WHEN
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //THEN
        assertNotNull(parkingTicket);
        assertEquals(car, parkingLot2.fetch(parkingTicket));
    }

    @Test
    void should_return_parking_ticket_for_parking_lot_2_when_park_given_parking_lot_2_has_more_space() {
        //GIVEN
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10, 5);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);

        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //WHEN
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //THEN
        assertNotNull(parkingTicket);
        assertEquals(car, parkingLot2.fetch(parkingTicket));
    }

    @Test
    void should_return_InvalidParkingTicketException_for_smart_parking_boy_when_fetch_given_invalid_parking_ticket() {
        //GIVEN
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10, 5);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);

        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //WHEN
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetch(parkingTicket);

        //THEN
        Exception exception = assertThrows(InvalidParkingTicketException.class,
                () -> smartParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized parking ticket.", exception.getMessage());

        exception = assertThrows(InvalidParkingTicketException.class, () -> smartParkingBoy.fetch(null));
        assertEquals("Please provide your parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_OutOfPositionException_for_smart_parking_boy_when_park_given_all_parking_lots_full() {
        //GIVEN
        Car car = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(10, 10);
        ParkingLot parkingLot2 = new ParkingLot(10,9);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        smartParkingBoy.park(car);

        //WHEN
        //THEN
        Exception exception = assertThrows(OutOfPositionException.class, () -> smartParkingBoy.park(car2));
        assertEquals("Not enough position.", exception.getMessage());
    }

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

    @Test
    void should_add_parking_boy_to_management_list_when_add_parking_boy_given_service_manager_adds_parking_boy() {
        //GIVEN
        ServiceManager serviceManager = new ServiceManager();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        ParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot1);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot3);

        //WHEN
        serviceManager.addParkingBoy(standardParkingBoy);
        serviceManager.addParkingBoy(smartParkingBoy, parkingLot3);
        serviceManager.addParkingBoy(superSmartParkingBoy, parkingLot2);

        //THEN
        assertSame(standardParkingBoy, serviceManager.getParkingBoy(parkingLot1));
        assertSame(smartParkingBoy, serviceManager.getParkingBoy(parkingLot3));
        assertSame(superSmartParkingBoy, serviceManager.getParkingBoy(parkingLot2));
    }




}

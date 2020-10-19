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

class ServiceManagerTest {
    @Test
    void should_add_parking_boy_to_management_list_when_add_parking_boy_given_service_manager_adds_parking_boy() {
        //GIVEN
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        parkingLotList.add(parkingLot3);

        ServiceManager serviceManager = new ServiceManager(parkingLotList);

        List<ParkingBoy> parkingBoyList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot3);

        parkingBoyList.add(parkingBoy);
        parkingBoyList.add(smartParkingBoy);
        parkingBoyList.add(superSmartParkingBoy);

        //WHEN
        serviceManager.addParkingBoy(parkingBoyList);

        //THEN
        assertTrue(serviceManager.getParkingBoysList().contains(parkingBoy));
        assertTrue(serviceManager.getParkingBoysList().contains(smartParkingBoy));
        assertTrue(serviceManager.getParkingBoysList().contains(superSmartParkingBoy));
    }

    @Test
    void should_return_parking_ticket_when_park_given_service_manager_assigns_parking_boy_to_park() {
        //GIVEN
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        parkingLotList.add(parkingLot3);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot3);
        serviceManager.addParkingBoy(parkingBoy);
        serviceManager.addParkingBoy(smartParkingBoy);
        serviceManager.addParkingBoy(superSmartParkingBoy);

        //WHEN
        ParkingTicket parkingTicket = serviceManager.park(car, smartParkingBoy);

        //THEN
        assertSame(car, serviceManager.fetch(parkingTicket));
    }

    @Test
    void should_return_InvalidParkingTicketException_when_park_fetch_given_serviceMnager_assigns_wrong_parkingBoy() {
        //GIVEN
        Car car = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        parkingLotList.add(parkingLot3);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot3);
        serviceManager.addParkingBoy(parkingBoy);
        serviceManager.addParkingBoy(smartParkingBoy);
        serviceManager.addParkingBoy(superSmartParkingBoy);

        //WHEN
        //THEN
        assertThrows(InvalidParkingTicketException.class,
                () -> serviceManager.fetch(new ParkingTicket()));

        assertThrows(InvalidParkingTicketException.class,
                () -> serviceManager
                        .getParkingBoy(superSmartParkingBoy)
                        .fetch(null));
    }

    @Test
    void should_return_OutOfPosition_when_parking_lot_full_given_serviceMnager_assigns_fetch_to_parkingBoy() {
        //GIVEN
        Car car = new Car();
        Car car2 = new Car();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        parkingLotList.add(parkingLot3);
        ServiceManager serviceManager = new ServiceManager(parkingLotList);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot2);
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot3);
        serviceManager.addParkingBoy(parkingBoy);
        serviceManager.addParkingBoy(smartParkingBoy);
        serviceManager.addParkingBoy(superSmartParkingBoy);

        //WHEN
        serviceManager.park(car, parkingBoy);
        //THEN
        Exception exception = assertThrows(OutOfPositionException.class, () -> serviceManager.park(car2, parkingBoy));
        assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    void should_return_parkingTicket_when_park_given_service_manager_parks() {
        //GIVEN
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);

        ServiceManager serviceManager = new ServiceManager(parkingLot);

        //WHEN
        ParkingTicket parkingTicket = serviceManager.park(car);

        //THEN
        assertTrue(parkingLot.isParkingLotFull());
        assertEquals(1, parkingLot.getCarsParked());
        assertSame(car, serviceManager.fetch(parkingTicket));
    }

}
package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.LockSupport;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingBoyTest {
    @Test
    void should_return_parking_ticket_when_parking_given_parking_boy_park() {
        //GIVEN
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        //WHEN
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //THEN
        assertNotNull(parkingTicket);
    }
}

package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingTicket;

public interface ParkingBoyActions {
    Car fetch(ParkingTicket parkingTicket);
    ParkingTicket park(Car car);
}

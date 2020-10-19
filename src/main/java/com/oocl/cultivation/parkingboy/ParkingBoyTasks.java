package com.oocl.cultivation.parkingboy;

import com.oocl.cultivation.Automobile;
import com.oocl.cultivation.ParkingTicket;

public interface ParkingBoyTasks {
    Automobile fetch(ParkingTicket parkingTicket);
    ParkingTicket park(Automobile automobile);
}

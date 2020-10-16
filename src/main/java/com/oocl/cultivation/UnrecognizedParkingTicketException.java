package com.oocl.cultivation;

public class UnrecognizedParkingTicketException extends RuntimeException{
    public UnrecognizedParkingTicketException(String message){
        super(message);
    }
}

package com.oocl.cultivation.exception;

public class InvalidParkingTicketException extends RuntimeException{
    public InvalidParkingTicketException(String message){
        super(message);
    }

    public InvalidParkingTicketException(){
        super();
    }
}

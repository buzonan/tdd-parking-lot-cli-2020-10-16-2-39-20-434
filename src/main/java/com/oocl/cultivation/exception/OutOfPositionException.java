package com.oocl.cultivation.exception;

public class OutOfPositionException extends RuntimeException {

    public static final String NOT_ENOUGH_POSITION = "Not enough position.";

    public OutOfPositionException() {
        super(NOT_ENOUGH_POSITION);
    }
}

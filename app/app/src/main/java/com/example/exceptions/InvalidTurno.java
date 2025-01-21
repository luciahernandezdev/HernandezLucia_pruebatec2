package com.example.exceptions;

public class InvalidTurno extends Exception{
    public InvalidTurno() {
    }

    public InvalidTurno(String message) {
        super(message);
    }
}

package com.example.exceptions;

public class InvalidTurno extends Exception{
    public InvalidTurno(String s, Exception e) {
    }

    public InvalidTurno(String message) {
        super(message);
    }
}

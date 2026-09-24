package com.gabriel.berlinclock.domain;

public class InvalidBerlinTimeException extends RuntimeException {
    public InvalidBerlinTimeException(String message) {
        super(message);
    }
}

package com.gabriel.berlinclock.domain.api;

import com.gabriel.berlinclock.domain.BerlinClock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record BerlinClockResponse(String digitalTime, String berlinTime) {
    private static final DateTimeFormatter DIGITAL_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static BerlinClockResponse createFrom(LocalTime time, BerlinClock clock){
        return new BerlinClockResponse(time.format(DIGITAL_FORMAT), clock.getBerlinTime(time));
    }
}

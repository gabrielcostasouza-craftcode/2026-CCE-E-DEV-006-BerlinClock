package com.gabriel.berlinclock.domain.api;

import com.gabriel.berlinclock.domain.BerlinClock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record BerlinClockResponse(String digitalTime, String berlinTime, List<String> rows) {
    private static final DateTimeFormatter DIGITAL_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static BerlinClockResponse createFrom(LocalTime time, BerlinClock clock){
        return new BerlinClockResponse(time.format(DIGITAL_FORMAT), clock.getBerlinTime(time), clock.getRows(time));
    }
}

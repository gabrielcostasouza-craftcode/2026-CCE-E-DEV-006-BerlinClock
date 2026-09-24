package com.gabriel.berlinclock.parser;

import java.time.LocalTime;

public class BerlinClockParser {

    public String singleMinuteRow(LocalTime time) {
        int lightedUpLamps = time.getMinute() % 5; //5 because every 5th min lamp will be on another row.
        return "Y".repeat(lightedUpLamps) + "0".repeat(4-lightedUpLamps);
    }

    public String fiveMinuteRow(LocalTime time) {
        throw new UnsupportedOperationException("This convertor hasn't been implemented yet");
    }

    public String singleHourRow(LocalTime time) {
        throw new UnsupportedOperationException("This convertor hasn't been implemented yet");
    }

    public String fiveHourRow(LocalTime time) {
        throw new UnsupportedOperationException("This convertor hasn't been implemented yet");
    }

    public String secondsLampRow(LocalTime time) {
        throw new UnsupportedOperationException("This convertor hasn't been implemented yet");
    }

    public String parseFull(LocalTime time) {
        throw new UnsupportedOperationException("This convertor hasn't been implemented yet");
    }
}

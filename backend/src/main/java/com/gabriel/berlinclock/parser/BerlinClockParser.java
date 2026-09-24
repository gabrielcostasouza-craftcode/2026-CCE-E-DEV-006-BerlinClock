package com.gabriel.berlinclock.parser;

import java.time.LocalTime;

public class BerlinClockParser {

    public String singleMinuteRow(LocalTime time) {
        int lightedUpLamps = time.getMinute() % 5; //5 because every 5th min lamp will be on another row.
        return "Y".repeat(lightedUpLamps) + "0".repeat(4-lightedUpLamps);
    }
}

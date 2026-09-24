package com.gabriel.berlinclock.parser;

import java.time.LocalTime;

public class BerlinClockParser {

    //The bottom row represents 1 minute blocks, and is made up of 4 yellow lamps.
    public String singleMinuteRow(LocalTime time) {
        int lightedUpLamps = time.getMinute() % 5; //5 because every 5th min lamp will be on another row.
        return "Y".repeat(lightedUpLamps) + "0".repeat(4-lightedUpLamps);
    }

    //The final two rows represent the minutes. The upper row represents 5 minute blocks, and is made up of 11 lamps- every third lamp is red, the rest are yellow.
    public String fiveMinuteRow(LocalTime time) {
        int lightedUpLamps = time.getMinute() % 5; // 5 bcs every lamp is 5 minutes.
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 11; i++) { // max 11 lamps
            if(i > lightedUpLamps) {
                sb.append("0");
            }else{
                sb.append(i % 3 == 0 ? "R" : "Y");
            }
        }
        return sb.toString();
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

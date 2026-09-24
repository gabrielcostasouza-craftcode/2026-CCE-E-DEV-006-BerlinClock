package com.gabriel.berlinclock.domain;

import com.gabriel.berlinclock.domain.row.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record BerlinClock(List<IBerlinRow> rows) {

    public static BerlinClock factory() {
        return new BerlinClock(
                List.of(
                        new SecondsLampRow(),
                        new FiveHourRow(),
                        new SingleHourRow(),
                        new FiveMinuteRow(),
                        new SingleMinuteRow()
                )
        );
    }

    public String getBerlinTime(LocalTime time) {
        var lamps = rows.stream().map(e -> e.parse(time)).toList();
        return lamps.stream().map(Lamp::convertListToLetterString).collect(Collectors.joining());
    }

    public LocalTime decodeToBerlinTime(String berlinTime){
        throw new IllegalArgumentException("Not yet implemented!");
    }

}

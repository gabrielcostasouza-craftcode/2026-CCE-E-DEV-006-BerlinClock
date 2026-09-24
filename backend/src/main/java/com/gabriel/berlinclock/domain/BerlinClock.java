package com.gabriel.berlinclock.domain;

import com.gabriel.berlinclock.domain.row.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record BerlinClock(List<IBerlinRow> rows) {

    public static BerlinClock standard() {
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
        return getRows(time).stream().collect(Collectors.joining());
    }

    public List<String> getRows(LocalTime time) {
        var lamps = rows.stream().map(e -> e.parse(time)).toList();
        return lamps.stream().map(Lamp::convertListToLetterString).toList();
    }

    public LocalTime decodeBerlinTimeToLocalTime(String berlinTime) {
        int maxLength = rows.stream().mapToInt(IBerlinRow::size).sum();
        if(berlinTime.length() != maxLength){
            throw new RuntimeException("Received length is not equal one that we expect");
        }

        var lamps = Lamp.getLampsFromString(berlinTime);
        int totalSeconds = 0;
        int index = 0;
        for (IBerlinRow row : rows) {
            totalSeconds += row.decode(lamps.subList(index, index + row.size()));
            index += row.size();
        }
        return LocalTime.ofSecondOfDay(totalSeconds);
    }

}

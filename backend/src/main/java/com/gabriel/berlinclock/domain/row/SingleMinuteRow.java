package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

public class SingleMinuteRow implements IBerlinRow{
    @Override
    public List<Lamp> parse(LocalTime time) {
        int lightedUpLamps = time.getMinute() % 5; //5 because every 5th min lamp will be on another row.
        int amountOfLamps = 4;
        return IntStream.rangeClosed(1, amountOfLamps).mapToObj(p -> p <= lightedUpLamps ? Lamp.YELLOW : Lamp.OFF).toList();
    }
}

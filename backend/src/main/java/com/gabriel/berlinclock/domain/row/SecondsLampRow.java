package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;
import java.util.List;

public class SecondsLampRow implements IBerlinRow {

    @Override
    public List<Lamp> parse(LocalTime time) {
        return List.of(time.getSecond() % 2 == 0 ? Lamp.YELLOW : Lamp.OFF);
    }

    @Override
    public int decode(List<Lamp> lamps) {
        return lamps.getFirst() == Lamp.YELLOW ? 0 : 1;
    }

    @Override
    public int size() {
        return 1;
    }
}

package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.IntStream;

public abstract class BerlinRow implements IBerlinRow {

    private final int amountOfLamps;

    public BerlinRow(int amountOfLamps) {
        this.amountOfLamps = amountOfLamps;
    }

    @Override
    public List<Lamp> parse(LocalTime time) {
        return IntStream.rangeClosed(1, size()).mapToObj(p -> p <= lightedUpLamps(time) ? lightLampUpAtPositions(p) : Lamp.OFF).toList();
    }

    @Override
    public int decode(List<Lamp> lamps) {
        return (int) lamps.stream().filter(l -> l != Lamp.OFF).count() * secondsPerLamp();
    }

    @Override
    public int size() {
        return amountOfLamps;
    }

    protected abstract int lightedUpLamps(LocalTime time);

    protected abstract Lamp lightLampUpAtPositions(int pos);

    protected abstract int secondsPerLamp();
}

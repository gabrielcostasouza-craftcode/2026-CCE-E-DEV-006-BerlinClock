package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;

public class FiveHourRow extends BerlinRow {

    public FiveHourRow() {
        super(4);
    }

    @Override
    protected int lightedUpLamps(LocalTime time) {
        return time.getHour() / 5;
    }

    @Override
    protected Lamp lightLampUpAtPositions(int pos) {
        return Lamp.RED;
    }

    @Override
    protected int secondsPerLamp() {
        return 5 * 60 * 60;
    }
}

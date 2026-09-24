package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;

public class FiveMinuteRow extends BerlinRow {

    public FiveMinuteRow() {
        super(11);
    }

    @Override
    protected int lightedUpLamps(LocalTime time) {
        return time.getMinute() / 5;
    }

    @Override
    protected Lamp lightLampUpAtPositions(int pos) {
        return pos % 3 == 0 ? Lamp.RED : Lamp.YELLOW;
    }
}

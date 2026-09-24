package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;

public class SingleMinuteRow extends BerlinRow {

    public SingleMinuteRow() {
        super(4);
    }

    @Override
    protected int lightedUpLamps(LocalTime time) {
        return time.getMinute() % 5;
    }

    @Override
    protected Lamp lightLampUpAtPositions(int pos) {
        return Lamp.YELLOW;
    }
}

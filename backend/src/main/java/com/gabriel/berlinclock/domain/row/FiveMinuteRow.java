package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class FiveMinuteRow implements IBerlinRow{
    @Override
    public List<Lamp> parse(LocalTime time) {
        int lightedUpLamps = time.getMinute() / 5; // 5 bcs every lamp is 5 minutes.
        int amountOfLamps = 11;

        List<Lamp> lamps = new LinkedList<>();
        for (int i = 1; i <= amountOfLamps; i++) { // max 11 lamps
            if(i > lightedUpLamps) {
                lamps.add(Lamp.OFF);
            }else{
                lamps.add(i % 3 == 0 ? Lamp.RED : Lamp.YELLOW);
            }
        }
        return lamps;
    }
}

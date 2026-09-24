package com.gabriel.berlinclock.domain.row;

import com.gabriel.berlinclock.domain.Lamp;

import java.time.LocalTime;
import java.util.List;

public interface IBerlinRow {
    List<Lamp> parse(LocalTime time);
}

package com.gabriel.berlinclock.controller;

import com.gabriel.berlinclock.domain.BerlinClock;
import com.gabriel.berlinclock.domain.api.BerlinClockResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("/api")
public class BerlinClockController {

    private final BerlinClock clock;

    public BerlinClockController() {
        this.clock = BerlinClock.standard();
    }

    @GetMapping("/to-berlin-time")
    public BerlinClockResponse toBerlinTime(@RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime localtime) {
        return BerlinClockResponse.createFrom(localtime, clock);
    }
}

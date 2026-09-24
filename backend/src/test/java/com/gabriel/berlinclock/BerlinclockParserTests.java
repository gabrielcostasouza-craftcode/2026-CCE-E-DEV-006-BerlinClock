package com.gabriel.berlinclock;

import com.gabriel.berlinclock.domain.BerlinClock;
import com.gabriel.berlinclock.domain.Lamp;
import com.gabriel.berlinclock.domain.row.*;
import com.gabriel.berlinclock.parser.BerlinClockParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BerlinclockParserTests {

    private final BerlinClock bc = BerlinClock.factory();
    private final IBerlinRow singleMinuteRow = new SingleMinuteRow();
    private final IBerlinRow fiveMinuteRow = new FiveMinuteRow();
    private final IBerlinRow singleHourRow = new SingleHourRow();
    private final IBerlinRow fiveHourRow = new FiveHourRow();
    private final IBerlinRow secondsLamp = new SecondsLampRow();

    @Test
    void contextLoads() {
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "00:00:00,	OOOO",
            "23:59:59,	YYYY",
            "12:32:00,	YYOO",
            "12:34:00,	YYYY",
            "12:35:00,	OOOO"
    })

    void singleMinuteRowLightsUpEveryMinuteNoMultiplesFive(LocalTime time, String expectedLights) {
        assertThat(singleMinuteRow.parse(time)).isEqualTo(Lamp.getLampsFromString(expectedLights));
    }


    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "00:00:00,	OOOOOOOOOOO",
            "23:59:59,	YYRYYRYYRYY",
            "12:04:00,	OOOOOOOOOOO",
            "12:23:00,	YYRYOOOOOOO",
            "12:35:00,	YYRYYRYOOOO"
    })
    void fiveMinuteRowLightsUpLampForEveryFiveMinutesAndRedEveryThirdLamp(LocalTime time, String expectedLights) {
        assertThat(fiveMinuteRow.parse(time)).isEqualTo(Lamp.getLampsFromString(expectedLights));

    }


    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "00:00:00,	OOOO",
            "23:59:59,	RRRO",
            "02:04:00,	RROO",
            "08:23:00,	RRRO",
            "14:35:00,	RRRR"
    })
    void singleHourRowLightsUpEveryMinuteNoMultiplesFive(LocalTime time, String expectedLights) {
        assertThat(singleHourRow.parse(time)).isEqualTo(Lamp.getLampsFromString(expectedLights));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "00:00:00,	OOOO",
            "23:59:59,	RRRR",
            "02:04:00,	OOOO",
            "08:23:00,	ROOO",
            "16:35:00,	RRRO"
    })
    void fireHourRowLightsUpRedLampEveryFifthHour(LocalTime time, String expectedLights) {
        assertThat(fiveHourRow.parse(time)).isEqualTo(Lamp.getLampsFromString(expectedLights));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "00:00:00,	Y",
            "23:59:59,	O"
    })
    void lampOnEveryEvenSecond(LocalTime time, String expectedLights) {
        assertThat(secondsLamp.parse(time)).isEqualTo(Lamp.getLampsFromString(expectedLights));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "00:00:00,	YOOOOOOOOOOOOOOOOOOOOOOO",
            "23:59:59,	ORRRRRRROYYRYYRYYRYYYYYY",
            "16:50:06,	YRRROROOOYYRYYRYYRYOOOOO",
            "11:37:01,	ORROOROOOYYRYYRYOOOOYYOO"
    })
    void fullBerlinClockGetsAllRows(LocalTime time, String expectedLights) {
        assertThat(bc.getTime(time)).isEqualTo(expectedLights);
    }



}

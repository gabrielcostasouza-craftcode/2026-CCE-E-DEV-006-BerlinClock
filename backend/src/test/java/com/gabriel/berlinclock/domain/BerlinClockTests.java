package com.gabriel.berlinclock.domain;

import com.gabriel.berlinclock.domain.row.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BerlinClockTests {

    private final BerlinClock bc = BerlinClock.standard();
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
        assertThat(bc.getBerlinTime(time)).isEqualTo(expectedLights);
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "23:59:59,	ORRRRRRROYYRYYRYYRYYYYYY, O, RRRR, RRRO, YYRYYRYYRYY, YYYY",

            "16:50:06,	YRRROROOOYYRYYRYYRYOOOOO, Y, RRRO, ROOO, YYRYYRYYRYO, OOOO",
    })
    void localTimeReturnsRows(LocalTime time, String expectedLights, String seconds, String fiveHour, String singleHour, String fiveMinutes, String singleMinutes) {
        assertThat(bc.getBerlinTime(time)).isEqualTo(expectedLights);
        assertThat(bc.getRows(time).get(0)).isEqualTo(seconds);
        assertThat(bc.getRows(time).get(1)).isEqualTo(fiveHour);
        assertThat(bc.getRows(time).get(2)).isEqualTo(singleHour);
        assertThat(bc.getRows(time).get(3)).isEqualTo(fiveMinutes);
        assertThat(bc.getRows(time).get(4)).isEqualTo(singleMinutes);
    }


    @ParameterizedTest(name = "{0} -> {1}")
    // We cannot get the full seconds back since we only track even and odds. Hence why I changed
//    @CsvSource({
//            "YOOOOOOOOOOOOOOOOOOOOOOO,	00:00:00",
//            "ORRRRRRROYYRYYRYYRYYYYYY,	23:59:59",
//            "YRRROROOOYYRYYRYYRYOOOOO,	16:50:06",
//            "ORROOROOOYYRYYRYOOOOYYOO,	11:37:01"
//    })
    @CsvSource({
            "YOOOOOOOOOOOOOOOOOOOOOOO,	00:00:00",
            "ORRRRRRROYYRYYRYYRYYYYYY,	23:59:01",
            "YRRROROOOYYRYYRYYRYOOOOO,	16:50:00",
            "ORROOROOOYYRYYRYOOOOYYOO,	11:37:01"
    })
    void fullBerlinToDigital(String berlinTime, LocalTime expectedTime) {
        assertThat(bc.decodeBerlinTimeToLocalTime(berlinTime)).isEqualTo(expectedTime);
    }

}

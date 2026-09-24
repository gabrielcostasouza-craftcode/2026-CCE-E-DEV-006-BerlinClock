package com.gabriel.berlinclock;

import com.gabriel.berlinclock.parser.BerlinClockParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BerlinclockParserTests {

    private final BerlinClockParser bcParser = new BerlinClockParser();

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
        assertThat(bcParser.singleMinuteRow(time).equals(expectedLights));

    }

}

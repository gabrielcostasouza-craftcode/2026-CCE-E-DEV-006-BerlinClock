package com.gabriel.berlinclock.domain;


import com.gabriel.berlinclock.controller.BerlinClockController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BerlinClockController.class)
public class BerlinClockControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "00:00:00,	YOOOOOOOOOOOOOOOOOOOOOOO",
            "23:59:59,	ORRRRRRROYYRYYRYYRYYYYYY",
            "16:50:06,	YRRROROOOYYRYYRYYRYOOOOO",
            "11:37:01,	ORROOROOOYYRYYRYOOOOYYOO"
    })
    void digitalTimeToBerlinTime(String time, String expectedLights) throws Exception {
        mockMvc.perform(get("/api/to-berlin-time").param("localtime", time))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.digitalTime").value(time))
                .andExpect(jsonPath("$.berlinTime").value(expectedLights));
    }

    @Test
    void digitalTimeToBerlinTimeRejectInvalidTime() throws Exception {
        mockMvc.perform(get("/api/to-berlin-time").param("localtime", "25:59:59"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void digitalTimeToBerlinTimeRejectMissingTime() throws Exception {
        mockMvc.perform(get("/api/to-berlin-time"))
                .andExpect(status().isBadRequest());
    }


    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "YOOOOOOOOOOOOOOOOOOOOOOO,	00:00:00",
            "ORRRRRRROYYRYYRYYRYYYYYY,	23:59:01",
            "YRRROROOOYYRYYRYYRYOOOOO,	16:50:00",
            "ORROOROOOYYRYYRYOOOOYYOO,	11:37:01"
    })
    void berlinTimeToDigitalTime(String time, String expectedLights) throws Exception {
        mockMvc.perform(get("/api/to-digital-time").param("localtime", time))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.digitalTime").value(time))
                .andExpect(jsonPath("$.berlinTime").value(expectedLights));
    }


}

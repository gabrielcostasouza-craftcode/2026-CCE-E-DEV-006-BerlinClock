package com.gabriel.berlinclock.domain;


import com.gabriel.berlinclock.controller.BerlinClockController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BerlinClockController.class)
public class BerlinClockControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void digitalTimeToBerlinTime() throws Exception {
        mockMvc.perform(get("/api/to-berlin-time").param("localtime", "23:59:59"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.digitalTime").value("23:59:59"))
                .andExpect(jsonPath("$.berlinTime").value("ORRRRRRROYYRYYRYYRYYYYYY"));
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

}

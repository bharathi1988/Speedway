package com.galvanize.speedway.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.speedway.entities.Racecar;
import com.galvanize.speedway.service.SpeedCarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class SpeedwayIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SpeedCarService speedCarService;

    @Test
    public void addRaceCarsTest() throws Exception {

        Racecar racecar = Racecar.builder()
                .nickName("Ferrari")
                .model("Model123")
                .year(2020)
                .topSpeed(250)
                .owner(1)
                .build();

        mockMvc.perform(post("/api/v1/racecars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(racecar)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nickName").value(racecar.getNickName()))
                .andExpect(jsonPath("$.model").value(racecar.getModel()))
                .andExpect(jsonPath("$.year").value(racecar.getYear()))
                .andExpect(jsonPath("$.topSpeed").value(racecar.getTopSpeed()))
                .andExpect(jsonPath("$.owner").value(racecar.getOwner()));
    }
    @Test
    public void testFindByCarId() throws Exception {

        Racecar racecar = Racecar.builder()
                .nickName("Ferrari")
                .model("Model123")
                .year(2020)
                .topSpeed(250)
                .owner(1)
                .build();

        Racecar expectedCar  = speedCarService.addRaceCar(racecar);

        mockMvc.perform(get("/api/v1/racecars/{carId}",expectedCar.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedCar.getId()))
                .andExpect(jsonPath("$.nickName").value(expectedCar.getNickName()))
                .andExpect(jsonPath("$.model").value(expectedCar.getModel()))
                .andExpect(jsonPath("$.year").value(expectedCar.getYear()))
                .andExpect(jsonPath("$.topSpeed").value(expectedCar.getTopSpeed()))
                .andExpect(jsonPath("$.owner").value(expectedCar.getOwner()));
    }
}

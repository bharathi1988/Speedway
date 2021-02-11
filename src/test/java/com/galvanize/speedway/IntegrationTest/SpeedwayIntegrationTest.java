package com.galvanize.speedway.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.speedway.entities.Driver;
import com.galvanize.speedway.entities.Race;
import com.galvanize.speedway.entities.Racecar;
import com.galvanize.speedway.service.SpeedCarService;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

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

    Long driverId;

    Driver driver;

    @BeforeEach
    public void setup(){
        driver = Driver.builder()
                .firstName("Micheal")
                .lastName("Schumaker")
                .nickName("Mike")
                .age(40)
                .wins(100)
                .losses(4)
                .build();

        driverId = speedCarService.addDriver(driver).getId();
    }

    @Test
    public void addRaceCarsTest() throws Exception {

        Racecar racecar = Racecar.builder()
                .nickName("Ferrari")
                .model("Model123")
                .year(2020)
                .topSpeed(250)
                .owner(driverId)
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
                .owner(driverId)
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

    @Test
    public void testFindAllCars() throws Exception {

        Racecar racecar = Racecar.builder()
                .nickName("Ferrari")
                .model("Model123")
                .year(2020)
                .topSpeed(250)
                .owner(driverId)
                .build();

        Racecar racecar2 = Racecar.builder()
                .nickName("Alpine")
                .model("Model1")
                .year(2021)
                .topSpeed(275)
                .owner(driverId)
                .build();
        Racecar expectedCar1  = speedCarService.addRaceCar(racecar);
        speedCarService.addRaceCar(racecar2);

        mockMvc.perform(get("/api/v1/racecars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].id").value(expectedCar1.getId()))
                .andExpect(jsonPath("$.[0].nickName").value(expectedCar1.getNickName()))
                .andExpect(jsonPath("$.[0].model").value(expectedCar1.getModel()))
                .andExpect(jsonPath("$.[0].year").value(expectedCar1.getYear()))
                .andExpect(jsonPath("$.[0].topSpeed").value(expectedCar1.getTopSpeed()))
                .andExpect(jsonPath("$.[0].owner").value(expectedCar1.getOwner()));
    }

    @Test
    public void addDriverTest() throws Exception{
        mockMvc.perform(post("/api/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(driver)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value(driver.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(driver.getLastName()))
                .andExpect(jsonPath("$.nickName").value(driver.getNickName()))
                .andExpect(jsonPath("$.age").value(driver.getAge()))
                .andExpect(jsonPath("$.wins").value(driver.getWins()))
                .andExpect(jsonPath("$.losses").value(driver.getLosses()));
    }

    @Test
    public void getDriverByIdTest() throws Exception{
        mockMvc.perform(get("/api/v1/drivers/{driverId}", driverId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(driverId))
                .andExpect(jsonPath("$.firstName").value(driver.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(driver.getLastName()))
                .andExpect(jsonPath("$.nickName").value(driver.getNickName()))
                .andExpect(jsonPath("$.age").value(driver.getAge()))
                .andExpect(jsonPath("$.wins").value(driver.getWins()))
                .andExpect(jsonPath("$.losses").value(driver.getLosses()));
    }

    @Test
    public void getAllDriversTest() throws Exception{
        mockMvc.perform(get("/api/v1/drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstName").value(driver.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").value(driver.getLastName()))
                .andExpect(jsonPath("$.[0].nickName").value(driver.getNickName()))
                .andExpect(jsonPath("$.[0].age").value(driver.getAge()))
                .andExpect(jsonPath("$.[0].wins").value(driver.getWins()))
                .andExpect(jsonPath("$.[0].losses").value(driver.getLosses()));

    }

    @Test
    public void addRaceTest() throws Exception {

        Race race = Race.builder()
                .name("Grand Prix III")
                .category("Stock Car")
                .date(LocalDate.now())
                .bestTime("03:36:78")
                .winner(driver)
                .participantsList(Arrays.asList(driver))
                .build();

        mockMvc.perform(post("/api/v1/race")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(race)))
                .andExpect(status().isCreated());
    }
}

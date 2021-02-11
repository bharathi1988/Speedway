package com.galvanize.speedway.controllers;

import com.galvanize.speedway.entities.Driver;
import com.galvanize.speedway.entities.Racecar;
import com.galvanize.speedway.service.SpeedCarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "SpeedWay API")
public class SpeedwayController {
    @Autowired
    SpeedCarService speedCarService;

    @PostMapping("/api/v1/racecars")
    @ResponseStatus(HttpStatus.CREATED)
    public Racecar addRacecar(@RequestBody Racecar racecar) {
        return speedCarService.addRaceCar(racecar);
    }

    @GetMapping("/api/v1/racecars/{carId}")
    public Racecar findById(@PathVariable("carId") int id) {
        return speedCarService.findById(id);
    }

    @GetMapping("/api/v1/racecars")
    public List<Racecar> findAllCars() {
        return speedCarService.findAllCars();
    }

    @PostMapping("/api/v1/drivers")
    @ResponseStatus(HttpStatus.CREATED)
    public Driver addDriver(@RequestBody Driver driver) {
        return speedCarService.addDriver(driver);
    }

    @GetMapping("/api/v1/drivers/{driverId}")
    public Driver findById(@PathVariable("driverId") Long id) {
        return speedCarService.findById(id);
    }

    @GetMapping("/api/v1/drivers")
    public List<Driver> getAllDrivers(){
        return speedCarService.getAllDrivers();
    }

}

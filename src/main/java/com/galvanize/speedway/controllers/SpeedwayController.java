package com.galvanize.speedway.controllers;

import com.galvanize.speedway.entities.Racecar;
import com.galvanize.speedway.service.SpeedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

}

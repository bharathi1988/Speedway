package com.galvanize.speedway.controllers;

import com.galvanize.speedway.entities.Racecar;
import com.galvanize.speedway.service.SpeedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeedwayController {
    @Autowired
    SpeedCarService speedCarService;

    @PostMapping("/api/v1/racecars")
    @ResponseStatus(HttpStatus.CREATED)
    public Racecar addRacecar(@RequestBody Racecar racecar) {
        return speedCarService.addRaceCar(racecar);
    }

}

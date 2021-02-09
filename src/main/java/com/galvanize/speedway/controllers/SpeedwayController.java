package com.galvanize.speedway.controllers;

import com.galvanize.speedway.entities.Racecar;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpeedwayController {

    @PostMapping("/api/v1/racecars")
    @ResponseStatus(HttpStatus.CREATED)
    public Racecar addRacecar(@RequestBody Racecar racecar) {
        return racecar;
    }

}

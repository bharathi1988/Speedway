package com.galvanize.speedway.service;

import com.galvanize.speedway.entities.Racecar;
import com.galvanize.speedway.repository.SpeedCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeedCarService {
    @Autowired
    SpeedCarRepository speedCarRepository;

    public Racecar addRaceCar(Racecar racecar) {
        return speedCarRepository.save(racecar);
    }
}

package com.galvanize.speedway.service;

import com.galvanize.speedway.entities.Driver;
import com.galvanize.speedway.entities.Race;
import com.galvanize.speedway.entities.Racecar;
import com.galvanize.speedway.repository.DriverRepository;
import com.galvanize.speedway.repository.RaceRepository;
import com.galvanize.speedway.repository.SpeedCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeedCarService {
    @Autowired
    SpeedCarRepository speedCarRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RaceRepository raceRepository;

    public Racecar addRaceCar(Racecar racecar) {
        return speedCarRepository.save(racecar);
    }

    public Racecar findById(int id) {
        return speedCarRepository.findById(id).get();
    }

    public List<Racecar> findAllCars() {
        return new ArrayList<>(speedCarRepository.findAll());
    }

    public Driver addDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver findById(Long id) {
        return driverRepository.findById(id).get();
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public Race addRace(Race race) {
        return raceRepository.save(race);
    }
}

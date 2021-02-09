package com.galvanize.speedway.repository;

import com.galvanize.speedway.entities.Racecar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedCarRepository extends CrudRepository<Racecar, Integer> {
}

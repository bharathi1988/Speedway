package com.galvanize.speedway.repository;

import com.galvanize.speedway.entities.Racecar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeedCarRepository extends JpaRepository<Racecar, Integer> {
}

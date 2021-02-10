package com.galvanize.speedway.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Racecar {

    @Id
    @GeneratedValue
    private int id;

    private String nickName;

    private String model;

    private int year;

    private int owner;

    private int topSpeed;

}

package com.galvanize.speedway.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Racecar {

    @Id
    @GeneratedValue
    private int id;

    private String nickName;

    private String model;

    private int year;


    private Long owner;

    private int topSpeed;



}

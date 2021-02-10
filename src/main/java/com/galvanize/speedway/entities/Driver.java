package com.galvanize.speedway.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String nickName;
    private int age;
    private int wins;
    private int losses;

    @OneToMany( fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private List<Racecar> carList;


}

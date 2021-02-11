package com.galvanize.speedway.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Race {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String category;
    private LocalDate date;
    private String bestTime;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Driver winner;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private List<Driver> participantsList;
}

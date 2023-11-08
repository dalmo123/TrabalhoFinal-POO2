package com.poo2.estacionamento.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poo2.estacionamento.state.ParkingLotState;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalSpaces;
    private int availableSpaces;

    @OneToMany
    private List<Vehicle> parkedVehicles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ParkingLotState state;


}

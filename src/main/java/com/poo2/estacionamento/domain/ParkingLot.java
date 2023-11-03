package com.poo2.estacionamento.domain;

import com.poo2.estacionamento.state.ParkingLotState;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalSpaces;
    private int availableSpaces;

    @OneToMany
    private List<Vehicle> parkedVehicles;

    @Enumerated(EnumType.STRING)
    private ParkingLotState state;


}

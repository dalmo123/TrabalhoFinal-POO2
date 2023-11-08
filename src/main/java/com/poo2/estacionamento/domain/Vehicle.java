package com.poo2.estacionamento.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String plateNumber;
    private String model;
    private String color;
    private String nrRodas;


    private Long parkingLotId;

    private Long parkingTicketId;

    public abstract String getType();

}

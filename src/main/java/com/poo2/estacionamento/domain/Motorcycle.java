package com.poo2.estacionamento.domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@DiscriminatorValue("motorcycle")
public class Motorcycle extends Vehicle {

    @ManyToOne
    private ParkingLot parkingLot;

    @ManyToOne
    private ParkingTicket parkingTicket;

    @Override
    public String getType() {
        return "motorcycle";
    }
}

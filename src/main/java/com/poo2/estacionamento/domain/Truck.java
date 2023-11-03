package com.poo2.estacionamento.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("truck")
public class Truck extends Vehicle{

    @ManyToOne
    private ParkingLot parkingLot;

    @ManyToOne
    private ParkingTicket parkingTicket;
    @Override
    public String getType() {
        return "truck";
    }
}

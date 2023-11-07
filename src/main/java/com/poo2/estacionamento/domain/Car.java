package com.poo2.estacionamento.domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@DiscriminatorValue("car")
public class Car extends Vehicle{




    @Override
    public String getType() {
        return "car";
    }
}

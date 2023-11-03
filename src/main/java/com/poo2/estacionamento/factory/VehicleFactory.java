package com.poo2.estacionamento.factory;

import com.poo2.estacionamento.domain.Vehicle;

public interface VehicleFactory {

    Vehicle createVehicle(String type);


}

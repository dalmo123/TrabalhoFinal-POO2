package com.poo2.estacionamento.factory;

import com.poo2.estacionamento.domain.Car;
import com.poo2.estacionamento.domain.Motorcycle;
import com.poo2.estacionamento.domain.Truck;
import com.poo2.estacionamento.domain.Vehicle;
import com.poo2.estacionamento.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactoryImpl implements VehicleFactory{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle createVehicle(String type) {
        return switch (type) {
            case "car" -> vehicleRepository.save(new Car());
            case "motorcycle" ->
                    vehicleRepository.save(new Motorcycle());
            case "truck" -> vehicleRepository.save(new Truck());
            default -> throw new IllegalArgumentException("Tipo de veículo não suportado.");
        };
    }
}


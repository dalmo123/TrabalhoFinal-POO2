package com.poo2.estacionamento.service;

import com.poo2.estacionamento.domain.Vehicle;
import com.poo2.estacionamento.dto.AddVehicleRequest;
import com.poo2.estacionamento.factory.VehicleFactory;
import com.poo2.estacionamento.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleFactory vehicleFactory;


    public Vehicle createVehicle(AddVehicleRequest vehicleRequest) {
        String type = vehicleRequest.getType();
        Vehicle createdVehicle = vehicleFactory.createVehicle(type);

        createdVehicle.setPlateNumber(vehicleRequest.getPlateNumber());
        createdVehicle.setModel(vehicleRequest.getModel());
        createdVehicle.setColor(vehicleRequest.getColor());
        createdVehicle.setNrRodas(vehicleRequest.getNrRodas());

        return vehicleRepository.save(createdVehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void removeVehicleById(Long id) {
        vehicleRepository.deleteById(id);
    }

}

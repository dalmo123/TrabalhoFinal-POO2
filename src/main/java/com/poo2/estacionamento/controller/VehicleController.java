package com.poo2.estacionamento.controller;

import com.poo2.estacionamento.domain.Vehicle;
import com.poo2.estacionamento.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }


    @DeleteMapping("/{id}")
    public void removeVehicle(@PathVariable Long id) {
        vehicleService.removeVehicleById(id);
    }

}

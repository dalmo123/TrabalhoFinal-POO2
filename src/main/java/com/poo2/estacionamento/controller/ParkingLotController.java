package com.poo2.estacionamento.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.poo2.estacionamento.domain.ParkingLot;
import com.poo2.estacionamento.domain.Vehicle;
import com.poo2.estacionamento.dto.AddVehicleRequest;
import com.poo2.estacionamento.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping
    public ResponseEntity<ParkingLot> createParkingLot(@RequestParam int numberOfSpaces) {
        ParkingLot createdParkingLot = parkingLotService.createParkingLot(numberOfSpaces);
        return new ResponseEntity<>(createdParkingLot, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingLot>> getAllParkingLots() {
        List<ParkingLot> parkingLots = parkingLotService.getAllParkingLots();
        return new ResponseEntity<>(parkingLots, HttpStatus.OK);
    }

    @GetMapping("/{parkingLotId}")
    public ResponseEntity<ParkingLot> getParkingLotById(@PathVariable Long parkingLotId) {
        Optional<ParkingLot> parkingLot = parkingLotService.getParkingLotById(parkingLotId);
        return parkingLot.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{parkingLotId}/vehicles")
    public ResponseEntity<List<Vehicle>> getVehiclesInParkingLot(@PathVariable Long parkingLotId) {
        List<Vehicle> vehicles = parkingLotService.getVehiclesInParkingLot(parkingLotId);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }


    @PostMapping("/{id}/vehicles")
    public ResponseEntity<String> addVehicleToParkingLot(
            @PathVariable Long id,
            @RequestBody AddVehicleRequest request
    ) {

        boolean added = parkingLotService.addVehicle(id, request);

        if (added) {
            return ResponseEntity.ok("Vehicle added to parking lot");
        } else {
            return ResponseEntity.badRequest().body("Vehicle could not be added to parking lot");
        }
    }

    @DeleteMapping("/{parkingLotId}/vehicles/{vehicleId}")
    public ResponseEntity<String> removeVehicleFromParkingLot(
            @PathVariable Long parkingLotId,
            @PathVariable Long vehicleId
    ) {
        boolean removed = parkingLotService.removeVehicle(parkingLotId, vehicleId);

        if (removed) {
            return ResponseEntity.ok("Vehicle removed from parking lot");
        } else {
            return ResponseEntity.badRequest().body("Vehicle could not be removed from parking lot");
        }
    }

}



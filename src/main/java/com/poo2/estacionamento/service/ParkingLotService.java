package com.poo2.estacionamento.service;

import com.poo2.estacionamento.domain.ParkingLot;
import com.poo2.estacionamento.domain.Vehicle;
import com.poo2.estacionamento.dto.AddVehicleRequest;
import com.poo2.estacionamento.observer.ConsoleParkingLotObserver;
import com.poo2.estacionamento.observer.ParkingLotObserverComponent;
import com.poo2.estacionamento.repository.ParkingLotRepository;
import com.poo2.estacionamento.repository.VehicleRepository;
import com.poo2.estacionamento.state.ParkingLotState;
import com.poo2.estacionamento.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ParkingLotObserverComponent parkingLotObserverComponent;

    @Autowired
    private State fullState;

    @Autowired
    private State availableState;



    public ParkingLot createParkingLot(int totalSpaces) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setTotalSpaces(totalSpaces);
        parkingLot.setAvailableSpaces(totalSpaces);
        parkingLot.setState(ParkingLotState.FREE);

        ConsoleParkingLotObserver consoleParkingLotObserver = new ConsoleParkingLotObserver();
        parkingLotObserverComponent.addObserver(consoleParkingLotObserver);

        return parkingLotRepository.save(parkingLot);
    }

    public List<ParkingLot> getAllParkingLots() {
        return parkingLotRepository.findAll();
    }

    public Optional<ParkingLot> getParkingLotById(Long parkingLotId) {
        return parkingLotRepository.findById(parkingLotId);
    }


    public List<Vehicle> getVehiclesInParkingLot(Long parkingLotId) {
        Optional<ParkingLot> parkingLot = parkingLotRepository.findById(parkingLotId);

        if (parkingLot.isPresent()) {
            return parkingLot.get().getParkedVehicles();
        } else {
            return List.of();
        }
    }

    public boolean addVehicle(Long parkingLotId, AddVehicleRequest vehicleRequest) {
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findById(parkingLotId);

        if (optionalParkingLot.isPresent()) {
            ParkingLot parkingLot = optionalParkingLot.get();

            if (isParkingLotFull(parkingLot)) {
                return false;
            }

            Vehicle createdVehicle = vehicleService.createVehicle(vehicleRequest);
            parkingLot.getParkedVehicles().add(createdVehicle);
            decrementAvailableSpaces(parkingLot);
            parkingLotRepository.save(parkingLot);

           isParkingLotFull(parkingLot);

            return true;
        }
        return false;
    }

    private boolean isParkingLotFull(ParkingLot parkingLot) {
        int availableSpace = parkingLot.getAvailableSpaces();
        boolean isFull = availableSpace == 0;

        if (isFull && parkingLot.getState() != ParkingLotState.FULL)
            fullState.handleParking(parkingLot);

        return isFull;
    }

    public boolean removeVehicle(Long parkingLotId, Long vehicleId) {
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findById(parkingLotId);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if (optionalParkingLot.isPresent() && optionalVehicle.isPresent()) {
            ParkingLot parkingLot = optionalParkingLot.get();
            Vehicle vehicle = optionalVehicle.get();


            if (parkingLot.getParkedVehicles().contains(vehicle)) {
                parkingLot.getParkedVehicles().remove(vehicle);
                incrementAvailableSpaces(parkingLot);
                parkingLotRepository.save(parkingLot);

                isParkingLotAvailable(parkingLot);
                return true;
            }


        }
        return false;
    }
    private void isParkingLotAvailable(ParkingLot parkingLot) {
        int availableSpace = parkingLot.getAvailableSpaces();
        boolean isFull = availableSpace > 0;

        if (isFull && parkingLot.getState() != ParkingLotState.FREE) {
            availableState.handleParking(parkingLot);
        }

    }

    public void decrementAvailableSpaces(ParkingLot parkingLot) {
        if (parkingLot.getAvailableSpaces() > 0) {
            parkingLot.setAvailableSpaces(parkingLot.getAvailableSpaces() - 1);
            parkingLotObserverComponent.notifyObserversOnVehicleEnter();
        }
    }

    public void incrementAvailableSpaces(ParkingLot parkingLot) {
        if (parkingLot.getAvailableSpaces() < parkingLot.getTotalSpaces()) {
            parkingLot.setAvailableSpaces(parkingLot.getAvailableSpaces() + 1);
            parkingLotObserverComponent.notifyObserversOnVehicleExit();
        }
    }


}





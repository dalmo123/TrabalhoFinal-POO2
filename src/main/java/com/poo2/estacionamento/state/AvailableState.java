package com.poo2.estacionamento.state;

import com.poo2.estacionamento.domain.ParkingLot;
import com.poo2.estacionamento.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailableState implements State{

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Override
    public void handleParking(ParkingLot parkingLot) {
        parkingLot.setState(ParkingLotState.FREE);
        parkingLotRepository.save(parkingLot);
        new LedDisplay().showEmptyMessage();
    }
}

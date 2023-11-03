package com.poo2.estacionamento.state;

import com.poo2.estacionamento.domain.ParkingLot;

public interface State {

    void handleParking(ParkingLot parkingLot);
}

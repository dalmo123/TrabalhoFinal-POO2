package com.poo2.estacionamento.observer;

import com.poo2.estacionamento.domain.Vehicle;

public interface ParkingLotObserver {
    void onVehicleEnter();
    void onVehicleExit();
}
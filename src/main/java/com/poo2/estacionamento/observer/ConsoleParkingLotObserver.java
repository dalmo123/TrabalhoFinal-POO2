package com.poo2.estacionamento.observer;

import org.springframework.stereotype.Component;

@Component
public class ConsoleParkingLotObserver implements ParkingLotObserver{
    @Override
    public void onVehicleEnter() {
        System.out.println("Veiculo entrou no estacionamento");
    }

    @Override
    public void onVehicleExit() {
        System.out.println("Veiculo saiu do estacionamento");
    }
}

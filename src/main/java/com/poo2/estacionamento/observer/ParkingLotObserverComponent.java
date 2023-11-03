package com.poo2.estacionamento.observer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParkingLotObserverComponent {

    private List<ParkingLotObserver> observers = new ArrayList<>();

    public void addObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ParkingLotObserver observer) {
        observers.remove(observer);
    }

    public void notifyObserversOnVehicleEnter() {
        for (ParkingLotObserver observer : observers) {
            observer.onVehicleEnter();
        }
    }

    public void notifyObserversOnVehicleExit() {
        for (ParkingLotObserver observer : observers) {
            observer.onVehicleExit();
        }
    }
}

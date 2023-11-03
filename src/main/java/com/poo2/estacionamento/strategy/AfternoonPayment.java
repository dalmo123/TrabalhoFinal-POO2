package com.poo2.estacionamento.strategy;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AfternoonPayment implements PaymentCalculationStrategy{

    @Override
    public double calculateAmount(long hoursParked) {
        double basePrice = 7.0;
        double pricePerHour = 7.0;
        return basePrice + (hoursParked - 1) * pricePerHour;
    }

    @Override
    public boolean isApplicable(LocalDateTime checkInTime) {
        int hourOfDay = checkInTime.getHour();
        return hourOfDay >= 13 && hourOfDay < 17;
    }
}

package com.poo2.estacionamento.strategy;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EveningPayment implements PaymentCalculationStrategy{

    @Override
    public double calculateAmount(long hoursParked) {
        double basePrice = 10.0;
        double pricePerHour = 10.0;
        return basePrice + (hoursParked - 1) * pricePerHour;
    }

    @Override
    public boolean isApplicable(LocalDateTime checkInTime) {
        int hourOfDay = checkInTime.getHour();
        return hourOfDay >= 17 && hourOfDay < 23;
    }
}

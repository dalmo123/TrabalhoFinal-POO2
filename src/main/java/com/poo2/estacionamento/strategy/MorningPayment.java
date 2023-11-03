package com.poo2.estacionamento.strategy;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MorningPayment implements PaymentCalculationStrategy{
    @Override
    public double calculateAmount(long hoursParked) {
        double basePrice = 5.0;
        double pricePerHour = 5.0;
        return basePrice + (hoursParked - 1) * pricePerHour;
    }

    @Override
    public boolean isApplicable(LocalDateTime checkInTime) {
        int hourOfDay = checkInTime.getHour();
        return hourOfDay >= 9 && hourOfDay < 12;
    }
}

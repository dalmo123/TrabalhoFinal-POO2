package com.poo2.estacionamento.strategy;

import java.time.LocalDateTime;

public interface PaymentCalculationStrategy {
    double calculateAmount(long hoursParked);

    boolean isApplicable(LocalDateTime checkInTime);
}

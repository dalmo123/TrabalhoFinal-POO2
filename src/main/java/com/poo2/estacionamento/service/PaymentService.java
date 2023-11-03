package com.poo2.estacionamento.service;

import com.poo2.estacionamento.adapter.*;
import com.poo2.estacionamento.domain.ParkingTicket;
import com.poo2.estacionamento.repository.ParkingTicketRepository;
import com.poo2.estacionamento.strategy.PaymentCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class PaymentService {

    private final List<PaymentCalculationStrategy> paymentStrategies;

    @Autowired
    public PaymentService(List<PaymentCalculationStrategy> paymentStrategies) {
        this.paymentStrategies = paymentStrategies;
    }

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    public double calculatePayment(Long ticketId) {
        ParkingTicket ticket = parkingTicketRepository.findById(ticketId).orElse(null);
        if (ticket != null) {
            LocalDateTime checkInTime = ticket.getCheckInTime();
            LocalDateTime checkOutTime = LocalDateTime.now();

            if (checkInTime != null) {
                Duration duration = Duration.between(checkInTime, checkOutTime);
                long hoursParked = Math.max(1, duration.toHours());

                for (PaymentCalculationStrategy strategy : paymentStrategies) {
                    if (strategy.isApplicable(checkInTime)) {
                        double amount = strategy.calculateAmount(hoursParked);
                        ticket.setPaid(true);
                        processPayment(amount);
                        parkingTicketRepository.save(ticket);
                        return amount;
                    }
                }
            }
        }
        return 0.0;
    }

    private void processPayment(double paymentAmount) {
        PaymentAdapter paymentAdapter;

        if (isOdd(randomNumber())) {
            paymentAdapter = new PaymentSystemAAdapter(new SystemA());
        } else {
            paymentAdapter = new PaymentSystemBAdapter(new SystemB());
        }

        paymentAdapter.makePayment(paymentAmount);
    }
    private int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    private boolean isOdd(int number) {
        return number % 2 != 0;
    }
}



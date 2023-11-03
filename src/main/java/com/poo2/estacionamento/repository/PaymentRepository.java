package com.poo2.estacionamento.repository;

import com.poo2.estacionamento.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByTicketId(Long ticketId);
}

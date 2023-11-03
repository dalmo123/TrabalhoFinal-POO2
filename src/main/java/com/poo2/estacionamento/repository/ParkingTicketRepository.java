package com.poo2.estacionamento.repository;

import com.poo2.estacionamento.domain.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {

    List<ParkingTicket> findByCheckOutTimeIsNull();
    List<ParkingTicket> findByCheckOutTimeIsNotNull();

}

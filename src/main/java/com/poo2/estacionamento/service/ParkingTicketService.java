package com.poo2.estacionamento.service;

import com.poo2.estacionamento.domain.ParkingLot;
import com.poo2.estacionamento.domain.ParkingTicket;
import com.poo2.estacionamento.domain.Vehicle;
import com.poo2.estacionamento.repository.ParkingLotRepository;
import com.poo2.estacionamento.repository.ParkingTicketRepository;
import com.poo2.estacionamento.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingTicketService{

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ParkingTicketRepository parkingTicketRepository;
    @Autowired
    private ParkingLotRepository parkingLotRepository;


    public Optional<ParkingTicket> createParkingTicket(Long parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElse(null);

        if (parkingLot != null) {
            ParkingTicket newTicket = new ParkingTicket();
            newTicket.setParkingLot(parkingLot);
            newTicket.setCheckInTime(LocalDateTime.now());

            parkingTicketRepository.save(newTicket);
            return Optional.of(newTicket);
        }

        return Optional.empty();
    }


        public String closeParkingTicket(Long ticketId) {
            ParkingTicket ticket = parkingTicketRepository.findById(ticketId).orElse(null);
            if (ticket != null) {
                if (ticket.isPaid()) {
                    ticket.setCheckOutTime(LocalDateTime.now());
                    parkingTicketRepository.save(ticket);
                    return "Ticket closed successfully! Thank you for visiting.";
                } else {
                    return "The ticket has not been paid. Cannot close it.";
                }
            } else {
                return "Ticket not found.";
            }
        }


    public List<ParkingTicket> getAllOpenTickets(){
        return parkingTicketRepository.findByCheckOutTimeIsNull();
    }

    public List<ParkingTicket> getAllPaidTickets() {
        return parkingTicketRepository.findByCheckOutTimeIsNotNull();
    }

}


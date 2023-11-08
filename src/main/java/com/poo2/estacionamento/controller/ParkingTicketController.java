package com.poo2.estacionamento.controller;

import com.poo2.estacionamento.domain.ParkingTicket;
import com.poo2.estacionamento.dto.ParkingTicketDTO;
import com.poo2.estacionamento.service.ParkingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/parking-tickets")
public class ParkingTicketController {

    @Autowired
    private ParkingTicketService parkingTicketService;

    @GetMapping("/open-tickets")
    public ResponseEntity<List<ParkingTicketDTO>> getAllParkingTickets() {
        List<ParkingTicket> parkingTickets = parkingTicketService.getAllOpenTickets();

        List<ParkingTicketDTO> ticketDTOs = new ArrayList<>();
        for (ParkingTicket ticket : parkingTickets) {
            ParkingTicketDTO ticketDTO = new ParkingTicketDTO();
            ticketDTO.setId(ticket.getId());
            ticketDTO.setCheckInTime(ticket.getCheckInTime());
            ticketDTO.setCheckOutTime(ticket.getCheckOutTime());
            ticketDTO.setPaid(ticket.isPaid());

            ticketDTOs.add(ticketDTO);
        }

        return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
    }

    @GetMapping("/paid-tickets")
    public ResponseEntity<List<ParkingTicketDTO>> getAllPaidTickets() {
        List<ParkingTicket> parkingTickets = parkingTicketService.getAllPaidTickets();

        List<ParkingTicketDTO> ticketDTOs = new ArrayList<>();
        for (ParkingTicket ticket : parkingTickets) {
            if (ticket.getCheckOutTime() != null) {
                ParkingTicketDTO ticketDTO = new ParkingTicketDTO();
                ticketDTO.setId(ticket.getId());
                ticketDTO.setCheckInTime(ticket.getCheckInTime());
                ticketDTO.setCheckOutTime(ticket.getCheckOutTime());
                ticketDTO.setPaid(ticket.isPaid());

                ticketDTOs.add(ticketDTO);
            }
        }

        return new ResponseEntity<>(ticketDTOs, HttpStatus.OK);
    }




    @PostMapping("/close/{ticketId}")
    public String closeParkingTicket(@PathVariable Long ticketId) {
        return parkingTicketService.closeParkingTicket(ticketId);
    }

}



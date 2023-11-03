package com.poo2.estacionamento.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ParkingTicketDTO {
    private Long id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean isPaid;
}

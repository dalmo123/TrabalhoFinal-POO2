package com.poo2.estacionamento.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingTicketDTO {
    private Long id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean isPaid;
}

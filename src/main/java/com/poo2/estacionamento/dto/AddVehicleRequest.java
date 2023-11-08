package com.poo2.estacionamento.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AddVehicleRequest {

    private String type;
    private String plateNumber;
    private String model;
    private String color;
    private String nrRodas;

    @JsonIgnore
    private Long parkingLotId;

    @JsonIgnore
    private Long parkingTicketId;
}

package com.poo2.estacionamento.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
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

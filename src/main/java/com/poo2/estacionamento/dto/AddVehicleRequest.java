package com.poo2.estacionamento.dto;

import lombok.Data;

@Data
public class AddVehicleRequest {

    private String type; // Tipo de veículo (car, motorcycle, etc.)
    private String plateNumber;
    private String model;
    private String color;
    private String nrRodas;
}

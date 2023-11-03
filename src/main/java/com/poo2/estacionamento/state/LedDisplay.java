package com.poo2.estacionamento.state;

public class LedDisplay {

    public void showFullMessage() {
        System.out.println("Estacionamento Cheio: Não há mais vagas disponíveis.");
    }

    public void showEmptyMessage() {
        System.out.println("Estacionamento Livre: Vagas disponíveis para estacionar.");
    }
}

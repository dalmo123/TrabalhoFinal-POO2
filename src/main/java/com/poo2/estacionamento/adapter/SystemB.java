package com.poo2.estacionamento.adapter;

public class SystemB implements PaymenteSystemB{
    @Override
    public void pay(double amount) {
        System.out.println("Pagamento de R$" + amount + "SystemB");
    }
}

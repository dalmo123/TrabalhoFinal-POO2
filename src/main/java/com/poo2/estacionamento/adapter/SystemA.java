package com.poo2.estacionamento.adapter;

public class SystemA implements PaymentSystemA {


    @Override
    public void processPayment(double totalAmount) {
        System.out.println("Pagamento de R$" + totalAmount + " processado pelo SystemA");
    }
}

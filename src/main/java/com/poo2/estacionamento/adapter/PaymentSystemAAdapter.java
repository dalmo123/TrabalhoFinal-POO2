package com.poo2.estacionamento.adapter;

public class PaymentSystemAAdapter implements PaymentAdapter{

    private PaymentSystemA systemA;

    public PaymentSystemAAdapter(PaymentSystemA systemA) {
        this.systemA = systemA;
    }
    @Override
    public void makePayment(double amount) {
        systemA.processPayment(amount);
    }
}

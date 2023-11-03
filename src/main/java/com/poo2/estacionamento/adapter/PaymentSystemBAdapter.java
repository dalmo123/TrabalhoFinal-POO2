package com.poo2.estacionamento.adapter;

public class PaymentSystemBAdapter implements PaymentAdapter {
    private PaymenteSystemB systemB;

    public PaymentSystemBAdapter(PaymenteSystemB systemB) {
        this.systemB = systemB;
    }

    @Override
    public void makePayment(double amount) {
        systemB.pay(amount);
    }
}

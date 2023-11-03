package com.poo2.estacionamento.controller;

import com.poo2.estacionamento.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<String> calculatePaymentForTicket(@RequestParam Long ticketId) {
        double amountToPay = paymentService.calculatePayment(ticketId);
        if(amountToPay != 0)
            return ResponseEntity.ok("Valor: " + amountToPay + "\nPagamento efetuado com sucesso!");

        return ResponseEntity.ok("Erro ao pagar, tente novamente!");
    }
}

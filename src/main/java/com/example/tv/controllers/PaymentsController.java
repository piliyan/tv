package com.example.tv.controllers;

import com.example.tv.entities.Payments;
import com.example.tv.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tv/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public List<Payments> getPayments(){
        return paymentsService.getAll();
    }

    @PostMapping
    public Payments addPayment(@RequestBody Payments payment){
        return paymentsService.addNew(payment);
    }

    @DeleteMapping(path = "{paymentId}")
    public void deletePayment(@PathVariable("paymentId") Integer paymentId){
        paymentsService.delete(paymentId);
    }

    @PutMapping(path = "{paymentId}")
    public Payments updateProvider(@PathVariable("paymentId") Integer paymentId,
                                   @RequestBody Payments payment){
        return paymentsService.update(paymentId, payment);
    }
}

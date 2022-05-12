package com.example.tv.services;

import com.example.tv.entities.Payments;
import com.example.tv.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentsService {
    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    public List<Payments> getAll() {
        return paymentsRepository.findAll();
    }

    public Payments addNew(Payments payment) {
        if(payment.getBill() <= 0){
            throw new IllegalStateException("The bill can't be 0 or less");
        }

        paymentsRepository.save(payment);
        return payment;
    }

    public void delete(Integer id) {
        boolean exists = paymentsRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Payment with ID" + id + "does not exists");
        }
        paymentsRepository.deleteById(id);
    }

    @Transactional
    public Payments update(Integer id, Payments newPayment) {
        Payments payment = paymentsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Payment with ID" + id + "does not exists"));

        if(newPayment.getTvChannelContract() != null
                && !Objects.equals(payment.getTvChannelContract(),newPayment.getTvChannelContract())){
            payment.setTvChannelContract(newPayment.getTvChannelContract());
        }

        if(newPayment.getTvPackageContract() != null
                && !Objects.equals(payment.getTvPackageContract(),newPayment.getTvPackageContract())){
            payment.setTvPackageContract(newPayment.getTvPackageContract());
        }

        if(newPayment.getDate() != null
                && !Objects.equals(payment.getDate(),newPayment.getDate())){
            payment.setDate(newPayment.getDate());
        }

        if(newPayment.getBill() != null
                && newPayment.getBill() > 0
                && !Objects.equals(payment.getBill(),newPayment.getBill())){
            payment.setBill(newPayment.getBill());
        }

        return payment;
    }
}

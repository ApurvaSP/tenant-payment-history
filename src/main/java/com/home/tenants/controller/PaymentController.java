package com.home.tenants.controller;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.controller.dtos.PaymentSearchDTO;
import com.home.tenants.controller.dtos.UpdatePaymentDTO;
import com.home.tenants.controller.mapper.PaymentMapper;
import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.exceptions.EntityNotFoundException;
import com.home.tenants.repository.daos.PaymentDAO;
import com.home.tenants.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentMapper paymentMapper;

    @PostMapping("/payments")
    public PaymentDTO create(@Valid @RequestBody PaymentDTO paymentDto) throws ConstraintsViolationException {
        PaymentDAO paymentDAO = paymentService.save(paymentMapper.makePaymentDAO(paymentDto));
        return paymentMapper.makePaymentDTO(paymentDAO);
    }

    @GetMapping("/payments/{paymentId}")
    public PaymentDTO get(@PathVariable Long paymentId) throws EntityNotFoundException {
        PaymentDAO paymentDAO = paymentService.get(paymentId);
        return paymentMapper.makePaymentDTO(paymentDAO);
    }

    @DeleteMapping("/payments/{paymentId}")
    public void delete(@PathVariable long paymentId) throws EntityNotFoundException {
        paymentService.delete(paymentId);
    }

    @PutMapping("/payments/{paymentId}")
    public PaymentDTO update(@PathVariable long paymentId, @Valid @RequestBody UpdatePaymentDTO paymentDto) throws EntityNotFoundException {
        PaymentDAO payment = paymentService.update(paymentId, paymentMapper.makePaymentDAOFromUpdateDTO(paymentDto));
        return paymentMapper.makePaymentDTO(payment);
    }

    @GetMapping("/contracts/{contractId}/payments")
    public PaymentSearchDTO search(@PathVariable long contractId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<PaymentDAO> paymentDAOs = paymentService.search(contractId, startDate, endDate);
        return paymentMapper.makePaymentSearchDTO(paymentDAOs);
    }
}

package com.home.tenants.services;

import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.exceptions.EntityNotFoundException;
import com.home.tenants.repository.PaymentRepository;
import com.home.tenants.repository.daos.PaymentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

    public PaymentDAO save(PaymentDAO payment) throws ConstraintsViolationException {
        try {
            return paymentRepository.save(payment);
        } catch(DataIntegrityViolationException dive) {
            LOG.warn("ConstraintsViolationException while creating a payment: {}", payment, dive);
            throw new ConstraintsViolationException(dive.getMessage());
        }
    }
}

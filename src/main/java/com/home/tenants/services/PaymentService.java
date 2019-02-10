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

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(PaymentService.class);

    @Transactional
    public PaymentDAO save(PaymentDAO paymentDAO) throws ConstraintsViolationException {
        paymentDAO.setId(null);
        try {
            return paymentRepository.save(paymentDAO);
        } catch (DataIntegrityViolationException dive) {
            LOG.warn("ConstraintsViolationException while creating a paymentDAO: {}", paymentDAO, dive);
            throw new ConstraintsViolationException(dive.getMessage());
        }
    }

    @Transactional
    public PaymentDAO get(long paymentId) throws EntityNotFoundException {
        return paymentRepository.findById(paymentId).
                orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + paymentId));

    }

    @Transactional
    public void delete(long paymentId) throws EntityNotFoundException {
        PaymentDAO paymentDAO = get(paymentId);
        paymentDAO.setIsDeleted(true);
    }

    @Transactional
    public PaymentDAO update(long paymentId, PaymentDAO payment) throws EntityNotFoundException {
        PaymentDAO paymentDAO = get(paymentId);
        paymentDAO.setDescription(payment.getDescription());
        paymentDAO.setValue(payment.getValue());
        paymentDAO.setTime(payment.getTime());
        paymentDAO.setUpdatedAt(new Date());
        return paymentDAO;
    }

    public List<PaymentDAO> search(long contractId, Date startDate, Date endDate) {
        return paymentRepository.findByContractIdAndTimeBetween(contractId, startDate, endDate);
    }
}
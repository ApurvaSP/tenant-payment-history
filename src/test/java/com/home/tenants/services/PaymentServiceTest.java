package com.home.tenants.services;

import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.repository.PaymentRepository;
import com.home.tenants.repository.daos.PaymentDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void testCreatePayment() throws ConstraintsViolationException {
        PaymentDAO paymentDTOToCreate = mock(PaymentDAO.class);
        PaymentDAO createdPaymentDAO = mock(PaymentDAO.class);

        when(paymentRepository.save(paymentDTOToCreate)).thenReturn(createdPaymentDAO);
        PaymentDAO actualPaymentDAO = paymentService.save(paymentDTOToCreate);
        assertEquals(actualPaymentDAO, createdPaymentDAO);

    }

    @Test(expected = ConstraintsViolationException.class)
    public void testCreatePaymentShouldFail() throws ConstraintsViolationException {
        PaymentDAO paymentDTOToCreate = mock(PaymentDAO.class);

        when(paymentRepository.save(paymentDTOToCreate)).thenThrow(DataIntegrityViolationException.class);
        paymentService.save(paymentDTOToCreate);

    }


}

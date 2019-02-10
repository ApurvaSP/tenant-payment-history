package com.home.tenants.services;

import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.exceptions.EntityNotFoundException;
import com.home.tenants.repository.PaymentRepository;
import com.home.tenants.repository.daos.PaymentDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    public void testGetPayment() throws EntityNotFoundException {
        PaymentDAO paymentDAO = mock(PaymentDAO.class);
        when(paymentRepository.findById(1234L)).thenReturn(Optional.of(paymentDAO));
        assertEquals(paymentService.get(1234L), paymentDAO);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetPaymentFailure() throws EntityNotFoundException {
        when(paymentRepository.findById(1234L)).thenReturn(Optional.empty());
        paymentService.get(1234L);
    }

    @Test
    public void testDeletePayment() throws EntityNotFoundException {
        PaymentDAO paymentDAO = mock(PaymentDAO.class);
        when(paymentRepository.findById(1234L)).thenReturn(Optional.of(paymentDAO));
        paymentService.delete(1234L);
        verify(paymentDAO).setIsDeleted(true);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeletePaymentFailure() throws EntityNotFoundException {
        when(paymentRepository.findById(1234L)).thenReturn(Optional.empty());
        paymentService.delete(1234L);
    }

    @Test
    public void testUpdatePayment() throws EntityNotFoundException {
        PaymentDAO paymentDAO = mock(PaymentDAO.class);
        Date current = new Date();
        when(paymentDAO.getDescription()).thenReturn("Rent paid");
        when(paymentDAO.getValue()).thenReturn(1);
        when(paymentDAO.getTime()).thenReturn(current);
        when(paymentRepository.findById(1234L)).thenReturn(Optional.of(paymentDAO));
        paymentService.update(1234L, paymentDAO);
        verify(paymentDAO).setDescription("Rent paid");
        verify(paymentDAO).setValue(1);
        verify(paymentDAO).setTime(current);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testUpdatePaymentFailure() throws EntityNotFoundException {
        when(paymentRepository.findById(1234L)).thenReturn(Optional.empty());
        paymentService.update(1234L, any(PaymentDAO.class));
    }
}

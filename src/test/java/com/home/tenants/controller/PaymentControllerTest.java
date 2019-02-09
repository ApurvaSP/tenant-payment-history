package com.home.tenants.controller;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.controller.mapper.PaymentMapper;
import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.exceptions.EntityNotFoundException;
import com.home.tenants.repository.daos.PaymentDAO;
import com.home.tenants.services.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    public void testCreatePayment() throws ConstraintsViolationException {

        PaymentDTO requestPaymentDTO = mock(PaymentDTO.class);
        PaymentDAO toBeCreatedPaymentDAO = mock(PaymentDAO.class);
        PaymentDAO createdPaymentDAO = mock(PaymentDAO.class);
        PaymentDTO responsePaymentDTO = mock(PaymentDTO.class);

        when(paymentMapper.makePaymentDAO(requestPaymentDTO)).thenReturn(toBeCreatedPaymentDAO);
        when(paymentService.save(toBeCreatedPaymentDAO)).thenReturn(createdPaymentDAO);
        when(paymentMapper.makePaymentDTO(createdPaymentDAO)).thenReturn(responsePaymentDTO);

        PaymentDTO actualPaymentDTO = paymentController.create(requestPaymentDTO);
        assertEquals(actualPaymentDTO, responsePaymentDTO);
    }

    @Test(expected = ConstraintsViolationException.class)
    public void testCreatePaymentFailure() throws ConstraintsViolationException {
        PaymentDTO requestPaymentDTO = mock(PaymentDTO.class);
        PaymentDAO toBeCreatedPaymentDAO = mock(PaymentDAO.class);

        when(paymentMapper.makePaymentDAO(requestPaymentDTO)).thenReturn(toBeCreatedPaymentDAO);
        when(paymentService.save(toBeCreatedPaymentDAO)).thenThrow(ConstraintsViolationException.class);
        paymentController.create(requestPaymentDTO);
    }

    @Test
    public void testGetPayment() throws EntityNotFoundException {
        PaymentDAO existingPaymentDAO = mock(PaymentDAO.class);
        PaymentDTO expectedPaymentDTO = mock(PaymentDTO.class);

        when(paymentService.get(1234L)).thenReturn(existingPaymentDAO);
        when(paymentMapper.makePaymentDTO(existingPaymentDAO)).thenReturn(expectedPaymentDTO);

        PaymentDTO actualPaymentDTO = paymentController.get(1234L);
        assertEquals(actualPaymentDTO, expectedPaymentDTO);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetPaymentFailure() throws EntityNotFoundException {
        when(paymentService.get(1234L)).thenThrow(EntityNotFoundException.class);
        paymentController.get(1234L);
    }

    @Test
    public void testDeletePayment() throws EntityNotFoundException {
        paymentController.delete(1234L);
        verify(paymentService).delete(1234L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testDeletePaymentFailure() throws EntityNotFoundException {
        doThrow(EntityNotFoundException.class).when(paymentService).delete(1234L);
        paymentController.delete(1234L);
    }

}

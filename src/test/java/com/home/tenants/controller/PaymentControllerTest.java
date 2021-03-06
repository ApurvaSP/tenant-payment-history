package com.home.tenants.controller;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.controller.dtos.PaymentSearchDTO;
import com.home.tenants.controller.dtos.UpdatePaymentDTO;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void testUpdatePayment() throws EntityNotFoundException {
        long paymentId = 1;
        UpdatePaymentDTO requestUpdatePaymentDTO = mock(UpdatePaymentDTO.class);
        PaymentDAO toBeCreatedPaymentDAO = mock(PaymentDAO.class);
        PaymentDAO updatedPaymentDAO = mock(PaymentDAO.class);
        PaymentDTO responsePaymentDTO = mock(PaymentDTO.class);

        when(paymentMapper.makePaymentDAOFromUpdateDTO(requestUpdatePaymentDTO)).thenReturn(toBeCreatedPaymentDAO);
        when(paymentService.update(paymentId, toBeCreatedPaymentDAO)).thenReturn(updatedPaymentDAO);
        when(paymentMapper.makePaymentDTO(updatedPaymentDAO)).thenReturn(responsePaymentDTO);

        PaymentDTO actualPaymentDTO = paymentController.update(paymentId, requestUpdatePaymentDTO);
        assertEquals(actualPaymentDTO, responsePaymentDTO);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testUpdatePaymentFailure() throws EntityNotFoundException {
        long paymentId = 1;
        UpdatePaymentDTO requestUpdatePaymentDTO = mock(UpdatePaymentDTO.class);
        PaymentDAO toBeCreatedPaymentDAO = mock(PaymentDAO.class);

        when(paymentMapper.makePaymentDAOFromUpdateDTO(requestUpdatePaymentDTO)).thenReturn(toBeCreatedPaymentDAO);
        when(paymentService.update(paymentId, toBeCreatedPaymentDAO)).thenThrow(EntityNotFoundException.class);

        paymentController.update(paymentId, requestUpdatePaymentDTO);
    }

    @Test
    public void testSearchPayment() {
        long contractId = 1;
        Date current = new Date();
        List<PaymentDAO> paymentDAOs = new ArrayList<>();
        paymentDAOs.add(new PaymentDAO(1L, 12L, 40, "Rent paid", new Date(), false));
        PaymentSearchDTO expectedSearchDTO = new PaymentSearchDTO(paymentDAOs);
        when(paymentService.search(contractId, current, current)).thenReturn(paymentDAOs);
        when(paymentMapper.makePaymentSearchDTO(paymentDAOs)).thenReturn(expectedSearchDTO);

        PaymentSearchDTO searchDTO = paymentController.search(contractId, current, current);
        assertEquals(searchDTO.getItems(), paymentDAOs);
        assertEquals(searchDTO.getSum().longValue(), 40L);
    }

    @Test
    public void testSearchPaymentInvalidContractId() {
        long contractId = 1;
        Date current = new Date();
        List<PaymentDAO> paymentDAOs = new ArrayList<>();
        PaymentSearchDTO expectedSearchDTO = new PaymentSearchDTO(paymentDAOs);
        when(paymentService.search(contractId, current, current)).thenReturn(paymentDAOs);
        when(paymentMapper.makePaymentSearchDTO(paymentDAOs)).thenReturn(expectedSearchDTO);

        PaymentSearchDTO searchDTO = paymentController.search(contractId, current, current);
        assertEquals(searchDTO.getItems().size(), 0);
        assertEquals(searchDTO.getSum().longValue(), 0);
    }

    @Test
    public void testSearchPaymentTimeNotBetweenStartAndEndDate() {
        long contractId = 1;
        Date current = new Date();
        List<PaymentDAO> paymentDAOs = new ArrayList<>();
        PaymentSearchDTO expectedSearchDTO = new PaymentSearchDTO(paymentDAOs);
        when(paymentService.search(contractId, current, current)).thenReturn(paymentDAOs);
        when(paymentMapper.makePaymentSearchDTO(paymentDAOs)).thenReturn(expectedSearchDTO);

        PaymentSearchDTO searchDTO = paymentController.search(contractId, current, current);
        assertEquals(searchDTO.getItems().size(), 0);
        assertEquals(searchDTO.getSum().longValue(), 0);
    }

}

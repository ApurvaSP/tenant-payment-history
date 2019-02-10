package com.home.tenants.services;

import com.home.tenants.TenantsApplication;
import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.repository.daos.PaymentDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TenantsApplication.class)
public class PaymentServiceIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    private Long contractId = 123L;
    private Date current = new Date();

    @Before
    public void setUp() throws ConstraintsViolationException {
        paymentService.save(testPaymentDAO(contractId, -2000, "Rent to be paid", current));
        paymentService.save(testPaymentDAO(contractId, 1000, "Rent paid", current));
        paymentService.save(testPaymentDAO(contractId, 500, "Rent paid", current));
    }

    @Test
    public void testSearch() {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.add(Calendar.DATE, 5);

        List<PaymentDAO> paymentDAOs = paymentService.search(contractId, current, c.getTime());
        assertNotNull(paymentDAOs);
        assertEquals(paymentDAOs.size(), 3);

        assertEquals(paymentDAOs.stream().mapToLong(dao -> dao.getValue()).sum(), -500);
    }

    @Test
    public void testSearchWithNonInvalidContractId() {
        Calendar c = Calendar.getInstance();
        c.setTime(current);
        c.add(Calendar.DATE, 5);

        List<PaymentDAO> paymentDAOs = paymentService.search(12, current, c.getTime());
        assertNotNull(paymentDAOs);
        assertEquals(paymentDAOs.size(), 0);
    }

    @Test
    public void testSearchInUnBoundDateRange() {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(current);
        startDate.add(Calendar.DATE, -15);

        Calendar endDate = Calendar.getInstance();
        endDate.setTime(current);
        endDate.add(Calendar.DATE, -5);

        List<PaymentDAO> paymentDAOs = paymentService.search(contractId, startDate.getTime(), endDate.getTime());
        assertNotNull(paymentDAOs);
        assertEquals(paymentDAOs.size(), 0);
    }

    private PaymentDAO testPaymentDAO(Long contractId, Integer value, String description, Date date) {
        PaymentDAO payment = new PaymentDAO(null, contractId, value, description, date, false);
        return payment;
    }
}

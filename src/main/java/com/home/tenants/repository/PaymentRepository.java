package com.home.tenants.repository;

import com.home.tenants.repository.daos.PaymentDAO;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PaymentRepository extends CrudRepository<PaymentDAO, Long> {

    List<PaymentDAO> findByContractIdAndTimeBetween(Long contractId, Date startDate, Date endDate);

}

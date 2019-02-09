package com.home.tenants.repository;

import com.home.tenants.repository.daos.PaymentDAO;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentDAO, Long> {

}

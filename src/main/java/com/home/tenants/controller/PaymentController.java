package com.home.tenants.controller;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.controller.mapper.PaymentMapper;
import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.repository.daos.PaymentDAO;
import com.home.tenants.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private PaymentMapper paymentMapper;

	@PostMapping
	public PaymentDTO create(@Valid @RequestBody PaymentDTO paymentDto) throws ConstraintsViolationException {
		PaymentDAO paymentDAO = paymentService.save(paymentMapper.makePaymentDO(paymentDto));
		PaymentDTO paymentDTO = paymentMapper.makePaymentDTO(paymentDAO);
		return paymentDTO;
	}
}

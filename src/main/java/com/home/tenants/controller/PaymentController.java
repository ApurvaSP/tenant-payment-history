package com.home.tenants.controller;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.controller.dtos.UpdatePaymentDTO;
import com.home.tenants.controller.mapper.PaymentMapper;
import com.home.tenants.exceptions.ConstraintsViolationException;
import com.home.tenants.exceptions.EntityNotFoundException;
import com.home.tenants.repository.daos.PaymentDAO;
import com.home.tenants.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
		PaymentDAO payment = paymentService.save(paymentMapper.makePaymentDAO(paymentDto));
		return paymentMapper.makePaymentDTO(payment);
	}

	@GetMapping("/{paymentId}")
	public PaymentDTO get(@PathVariable Long paymentId) throws EntityNotFoundException {
		PaymentDAO payment = paymentService.get(paymentId);
		return paymentMapper.makePaymentDTO(payment);
	}

	@DeleteMapping("/{paymentId}")
	public void delete(@PathVariable long paymentId) throws EntityNotFoundException {
		paymentService.delete(paymentId);
	}

	@PutMapping("/{paymentId}")
	public PaymentDTO update(@PathVariable long paymentId, @Valid @RequestBody UpdatePaymentDTO paymentDto) throws EntityNotFoundException {
		PaymentDAO payment = paymentService.update(paymentId, paymentMapper.makePaymentDAOFromUpdateDTO(paymentDto));
		return paymentMapper.makePaymentDTO(payment);
	}
}

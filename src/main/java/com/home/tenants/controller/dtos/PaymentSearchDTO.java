package com.home.tenants.controller.dtos;

import com.home.tenants.repository.daos.PaymentDAO;

import java.util.List;

public class PaymentSearchDTO {
    private List<PaymentDAO> items;

    public PaymentSearchDTO(List<PaymentDAO> items) {
        this.items = items;
    }

    public Long getSum() {
        return items.stream().mapToLong(dto ->
                dto.getValue()).sum();
    }

    public List<PaymentDAO> getItems() {
        return items;
    }
}

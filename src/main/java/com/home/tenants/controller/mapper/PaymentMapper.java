package com.home.tenants.controller.mapper;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.repository.daos.PaymentDAO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDAO makePaymentDO(PaymentDTO paymentDto) {
        PaymentDAO paymentDAO = new PaymentDAO(
                paymentDto.getContractId(),
                paymentDto.getValue(),
                paymentDto.getDescription(),
                paymentDto.getTime(),
                paymentDto.getCreatedAt(),
                paymentDto.getUpdatedAt(),
                paymentDto.getIsImported()
        );
        return paymentDAO;
    }

    public PaymentDTO makePaymentDTO(PaymentDAO paymentDAO) {

        PaymentDTO paymentDTO = new PaymentDTO(
                paymentDAO.getId(),
                paymentDAO.getContractId(),
                paymentDAO.getValue(),
                paymentDAO.getDescription(),
                paymentDAO.getTime(),
                paymentDAO.getCreatedAt(),
                paymentDAO.getUpdatedAt(),
                paymentDAO.getIsImported()
        );
        return paymentDTO;
    }
}

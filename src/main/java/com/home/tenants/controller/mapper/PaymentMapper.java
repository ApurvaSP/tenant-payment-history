package com.home.tenants.controller.mapper;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.controller.dtos.UpdatePaymentDTO;
import com.home.tenants.repository.daos.PaymentDAO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDAO makePaymentDAO(PaymentDTO paymentDto) {
        PaymentDAO paymentDAO = new PaymentDAO(
                paymentDto.getId(),
                paymentDto.getContractId(),
                paymentDto.getValue(),
                paymentDto.getDescription(),
                paymentDto.getTime(),
                paymentDto.getIsImported()
        );
        return paymentDAO;
    }

    public PaymentDAO makePaymentDAOFromUpdateDTO(UpdatePaymentDTO paymentDto) {
        PaymentDAO paymentDAO = new PaymentDAO(
                paymentDto.getValue(),
                paymentDto.getDescription(),
                paymentDto.getTime()
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
                paymentDAO.getIsImported(),
                paymentDAO.getIsDeleted()
        );
        return paymentDTO;
    }
}

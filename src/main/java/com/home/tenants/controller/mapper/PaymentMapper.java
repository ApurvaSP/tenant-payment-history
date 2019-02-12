package com.home.tenants.controller.mapper;

import com.home.tenants.controller.dtos.PaymentDTO;
import com.home.tenants.controller.dtos.PaymentSearchDTO;
import com.home.tenants.controller.dtos.UpdatePaymentDTO;
import com.home.tenants.repository.daos.PaymentDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper {

    public PaymentDAO makePaymentDAO(PaymentDTO paymentDto) {
        return new PaymentDAO(
                paymentDto.getId(),
                paymentDto.getContractId(),
                paymentDto.getValue(),
                paymentDto.getDescription(),
                paymentDto.getTime(),
                paymentDto.getIsImported()
        );
    }

    public PaymentDAO makePaymentDAOFromUpdateDTO(UpdatePaymentDTO paymentDto) {
        return new PaymentDAO(
                paymentDto.getValue(),
                paymentDto.getDescription(),
                paymentDto.getTime()
        );
    }

    public PaymentDTO makePaymentDTO(PaymentDAO paymentDAO) {

        return new PaymentDTO(
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
    }

    public PaymentSearchDTO makePaymentSearchDTO(List<PaymentDAO> paymentDAOs) {
        return new PaymentSearchDTO(paymentDAOs);
    }
}

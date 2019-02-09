package com.home.tenants.controller.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {

    private PaymentDTO() {

    }

    public PaymentDTO(Long id, Long contractId, Integer value, String description, Date time, Date createdAt, Date updatedAt, Boolean isImported, Boolean isDeleted) {
        this.id = id;
        this.contractId = contractId;
        this.value = value;
        this.description = description;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isImported = isImported;
        this.isDeleted = isDeleted;
    }

    private Long id;

    @NotNull
    private Long contractId;
    private Integer value;

    @NotBlank
    private String description;

    private Date time;
    private Date createdAt;
    private Date updatedAt;

    private Boolean isImported;
    private Boolean isDeleted;


    public Date getTime() {
        return time;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getIsImported() {
        return isImported;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public Long getId() {
        return id;
    }

    public Long getContractId() {
        return contractId;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}

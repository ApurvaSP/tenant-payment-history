package com.home.tenants.controller.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePaymentDTO {

    private UpdatePaymentDTO() {

    }

    public UpdatePaymentDTO(Integer value, String description, Date time) {
        this.value = value;
        this.description = description;
        this.time = time;
    }

    @NotNull
    private Integer value;

    @NotBlank
    private String description;

    private Date time;

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Date getTime() {
        return time;
    }
}

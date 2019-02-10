package com.home.tenants.controller.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdatePaymentDTO {

    @NotNull
    private Integer value;

    @NotBlank
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
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

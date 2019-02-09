package com.home.tenants.repository.daos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "payment"
)
public class PaymentDAO {

    public PaymentDAO(Long id, Long contractId, Integer value, String description, Date time, Date createdAt, Date updatedAt, Boolean isImported) {
        this.id = id;
        this.contractId = contractId;
        this.value = value;
        this.description = description;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isImported = isImported;
    }

    public PaymentDAO(Long contractId, Integer value, String description, Date time, Date createdAt, Date updatedAt, Boolean isImported) {
        this(null, contractId, value, description, time, createdAt, updatedAt, isImported);
    }

    private PaymentDAO() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contractId;

    @Column(nullable = false)
    private Integer value;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date time;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updatedAt;

    @Column(nullable = false)
    private Boolean isImported = false;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getIsImported() {
        return isImported;
    }

    public void setgetIsImported(Boolean imported) {
        isImported = imported;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}

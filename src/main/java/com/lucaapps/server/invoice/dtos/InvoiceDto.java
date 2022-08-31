package com.lucaapps.server.invoice.dtos;

import com.lucaapps.server.invoice.entities.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceDto {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime paymentDue;

    public InvoiceDto() {}

    public InvoiceDto(Long id, String description, LocalDateTime createdAt, LocalDateTime paymentDue) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
        this.paymentDue = paymentDue;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(LocalDateTime paymentDue) {
        this.paymentDue = paymentDue;
    }


}

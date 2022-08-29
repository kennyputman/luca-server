package com.lucaapps.server.invoice.dtos;

import com.lucaapps.server.invoice.entities.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceWithItemsDto {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime paymentDue;
    private BigDecimal totalCost;
    private List<Item> items;

    public InvoiceWithItemsDto() {}

    public InvoiceWithItemsDto(Long id, String description, LocalDateTime createdAt, LocalDateTime paymentDue,
                               BigDecimal totalCost, List<Item> items) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
        this.paymentDue = paymentDue;
        this.totalCost = totalCost;
        this.items = items;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(LocalDateTime paymentDue) {
        this.paymentDue = paymentDue;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

package com.lucaapps.server.invoice.entities;

import com.lucaapps.server.common.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "payment_due")
    private LocalDateTime paymentDue;

    @OneToMany(mappedBy = "invoice",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public Invoice() {}

    public Invoice(String description, LocalDateTime paymentDue, List<Item> items) {
        this.description = description;
        this.paymentDue = paymentDue;
        this.items = items;
    }

    public BigDecimal getTotalCost(){
        return this.items.stream().map( i -> i.sum()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

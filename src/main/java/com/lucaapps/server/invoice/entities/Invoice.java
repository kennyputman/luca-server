package com.lucaapps.server.invoice.entities;

import com.lucaapps.server.shared.BaseEntity;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "invoice")
    private List<Item> items;

    public Invoice() {}

    public Invoice(String description, LocalDateTime paymentDue) {
        this.description = description;
        this.paymentDue = paymentDue;
        this.items = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}

package com.lucaapps.server.invoice.entities;

import com.lucaapps.server.common.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Item(){};

    public Item(String description, BigDecimal price, int quantity, Invoice invoice) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.invoice = invoice;
    }

    public void setInvoice(Invoice invoice){
        this.invoice = invoice;
    }

    public BigDecimal sum(){
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

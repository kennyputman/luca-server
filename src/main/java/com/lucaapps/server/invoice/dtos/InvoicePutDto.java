package com.lucaapps.server.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucaapps.server.invoice.entities.Item;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class InvoicePutDto {

    @NotNull
    private Long id;

    @NotBlank(message = "description must be included")
    @JsonProperty("description")
    private String description;

    @NotNull
    @JsonProperty("paymentDue")
    private LocalDateTime paymentDue;



    @JsonProperty("items")
    private List<Item> items;

    public InvoicePutDto(){}

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
        return "InvoicePostDto{" +
                "description='" + description + '\'' +
                ", paymentDue=" + paymentDue +
                ", items=" + items +
                '}';
    }
}

package com.lucaapps.server.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class InvoicePostDto {

    @NotBlank(message = "description must be included")
    @JsonProperty("description")
    private String description;

    @NotBlank
    @JsonProperty("paymentDue")
    private LocalDateTime paymentDue;


    public InvoicePostDto(){}

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
}

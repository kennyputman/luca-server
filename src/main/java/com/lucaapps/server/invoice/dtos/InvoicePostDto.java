package com.lucaapps.server.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class InvoicePostDto {

    @NotNull
    @JsonProperty("description")
    private String description;


    public InvoicePostDto(){}

    public InvoicePostDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.lucaapps.server.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class InvoicePostDto {

    @NotBlank(message = "description must be included")
    @JsonProperty("description")
    private String description;


    public InvoicePostDto(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

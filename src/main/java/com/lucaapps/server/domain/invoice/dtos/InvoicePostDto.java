package com.lucaapps.server.domain.invoice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucaapps.server.domain.invoice.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InvoicePostDto {

    @NotBlank(message = "description must be included")
    @JsonProperty("description")
    private String description;

    @NotNull
    @JsonProperty("paymentDue")
    private LocalDateTime paymentDue;

    @JsonProperty("items")
    private List<Item> items;

}

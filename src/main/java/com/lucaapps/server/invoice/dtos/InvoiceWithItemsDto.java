package com.lucaapps.server.invoice.dtos;

import com.lucaapps.server.invoice.entities.Item;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InvoiceWithItemsDto {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime paymentDue;
    private BigDecimal totalCost;
    private List<Item> items;

}

package com.lucaapps.server.invoice.dtos;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class InvoiceDto {

    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime paymentDue;

}

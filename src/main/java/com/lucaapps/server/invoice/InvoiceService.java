package com.lucaapps.server.invoice;

import com.lucaapps.server.invoice.dtos.InvoicePostDto;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<Invoice> getAllInvoices();

    Optional<Invoice> getInvoiceById(Long id);

    Invoice addNewInvoice(InvoicePostDto invoicePostDto);
}

package com.lucaapps.server.invoice.services;

import com.lucaapps.server.invoice.dtos.InvoiceDto;
import com.lucaapps.server.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.invoice.entities.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<InvoiceDto> getAllInvoices();

    Optional<Invoice> getInvoiceById(Long id);

    Invoice addNewInvoice(InvoicePostDto invoicePostDto);

    void deleteInvoice(Long invoiceId);

    InvoiceWithItemsDto getInvoiceWithItems(Long id);
}

package com.lucaapps.server.domain.invoice.services;

import com.lucaapps.server.domain.invoice.dtos.InvoiceDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePutDto;
import com.lucaapps.server.domain.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.domain.invoice.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDto> getAllInvoices();

    Invoice getInvoiceById(Long id);

    InvoiceWithItemsDto addNewInvoice(InvoicePostDto invoicePostDto);

    void deleteInvoice(Long invoiceId);

    InvoiceWithItemsDto getInvoiceWithItems(Long id);

    InvoiceWithItemsDto updateInvoice(InvoicePutDto updatedInvoice);
}

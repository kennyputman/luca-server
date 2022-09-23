package com.lucaapps.server.domain.invoice.services;

import com.lucaapps.server.domain.invoice.dtos.InvoiceDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePutDto;
import com.lucaapps.server.domain.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.domain.invoice.entities.Invoice;
import com.lucaapps.server.domain.user.entities.AppUser;

import java.util.List;

public interface InvoiceService {
    List<InvoiceDto> getAllInvoices(AppUser authUser);


    InvoiceWithItemsDto addNewInvoice(InvoicePostDto invoicePostDto, AppUser authUser);

    void deleteInvoice(Long invoiceId, AppUser authUser);

    InvoiceWithItemsDto getInvoiceWithItems(Long id, AppUser authUser);

    InvoiceWithItemsDto updateInvoice(InvoicePutDto updatedInvoice, AppUser authUser);
}

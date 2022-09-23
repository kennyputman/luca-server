package com.lucaapps.server.domain.invoice.services;

import com.lucaapps.server.domain.user.entities.AppUser;
import com.lucaapps.server.exception.AppException;
import com.lucaapps.server.exception.Error;
import com.lucaapps.server.domain.invoice.dtos.InvoiceDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePutDto;
import com.lucaapps.server.domain.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.domain.invoice.entities.Invoice;
import com.lucaapps.server.domain.invoice.entities.Item;
import com.lucaapps.server.domain.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public List<InvoiceDto> getAllInvoices(AppUser authUser) {
        List<Invoice> invoices = this.invoiceRepository.findInvoicesByUserId(authUser.getId());
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice i : invoices) {
            invoiceDtos.add(
                    InvoiceDto.builder()
                            .id(i.getId())
                            .description(i.getDescription())
                            .createdAt(i.getCreatedAt())
                            .paymentDue(i.getPaymentDue())
                            .build()
            );
        }
        return invoiceDtos;
    }


    @Override
    @Transactional
    public InvoiceWithItemsDto addNewInvoice(InvoicePostDto invoicePostDto, AppUser authUser) {

        Invoice mappedInvoice = new Invoice(invoicePostDto.getDescription(), invoicePostDto.getPaymentDue(),
                invoicePostDto.getItems(), authUser);
        for (Item item : mappedInvoice.getItems()) {
            item.setInvoice(mappedInvoice);
        }

        Invoice createdInvoice = this.invoiceRepository.save(mappedInvoice);
        return mapInvoiceToInvoiceWithItemsDto(createdInvoice);
    }

    @Override
    @Transactional
    public InvoiceWithItemsDto updateInvoice(InvoicePutDto updatedInvoice, AppUser appUser) {
        Optional<Invoice> exists = this.invoiceRepository.findById(updatedInvoice.getId());

        if (exists.isEmpty()) {
            throw new IllegalArgumentException("Invoice of id: " + updatedInvoice.getId() + " not found");
        }

        Invoice invoice = exists.get();

        if(invoice.getAppUser().getId() != appUser.getId()){
            throw new AppException(Error.FORBIDDEN);
        }

        invoice.setDescription(updatedInvoice.getDescription());
        invoice.setPaymentDue(updatedInvoice.getPaymentDue());
        invoice.setItems(updatedInvoice.getItems());

        for (Item item : invoice.getItems()) {
            item.setInvoice(invoice);
        }

        Invoice createdInvoice = this.invoiceRepository.save(invoice);
        return mapInvoiceToInvoiceWithItemsDto(createdInvoice);
    }

    @Override
    @Transactional
    public void deleteInvoice(Long invoiceId, AppUser authUser) {
        Invoice invoice = this.invoiceRepository.findById(invoiceId).orElseThrow(() ->
                new AppException(Error.INVOICE_NOT_FOUND));

        if(invoice.getAppUser().getId() != authUser.getId()){
            throw new AppException(Error.FORBIDDEN);
        }
        this.invoiceRepository.delete(invoice);
    }

    @Override
    public InvoiceWithItemsDto getInvoiceWithItems(Long id, AppUser authUser) {
        Invoice invoice = this.invoiceRepository.findById(id)
                .orElseThrow(() -> new AppException(Error.INVOICE_NOT_FOUND));

        if(invoice.getAppUser().getId() != authUser.getId()){
            throw new AppException(Error.FORBIDDEN);
        }
        return mapInvoiceToInvoiceWithItemsDto(invoice);
    }

    private InvoiceWithItemsDto mapInvoiceToInvoiceWithItemsDto(Invoice i) {
        return InvoiceWithItemsDto.builder()
                .id(i.getId())
                .createdAt(i.getCreatedAt())
                .description(i.getDescription())
                .paymentDue(i.getPaymentDue())
                .totalCost(i.getTotalCost())
                .items(i.getItems())
                .build();
    }

}
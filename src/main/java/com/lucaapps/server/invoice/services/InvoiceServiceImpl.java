package com.lucaapps.server.invoice.services;

import com.lucaapps.server.exception.AppException;
import com.lucaapps.server.exception.Error;
import com.lucaapps.server.invoice.dtos.InvoiceDto;
import com.lucaapps.server.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.invoice.dtos.InvoicePutDto;
import com.lucaapps.server.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.invoice.entities.Invoice;
import com.lucaapps.server.invoice.entities.Item;
import com.lucaapps.server.invoice.repository.InvoiceRepository;
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
    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoices = this.invoiceRepository.findAll();
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
    public Invoice getInvoiceById(Long id) {

        Invoice found = this.invoiceRepository.findById(id)
                .orElseThrow(() -> new AppException(Error.INVOICE_NOT_FOUND));
        return found;
    }

    @Override
    @Transactional
    public InvoiceWithItemsDto addNewInvoice(InvoicePostDto invoicePostDto) {

        Invoice mappedInvoice = new Invoice(invoicePostDto.getDescription(), invoicePostDto.getPaymentDue(), invoicePostDto.getItems());
        for (Item item : mappedInvoice.getItems()) {
            item.setInvoice(mappedInvoice);
        }

        Invoice createdInvoice = this.invoiceRepository.save(mappedInvoice);

        InvoiceWithItemsDto invoice = InvoiceWithItemsDto.builder()
                .id(createdInvoice.getId())
                .createdAt(createdInvoice.getCreatedAt())
                .description(createdInvoice.getDescription())
                .paymentDue(createdInvoice.getPaymentDue())
                .totalCost(createdInvoice.getTotalCost())
                .items(createdInvoice.getItems())
                .build();

        return invoice;
    }

    @Override
    public InvoiceWithItemsDto updateInvoice(InvoicePutDto updatedInvoice) {
        Optional<Invoice> exists = this.invoiceRepository.findById(updatedInvoice.getId());

        if (exists.isEmpty()) {
            throw new IllegalArgumentException("Invoice of id: " + updatedInvoice.getId() + " not found");
        }

        Invoice invoice = exists.get();
        invoice.setDescription(updatedInvoice.getDescription());
        invoice.setPaymentDue(updatedInvoice.getPaymentDue());
        invoice.setItems(updatedInvoice.getItems());

        for (Item item : invoice.getItems()) {
            item.setInvoice(invoice);
        }

        Invoice createdInvoice = this.invoiceRepository.save(invoice);

        InvoiceWithItemsDto invoiceDto = new InvoiceWithItemsDto(
                createdInvoice.getId(), createdInvoice.getDescription(),
                createdInvoice.getCreatedAt(), createdInvoice.getPaymentDue(),
                createdInvoice.getTotalCost(), createdInvoice.getItems());
        return invoiceDto;
    }

    @Override
    @Transactional
    public void deleteInvoice(Long invoiceId) {
        boolean exists = this.invoiceRepository.existsById(invoiceId);

        if (!exists) {
            throw new IllegalArgumentException("invoice Id does not exist");
        }

        this.invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public InvoiceWithItemsDto getInvoiceWithItems(Long id) {
        Invoice invoice = this.invoiceRepository.findById(id)
                .orElseThrow(() -> new AppException(Error.INVOICE_NOT_FOUND));

        return new InvoiceWithItemsDto(invoice.getId(), invoice.getDescription(), invoice.getCreatedAt(),
                invoice.getPaymentDue(), invoice.getTotalCost(), invoice.getItems());
    }

}


















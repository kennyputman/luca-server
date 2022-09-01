package com.lucaapps.server.invoice.services;

import com.lucaapps.server.invoice.dtos.InvoiceDto;
import com.lucaapps.server.invoice.dtos.InvoicePostDto;
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
    public List<InvoiceDto> getAllInvoices(){
        List<Invoice> invoices = this.invoiceRepository.findAll();
        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice i: invoices) {
            invoiceDtos.add(new InvoiceDto(
                    i.getId(), i.getDescription(), i.getCreatedAt(), i.getPaymentDue()
            ));
        }
        return invoiceDtos;
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return this.invoiceRepository.findById(id);
    }

    @Override
    @Transactional
    public InvoiceWithItemsDto addNewInvoice(InvoicePostDto invoicePostDto) {

        Invoice mappedInvoice = new Invoice(invoicePostDto.getDescription(), invoicePostDto.getPaymentDue(), invoicePostDto.getItems());
        for (Item item: mappedInvoice.getItems()) {
            item.setInvoice(mappedInvoice);
        }

        Invoice createdInvoice =  this.invoiceRepository.save(mappedInvoice);

        InvoiceWithItemsDto invoice = new InvoiceWithItemsDto(
                createdInvoice.getId(), createdInvoice.getDescription(),
                createdInvoice.getCreatedAt(), createdInvoice.getPaymentDue(),
                createdInvoice.getTotalCost(), createdInvoice.getItems());
        return invoice;
    }

    @Override
    @Transactional
    public void deleteInvoice(Long invoiceId) {
        boolean exists = this.invoiceRepository.existsById(invoiceId);

        if(!exists){
            throw new IllegalArgumentException("invoice Id does not exist");
        }

        this.invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public InvoiceWithItemsDto getInvoiceWithItems(Long id) {
        Optional<Invoice> invoiceById  = this.invoiceRepository.findById(id);

        if (invoiceById.isEmpty()) {
            throw new IllegalArgumentException("invoice of id: " + id + " not found");
        }
        Invoice i = invoiceById.get();
        return new InvoiceWithItemsDto(i.getId(), i.getDescription(), i.getCreatedAt(),
                i.getPaymentDue(), i.getTotalCost(), i.getItems());
    }
}


















package com.lucaapps.server.invoice;

import com.lucaapps.server.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.invoice.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public List<Invoice> getAllInvoices(){
        return this.invoiceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> getInvoiceById(Long id) {
        return this.invoiceRepository.findById(id);
    }

    @Override
    public Invoice addNewInvoice(InvoicePostDto invoicePostDto) {

        Invoice invoice = new Invoice(invoicePostDto.getDescription(), invoicePostDto.getPaymentDue());
        return this.invoiceRepository.save(invoice);
    }

    @Override
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


















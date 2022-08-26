package com.lucaapps.server.invoice;

import com.lucaapps.server.invoice.dtos.InvoicePostDto;
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

        Invoice invoice = new Invoice(invoicePostDto.getDescription());
        return this.invoiceRepository.save(invoice);
    }
}

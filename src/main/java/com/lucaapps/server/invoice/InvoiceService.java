package com.lucaapps.server.invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<Invoice> getAllInvoices();

    Optional<Invoice> getInvoiceById(Long id);
}

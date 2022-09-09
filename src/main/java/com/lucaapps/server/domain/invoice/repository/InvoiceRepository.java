package com.lucaapps.server.domain.invoice.repository;

import com.lucaapps.server.domain.invoice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}


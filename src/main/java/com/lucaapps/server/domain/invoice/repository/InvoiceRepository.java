package com.lucaapps.server.domain.invoice.repository;

import com.lucaapps.server.domain.invoice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT i FROM Invoice i WHERE i.appUser.id = :id")
    List<Invoice> findInvoicesByUserId(@Param("id") Long id);
}


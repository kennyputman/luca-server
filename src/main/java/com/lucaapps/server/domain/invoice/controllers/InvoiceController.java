package com.lucaapps.server.domain.invoice.controllers;

import com.lucaapps.server.domain.invoice.dtos.InvoiceDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePutDto;
import com.lucaapps.server.domain.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.domain.invoice.entities.Item;
import com.lucaapps.server.domain.invoice.services.InvoiceService;
import com.lucaapps.server.domain.user.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getInvoices(@AuthenticationPrincipal UserDetails authUser) {
        return ResponseEntity.ok(this.invoiceService.getAllInvoices((AppUser) authUser));
    }


    @GetMapping("/{id}")
    public ResponseEntity<InvoiceWithItemsDto> getInvoiceWithItems(@PathVariable Long id, @AuthenticationPrincipal UserDetails authUser){
        InvoiceWithItemsDto invoice = this.invoiceService.getInvoiceWithItems(id, (AppUser) authUser);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<InvoiceWithItemsDto> createInvoice(@Valid @RequestBody final InvoicePostDto invoicePost, @AuthenticationPrincipal UserDetails authUser){
        InvoiceWithItemsDto invoice = this.invoiceService.addNewInvoice(invoicePost, (AppUser) authUser);
        return ResponseEntity.ok(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceWithItemsDto> updateInvoice(
            @PathVariable Long id, @Valid @RequestBody final InvoicePutDto updatedInvoice,
            @AuthenticationPrincipal UserDetails authUser){
        if(id != updatedInvoice.getId()){
            return ResponseEntity.badRequest().build();
        }
        InvoiceWithItemsDto invoice = this.invoiceService.updateInvoice(updatedInvoice, (AppUser) authUser);
        return ResponseEntity.ok(invoice);
    }

    @DeleteMapping("/{id}")
    public  void deleteInvoice(@PathVariable Long id, @AuthenticationPrincipal UserDetails authUser ){
        this.invoiceService.deleteInvoice(id, (AppUser) authUser);
    }

}

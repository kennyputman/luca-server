package com.lucaapps.server.domain.invoice.controllers;

import com.lucaapps.server.domain.invoice.dtos.InvoiceDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.domain.invoice.dtos.InvoicePutDto;
import com.lucaapps.server.domain.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.domain.invoice.entities.Item;
import com.lucaapps.server.domain.invoice.services.InvoiceService;
import com.lucaapps.server.domain.invoice.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ItemService itemService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, ItemService itemService) {
        this.invoiceService = invoiceService;
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getInvoices(@AuthenticationPrincipal User authUser) {
        return ResponseEntity.ok(this.invoiceService.getAllInvoices());
    }


    @GetMapping("/{id}")
    public ResponseEntity<InvoiceWithItemsDto> getInvoiceWithItems(@PathVariable Long id){
        InvoiceWithItemsDto invoice = this.invoiceService.getInvoiceWithItems(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/{invoice_id}/items/{item_id}")
    public ResponseEntity<Item> getItemById(
            @PathVariable("invoice_id" ) Long invoice_id,  @PathVariable("item_id") Long item_id){
       Optional<Item> item = this.itemService.getItemById(invoice_id, item_id);
       if(item.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(item.get());
    }

    @PostMapping
    public ResponseEntity<InvoiceWithItemsDto> createInvoice(@Valid @RequestBody final InvoicePostDto invoicePost){
        InvoiceWithItemsDto invoice = this.invoiceService.addNewInvoice(invoicePost);
        return ResponseEntity.ok(invoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceWithItemsDto> updateInvoice(@PathVariable Long id, @Valid @RequestBody final InvoicePutDto updatedInvoice){
        if(id != updatedInvoice.getId()){
            return ResponseEntity.badRequest().build();
        }
        InvoiceWithItemsDto invoice = this.invoiceService.updateInvoice(updatedInvoice);
        return ResponseEntity.ok(invoice);
    }

    @DeleteMapping("/{id}")
    public  void deleteInvoice(@PathVariable Long id ){
        this.invoiceService.deleteInvoice(id);
    }

}


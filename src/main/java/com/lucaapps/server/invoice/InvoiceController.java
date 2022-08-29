package com.lucaapps.server.invoice;

import com.lucaapps.server.invoice.dtos.InvoicePostDto;
import com.lucaapps.server.invoice.dtos.InvoiceWithItemsDto;
import com.lucaapps.server.invoice.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Invoice>> getInvoices() {
        return ResponseEntity.ok(this.invoiceService.getAllInvoices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = this.invoiceService.getInvoiceById(id);
        if (invoice.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice.get());
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<InvoiceWithItemsDto> getInvoiceWithItems(@PathVariable Long id){
        InvoiceWithItemsDto invoice = this.invoiceService.getInvoiceWithItems(id);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody final InvoicePostDto invoice){
        Invoice newInvoice = this.invoiceService.addNewInvoice(invoice);
        return ResponseEntity.ok(newInvoice);
    }

    @DeleteMapping("/{id}")
    public  void deleteInvoice(@PathVariable Long id ){
        this.invoiceService.deleteInvoice(id);
    }

}



















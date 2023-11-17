package com.killerwilmer.store.controller;

import com.killerwilmer.store.entity.Invoice;
import com.killerwilmer.store.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

  private InvoiceService invoiceService;

  @Autowired
  public InvoiceController(InvoiceService invoiceService) {
    this.invoiceService = invoiceService;
  }

  @PostMapping("/generate/{cartId}")
  public ResponseEntity<Invoice> generateInvoice(@PathVariable Integer cartId) {
    Invoice invoice = invoiceService.generateInvoiceFromCart(cartId);

    if (invoice != null) {
      return ResponseEntity.ok(invoice);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}

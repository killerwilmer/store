package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Cart;
import com.killerwilmer.store.entity.Invoice;
import com.killerwilmer.store.repository.CartRepository;
import com.killerwilmer.store.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InvoiceServiceImpl implements InvoiceService {

  private CartRepository cartRepository;
  private final InvoiceRepository invoiceRepository;

  @Autowired
  public InvoiceServiceImpl(CartRepository cartRepository, InvoiceRepository invoiceRepository) {
    this.cartRepository = cartRepository;
    this.invoiceRepository = invoiceRepository;
  }

  @Override
  public Invoice generateInvoiceFromCart(Integer cartId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);

    if (cart != null && cart.getStatus().equals("Active")) {
      Invoice invoice = cart.generateInvoice();
      invoice.setDate(LocalDate.now());
      cart.setStatus("Completed");
      cartRepository.save(cart);

      Invoice saveInvoice = invoiceRepository.save(invoice);

      return saveInvoice;
    }

    return null;
  }
}

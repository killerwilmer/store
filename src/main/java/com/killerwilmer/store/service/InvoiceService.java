package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Invoice;

public interface InvoiceService {

  Invoice generateInvoiceFromCart(Integer cartId);
}

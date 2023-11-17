package com.killerwilmer.store.repository;

import com.killerwilmer.store.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {}

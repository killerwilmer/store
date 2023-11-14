package com.killerwilmer.store.repository;

import com.killerwilmer.store.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {}

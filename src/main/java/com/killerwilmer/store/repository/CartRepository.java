package com.killerwilmer.store.repository;

import com.killerwilmer.store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {}

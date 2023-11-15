package com.killerwilmer.store.repository;

import com.killerwilmer.store.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

  @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
  List<CartItem> findByCartId(@Param("cartId") Integer cartId);
}

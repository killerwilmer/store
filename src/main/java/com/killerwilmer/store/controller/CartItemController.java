package com.killerwilmer.store.controller;

import com.killerwilmer.store.entity.CartItem;
import com.killerwilmer.store.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

  private final CartItemService cartItemService;

  @Autowired
  public CartItemController(CartItemService cartItemService) {
    this.cartItemService = cartItemService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CartItem> getCartItemById(@PathVariable Integer id) {
    CartItem cartItem = cartItemService.getCartItemById(id);
    return cartItem != null ? ResponseEntity.ok(cartItem) : ResponseEntity.notFound().build();
  }
}

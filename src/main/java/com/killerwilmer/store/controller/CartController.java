package com.killerwilmer.store.controller;

import com.killerwilmer.store.entity.Buyer;
import com.killerwilmer.store.entity.Cart;
import com.killerwilmer.store.service.BuyerService;
import com.killerwilmer.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

  private final CartService cartService;

  private final BuyerService buyerService;

  @Autowired
  public CartController(CartService cartService, BuyerService buyerService) {
    this.cartService = cartService;
    this.buyerService = buyerService;
  }

  @PostMapping("/create/{buyerId}")
  public ResponseEntity<Cart> createCart(@PathVariable Integer buyerId) {
    Buyer buyer = buyerService.getBuyerById(buyerId).stream().findFirst().orElse(null);

    Cart cart = cartService.createCart(buyer);
    return ResponseEntity.ok(cart);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cart> getCartById(@PathVariable Integer id) {
    Cart cart = cartService.getCartById(id);
    return cart != null ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
  }

  @GetMapping
  public List<Cart> getAllCarts() {
    return cartService.getAllCarts();
  }

  @PostMapping("/{cartId}/add-product/{productId}/{quantity}")
  public ResponseEntity<Cart> addProductToCart(
      @PathVariable Integer cartId, @PathVariable Integer productId, @PathVariable int quantity) {
    cartService.addProductToCart(cartId, productId, quantity);
    return ResponseEntity.ok(cartService.getCartById(cartId));
  }

  @PostMapping("/{cartId}/remove-product/{productId}")
  public ResponseEntity<Cart> removeProductFromCart(
      @PathVariable Integer cartId, @PathVariable Integer productId) {
    cartService.removeProductFromCart(cartId, productId);
    return ResponseEntity.ok(cartService.getCartById(cartId));
  }
}

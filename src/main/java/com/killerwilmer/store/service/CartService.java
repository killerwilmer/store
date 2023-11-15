package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Buyer;
import com.killerwilmer.store.entity.Cart;
import com.killerwilmer.store.entity.CartItem;

import java.util.List;

public interface CartService {

  Cart createCart(Buyer buyer);

  Cart getCartById(Integer id);

  List<Cart> getAllCarts();

  List<CartItem> getCartItemsByCart(Integer cartId);

  void addProductToCart(Integer cartId, Integer productId, int quantity);

  void removeProductFromCart(Integer cartId, Integer productId);
}

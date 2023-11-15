package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.CartItem;

import java.util.List;

public interface CartItemService {

  CartItem getCartItemById(Integer id);

  List<CartItem> getCartItemsByCart(Integer cartId);
}

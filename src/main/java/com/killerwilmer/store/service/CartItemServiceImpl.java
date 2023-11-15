package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.CartItem;
import com.killerwilmer.store.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

  private final CartItemRepository cartItemRepository;

  @Autowired
  public CartItemServiceImpl(CartItemRepository cartItemRepository) {
    this.cartItemRepository = cartItemRepository;
  }

  @Override
  public CartItem getCartItemById(Integer id) {
    return cartItemRepository.findById(id).orElse(null);
  }

  @Override
  public List<CartItem> getCartItemsByCart(Integer cartId) {
    return cartItemRepository.findByCartId(cartId);
  }
}

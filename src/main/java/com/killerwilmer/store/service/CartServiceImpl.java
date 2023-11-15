package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Buyer;
import com.killerwilmer.store.entity.Cart;
import com.killerwilmer.store.entity.CartItem;
import com.killerwilmer.store.entity.Product;
import com.killerwilmer.store.repository.CartItemRepository;
import com.killerwilmer.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductService productService;

  @Autowired
  public CartServiceImpl(
      CartRepository cartRepository,
      CartItemRepository cartItemRepository,
      ProductService productService) {
    this.cartRepository = cartRepository;
    this.cartItemRepository = cartItemRepository;
    this.productService = productService;
  }

  @Override
  public Cart createCart(Buyer buyer) {
    Cart cart = new Cart();
    cart.setBuyer(buyer);
    cart.setStatus("Active");
    return cartRepository.save(cart);
  }

  @Override
  public Cart getCartById(Integer id) {
    return cartRepository.findById(id).orElse(null);
  }

  @Override
  public List<Cart> getAllCarts() {
    return cartRepository.findAll();
  }

  @Override
  public List<CartItem> getCartItemsByCart(Integer cartId) {
    return cartItemRepository.findByCartId(cartId);
  }

  public void addProductToCart(Integer cartId, Integer productId, int quantity) {
    Cart cart = cartRepository.findById(cartId).orElse(null);
    Product product = productService.getProductById(productId).stream().findFirst().orElse(null);

    if (cart != null && product != null) {
      CartItem cartItem = new CartItem();
      cartItem.setCart(cart);
      cartItem.setProduct(product);
      cartItem.setQuantity(quantity);
      cartItem.setUnitPrice(product.getPrice());

      if (cart.getCartItems() == null) {
        cart.setCartItems(new HashSet<>());
      }

      cart.getCartItems().add(cartItem);
      cartRepository.save(cart);
    }
  }

  @Override
  public void removeProductFromCart(Integer cartId, Integer productId) {
    Cart cart = cartRepository.findById(cartId).orElse(null);

    if (cart != null) {
      cart.getCartItems().removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));
      cartRepository.save(cart);
    }
  }
}

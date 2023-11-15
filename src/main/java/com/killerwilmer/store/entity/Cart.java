package com.killerwilmer.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Carts", schema = "STORE")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "buyer_id")
  private Buyer buyer;

  @Column(name = "status", nullable = false, length = 50)
  private String status;

  @OneToMany(
      mappedBy = "cart",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @JsonIgnoreProperties("cart")
  private Set<CartItem> cartItems = new LinkedHashSet<>();

  public Set<CartItem> getCartItems() {
    return cartItems;
  }

  public void setCartItems(Set<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Buyer getBuyer() {
    return buyer;
  }

  public void setBuyer(Buyer buyer) {
    this.buyer = buyer;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}

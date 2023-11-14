package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

  List<Product> getAllProducts();

  Optional<Product> getProductById(Integer id);

  Product createProduct(Product product);

  Optional<Product> updateProduct(Integer id, Product updatedProduct);

  void deleteProduct(Integer id);
}

package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Product;
import com.killerwilmer.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Optional<Product> getProductById(Integer id) {
    return productRepository.findById(id);
  }

  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Optional<Product> updateProduct(Integer id, Product updatedProduct) {
    if (productRepository.existsById(id)) {
      updatedProduct.setId(id);
      return Optional.of(productRepository.save(updatedProduct));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void deleteProduct(Integer id) {
    productRepository.deleteById(id);
  }
}

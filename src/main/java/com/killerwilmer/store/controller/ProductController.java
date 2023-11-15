package com.killerwilmer.store.controller;

import com.killerwilmer.store.entity.Category;
import com.killerwilmer.store.entity.Product;
import com.killerwilmer.store.service.CategoryService;
import com.killerwilmer.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;
  private final CategoryService categoryService;

  @Autowired
  public ProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
    return productService
        .getProductById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);
    return ResponseEntity.ok(createdProduct);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable Integer id, @RequestBody Product updatedProduct) {
    return productService
        .updateProduct(id, updatedProduct)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{productId}/add-category/{categoryId}")
  public ResponseEntity<Product> addCategoryToProduct(
      @PathVariable Integer productId, @PathVariable Integer categoryId) {
    Product product = productService.getProductById(productId).stream().findFirst().orElse(null);
    Category category =
        categoryService.getCategoryById(categoryId).stream().findFirst().orElse(null);

    if (product != null && category != null) {
      product.getCategories().add(category);
      productService.createProduct(product);
      return ResponseEntity.ok(product);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}

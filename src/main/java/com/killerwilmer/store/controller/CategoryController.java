package com.killerwilmer.store.controller;

import com.killerwilmer.store.entity.Category;
import com.killerwilmer.store.entity.Product;
import com.killerwilmer.store.response.ApiResponse;
import com.killerwilmer.store.service.CategoryServiceImpl;
import com.killerwilmer.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

  private final CategoryServiceImpl categoryService;
  private final ProductService productService;

  @Autowired
  public CategoryController(CategoryServiceImpl categoryService, ProductService productService) {
    this.categoryService = categoryService;
    this.productService = productService;
  }

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Integer id) {
    Optional<Category> categoryOptional = categoryService.getCategoryById(id);

    if (categoryOptional.isPresent()) {
      Category category = categoryOptional.get();
      return ResponseEntity.ok(new ApiResponse<>(true, "Category found", category));
    } else {
      return ResponseEntity.ok(new ApiResponse<>(false, "Category not found", null));
    }
  }

  @PostMapping
  public Category createCategory(@RequestBody Category category) {
    return categoryService.createCategory(category);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Category>> updateCategory(
      @PathVariable Integer id, @RequestBody Category updatedCategory) {

    Optional<Category> categoryOptional = categoryService.updateCategory(id, updatedCategory);

    if (categoryOptional.isPresent()) {
      Category category = categoryOptional.get();
      return ResponseEntity.ok(new ApiResponse<>(true, "Category updated", category));
    } else {
      return ResponseEntity.ok(new ApiResponse<>(false, "Category not found", null));
    }
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable Integer id) {
    categoryService.deleteCategory(id);
  }

  @PostMapping("/{categoryId}/add-product/{productId}")
  public ResponseEntity<Category> addProductToCategory(
      @PathVariable Integer categoryId, @PathVariable Integer productId) {
    Category category =
        categoryService.getCategoryById(categoryId).stream().findFirst().orElse(null);
    Product product = productService.getProductById(productId).stream().findFirst().orElse(null);

    if (category != null && product != null) {
      category.getProducts().add(product);
      categoryService.createCategory(category);
      return ResponseEntity.ok(category);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}

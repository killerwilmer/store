package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

  List<Category> findAll();

  Optional<Category> getCategoryById(Integer id);

  Category createCategory(Category category);

  Optional<Category> updateCategory(Integer id, Category updatedCategory);

  void deleteCategory(Integer id);
}

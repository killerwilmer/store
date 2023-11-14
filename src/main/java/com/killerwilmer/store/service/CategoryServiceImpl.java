package com.killerwilmer.store.service;

import com.killerwilmer.store.entity.Category;
import com.killerwilmer.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAllByOrderByNameAsc();
  }

  public Optional<Category> getCategoryById(Integer id) {
    return categoryRepository.findById(id);
  }

  public Category createCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Optional<Category> updateCategory(Integer id, Category updatedCategory) {
    if (categoryRepository.existsById(id)) {
      updatedCategory.setId(id);
      return Optional.of(categoryRepository.save(updatedCategory));
    } else {
      return Optional.empty();
    }
  }

  public void deleteCategory(Integer id) {
    categoryRepository.deleteById(id);
  }
}

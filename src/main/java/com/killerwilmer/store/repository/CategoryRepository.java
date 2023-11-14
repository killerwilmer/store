package com.killerwilmer.store.repository;

import com.killerwilmer.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  public List<Category> findAllByOrderByNameAsc();
}

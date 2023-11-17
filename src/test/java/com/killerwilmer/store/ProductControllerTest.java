package com.killerwilmer.store;

import com.killerwilmer.store.controller.ProductController;
import com.killerwilmer.store.entity.Product;
import com.killerwilmer.store.service.CategoryService;
import com.killerwilmer.store.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductService productService;
  @MockBean private CategoryService categoryService;

  @Test
  public void testGetProductById() throws Exception {

    when(productService.getProductById(anyInt()))
        .thenReturn(Optional.of(new Product()));

    mockMvc.perform(get("/api/products/1")).andExpect(status().isOk());
  }
}

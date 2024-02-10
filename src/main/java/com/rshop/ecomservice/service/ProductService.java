package com.rshop.ecomservice.service;

import com.rshop.ecomservice.domain.Product;
import com.rshop.ecomservice.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductService {

  @Autowired
  private ProductRepo productRepo;

  public List<Product> getAllProducts() {
    return productRepo.findAll();
  }

  public Product getProduct(long id) {
    return productRepo
        .findById(id)
        .orElse(null);
  }

  public Product save(Product product) {
    return productRepo.save(product);
  }

  public List<Product> searchProduct(String searchString) {
    if (Objects.isNull(searchString) || searchString.trim().isEmpty()) {
      return productRepo.findAll();
    }
    return productRepo.findByTitleContaining(searchString);
  }
}

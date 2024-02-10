package com.rshop.ecomservice.repo;

import com.rshop.ecomservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
  List<Product> findByTitleContaining(String searchString);
}

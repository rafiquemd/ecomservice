package com.rshop.ecomservice.controller;

import com.rshop.ecomservice.domain.Product;
import com.rshop.ecomservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
@Slf4j
public class ProductController {

  @Autowired
  private ProductService service;

  @GetMapping
  public ResponseEntity<List<Product>> searchProduct(@RequestParam(name = "q", required = false) String searchString) {
    log.info("Search String {}", searchString);
    return ResponseEntity.ok(service.searchProduct(searchString));
  }

}

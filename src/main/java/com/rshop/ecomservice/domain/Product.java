package com.rshop.ecomservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String description;

  private Double price;

  private String imageUrl;

  @Enumerated(EnumType.STRING)
  private Category category;

  public enum Category {
    HOME, SPORTS, KITCHEN, ELECTRONICS
  }
}

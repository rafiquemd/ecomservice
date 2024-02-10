package com.rshop.ecomservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderProduct {

  public OrderProduct(Order order, Product product, Integer quantity) {
    orderProductPK = new OrderProductPK();
    orderProductPK.setOrder(order);
    orderProductPK.setProduct(product);
    this.quantity = quantity;
  }


  @EmbeddedId
  @JsonIgnore
  private OrderProductPK orderProductPK;

  @Column(nullable = false)
  private Integer quantity;

  @Transient
  public Product getProduct() {
    return this.orderProductPK.getProduct();
  }

  @Transient
  public Double getTotalPrice() {
    return getProduct().getPrice() * getQuantity();
  }

}
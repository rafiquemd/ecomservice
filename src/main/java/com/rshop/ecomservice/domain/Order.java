package com.rshop.ecomservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateCreated;

  private String status;

  @JsonManagedReference
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderProductPK.order")
//  @Valid
  private List<OrderProduct> orderProducts;

  @Transient
  public Double getTotalOrderPrice() {
    double sum = 0D;
    List<OrderProduct> orderProducts = getOrderProducts();
    for (OrderProduct op : orderProducts) {
      sum += op.getTotalPrice();
    }
    return sum;
  }

  @Transient
  public int getNumberOfProducts() {
    return this.orderProducts.size();
  }

  public enum OrderStatus {
    PAID
  }
}

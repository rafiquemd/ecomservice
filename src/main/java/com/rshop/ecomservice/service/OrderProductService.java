package com.rshop.ecomservice.service;

import com.rshop.ecomservice.domain.OrderProduct;
import com.rshop.ecomservice.repo.OrderProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductService {

@Autowired
  private OrderProductRepo orderProductRepository;

  public OrderProduct create(OrderProduct orderProduct) {
    return this.orderProductRepository.save(orderProduct);
  }
}
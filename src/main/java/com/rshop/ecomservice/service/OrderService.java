package com.rshop.ecomservice.service;

import com.rshop.ecomservice.controller.OrderController;
import com.rshop.ecomservice.domain.Order;
import com.rshop.ecomservice.domain.OrderProduct;
import com.rshop.ecomservice.dto.OrderProductDto;
import com.rshop.ecomservice.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  private OrderProductService orderProductService;

  @Autowired
  private ProductService productService;

  public List<Order> getAllOrders() {
    return orderRepo.findAll();
  }

  public Optional<Order> getOrderById(long id) {
    return orderRepo.findById(id);
  }


  public Order processOrder(OrderController.OrderForm orderForm) {
    checkInventory(orderForm.getProductOrders());
    Order order = new Order();
    order.setStatus(Order.OrderStatus.PAID.name());
    order = create(order);

    List<OrderProduct> orderProducts = new ArrayList<>();
    for (OrderProductDto dto : orderForm.getProductOrders()) {
      orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto.getProductId()), dto.getQuantity())));
    }

    order.setOrderProducts(orderProducts);
    return update(order);
  }

  public Order create(Order order) {
    order.setDateCreated(LocalDate.now());
    return orderRepo.save(order);
  }

  public Order update(Order order) {
    return orderRepo.save(order);
  }


  private void checkInventory(List<OrderProductDto> orderProducts) {
    List<OrderProductDto> outOfStock = orderProducts
        .stream()
        .filter(op -> Objects.isNull(productService.getProduct(op.getProductId())))
        .toList();

    if (!CollectionUtils.isEmpty(outOfStock)) {
      throw new RuntimeException("Products not in stock");
    }
  }

}

package com.rshop.ecomservice.controller;

import com.rshop.ecomservice.domain.Order;
import com.rshop.ecomservice.domain.OrderProduct;
import com.rshop.ecomservice.dto.OrderProductDto;
import com.rshop.ecomservice.service.OrderProductService;
import com.rshop.ecomservice.service.OrderService;
import com.rshop.ecomservice.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Order> getAllOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Order getOrderById(@PathVariable Long id) {
    return orderService.getOrderById(id).orElse(null);
  }

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody OrderForm orderForm) {
    Order order = orderService.processOrder(orderForm);
    String uri = ServletUriComponentsBuilder
        .fromCurrentServletMapping()
        .path("/orders/{id}")
        .buildAndExpand(order.getId())
        .toString();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", uri);
    return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
  }


  @Data
  public static class OrderForm {
    private List<OrderProductDto> productOrders;
  }
}

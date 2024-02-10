package com.rshop.ecomservice.repo;

import com.rshop.ecomservice.domain.OrderProduct;
import com.rshop.ecomservice.domain.OrderProductPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepo extends CrudRepository<OrderProduct, OrderProductPK> {
}
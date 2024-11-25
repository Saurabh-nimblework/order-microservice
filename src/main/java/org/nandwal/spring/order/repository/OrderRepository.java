package org.nandwal.spring.order.repository;

import org.nandwal.spring.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, String> {
}

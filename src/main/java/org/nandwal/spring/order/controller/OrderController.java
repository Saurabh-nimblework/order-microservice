package org.nandwal.spring.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.nandwal.spring.order.entity.Order;
import org.nandwal.spring.order.kafka.OrderProducerConfig;
import org.nandwal.spring.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final OrderProducerConfig orderProducerConfig;
    private final ObjectMapper objectMapper;

    @Autowired
    public OrderController(OrderProducerConfig orderProducerConfig, ObjectMapper objectMapper) {
        this.orderProducerConfig = orderProducerConfig;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/all")
    public void saveOrder(@RequestBody Order order) {
        try {
            String orderJson = objectMapper.writeValueAsString(order);
            orderProducerConfig.publish(orderJson);
            orderService.saveOrder(order);
            System.out.println("Order placed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package org.nandwal.spring.order.controller;

import org.nandwal.spring.order.entity.Order;
import org.nandwal.spring.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.stream.function.StreamBridge;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/all")
    public void saveOrder(@RequestBody Order order) {
        try {
            streamBridge.send("sendOrderDetails-out-0",order);
            orderService.saveOrder(order);
            log.info("Order placed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
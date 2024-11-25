package org.nandwal.spring.order.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nandwal.spring.order.entity.Order;
import org.nandwal.spring.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.util.function.Consumer;

@Configuration
public class OrderStatusListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private OrderService orderService;

    @Bean
    public Consumer<String> receiveInventoryUpdates() {
        return message -> {
            try {
                Order order = objectMapper.readValue(message, Order.class);
                orderService.saveOrder(order);
                System.out.println("Order status for itemId: " + order.getItemId() + " is: " + order.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}

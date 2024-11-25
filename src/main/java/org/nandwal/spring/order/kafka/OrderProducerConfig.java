package org.nandwal.spring.order.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

@Configuration
public class OrderProducerConfig {

    // Use a queue to store messages until the Supplier is triggered
    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    // Supplier that retrieves messages from the queue and publishes them
    @Bean
    public Supplier<String> sendOrderDetails() {
        return () -> messageQueue.poll(); // Returns null if the queue is empty
    }

    // Method to add a message to the queue
    public void publish(String message) {
        messageQueue.offer(message);
    }
}

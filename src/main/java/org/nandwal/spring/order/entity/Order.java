package org.nandwal.spring.order.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String orderId;
    private String itemId;
    private int quantity;
    private String status;

}

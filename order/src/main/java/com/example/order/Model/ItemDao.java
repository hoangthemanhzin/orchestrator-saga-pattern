package com.example.order.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item_table")
public class ItemDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable=false)
    private OrderDao order;
    private String itemUUID;
    private long quantity;
}

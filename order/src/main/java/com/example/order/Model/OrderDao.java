package com.example.order.Model;

import com.example.common.util.orderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "order_table")
public class OrderDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private double orderPrice;
    @OneToMany(mappedBy = "order",
               cascade = CascadeType.ALL)
    private List<ItemDao> items;
    private String orderUUID;
    private String stockStatus = orderStatus.NEW;
    private String stockServiceReason;
    private String paymentStatus = orderStatus.NEW;
    private String paymentServiceReason;
}

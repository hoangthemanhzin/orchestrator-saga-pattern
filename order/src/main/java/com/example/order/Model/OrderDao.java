package com.example.order.Model;

import com.example.common.util.orderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Entity
@Table(name = "order_table")
public class OrderDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "orderPrice")
    private double orderPrice;
    @OneToMany(mappedBy = "order",
               cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ItemDao> items;
    @Column(name = "orderUUID")
    private String orderUUID;
    @Column(name = "stockStatus")
    private String stockStatus = orderStatus.NEW;
    @Column(name = "stockServiceReason")
    private String stockServiceReason;
    @Column(name = "payment_status")
    private String paymentStatus = orderStatus.NEW;
    @Column(name = "paymentServiceReason")
    private String paymentServiceReason;

    public OrderDao(Integer id, double orderPrice, List<ItemDao> items, String orderUUID, String stockStatus, String stockServiceReason, String paymentStatus, String paymentServiceReason) {
        Id = id;
        this.orderPrice = orderPrice;
        this.items = items;
        this.orderUUID = orderUUID;
        this.stockStatus = stockStatus;
        this.stockServiceReason = stockServiceReason;
        this.paymentStatus = paymentStatus;
        this.paymentServiceReason = paymentServiceReason;
    }

    public OrderDao() {
    }

    public Integer getId() {
        return Id;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public List<ItemDao> getItems() {
        return items;
    }

    public String getOrderUUID() {
        return orderUUID;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public String getStockServiceReason() {
        return stockServiceReason;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentServiceReason() {
        return paymentServiceReason;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setItems(List<ItemDao> items) {
        this.items = items;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public void setStockServiceReason(String stockServiceReason) {
        this.stockServiceReason = stockServiceReason;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentServiceReason(String paymentServiceReason) {
        this.paymentServiceReason = paymentServiceReason;
    }
}

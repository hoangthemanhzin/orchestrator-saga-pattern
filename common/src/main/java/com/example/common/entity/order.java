package com.example.common.entity;

import lombok.Data;
import java.util.List;
import java.util.stream.Stream;

@Data
public class order {
    private String orderNumber;
    private List<item> items;
    private double orderPrice;
    private String stockStatus;
    private String stockServiceReason;
    private String paymentStatus;
    private String paymentServiceReason;

    public order(String orderNumber, List<item> items, double orderPrice, String stockStatus, String stockServiceReason, String paymentStatus, String paymentServiceReason) {
        this.orderNumber = orderNumber;
        this.items = items;
        this.orderPrice = orderPrice;
        this.stockStatus = stockStatus;
        this.stockServiceReason = stockServiceReason;
        this.paymentStatus = paymentStatus;
        this.paymentServiceReason = paymentServiceReason;
    }

    public order() {
    }
}

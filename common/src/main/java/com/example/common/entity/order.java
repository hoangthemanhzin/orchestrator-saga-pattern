package com.example.common.entity;

import lombok.Data;
import java.util.List;

@Data
public class order {
    private String orderNumber;

    private List<item> items;

    private double orderPrice;

    private String stockStatus;

    private String stockServiceReason;

    private String paymentStatus;

    private String paymentServiceReason;
}

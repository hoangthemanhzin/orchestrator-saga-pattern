package com.example.common.entity;

import lombok.Data;

@Data
public class item {
    private String itemNumber;
    private long quantity;

    public item (String itemNumber, long quantity){
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }
}

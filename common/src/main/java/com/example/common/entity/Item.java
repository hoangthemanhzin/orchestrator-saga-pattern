package com.example.common.entity;

import lombok.Data;

@Data
public class Item {
    private String itemNumber;
    private long quantity;

    public Item (String itemNumber, long quantity){
        this.itemNumber = itemNumber;
        this.quantity = quantity;
    }
}

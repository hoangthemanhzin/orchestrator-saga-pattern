package com.example.order.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "item_table")
public class ItemDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable=false)
    private OrderDao order;
    @Column(name = "itemUUID")
    private String itemUUID;
    @Column(name = "quantity")
    private long quantity;

    public ItemDao() {
    }

    public ItemDao(Integer id, OrderDao order, String itemUUID, long quantity) {
        Id = id;
        this.order = order;
        this.itemUUID = itemUUID;
        this.quantity = quantity;
    }

    public Integer getId() {
        return Id;
    }

    public OrderDao getOrder() {
        return order;
    }

    public String getItemUUID() {
        return itemUUID;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setOrder(OrderDao order) {
        this.order = order;
    }

    public void setItemUUID(String itemUUID) {
        this.itemUUID = itemUUID;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}

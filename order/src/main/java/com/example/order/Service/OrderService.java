package com.example.order.Service;

import com.example.order.Model.OrderDao;

import java.util.List;

public interface OrderService {
    public void saveOrderDao(OrderDao orderDao);

    public void saveAllOrderDao(List<OrderDao> orderDaos);
}

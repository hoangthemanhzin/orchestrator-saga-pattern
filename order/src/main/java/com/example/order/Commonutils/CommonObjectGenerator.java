package com.example.order.Commonutils;

import com.example.common.entity.item;
import com.example.common.entity.order;
import com.example.order.Model.OrderDao;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CommonObjectGenerator {
    public order orderGenerator(OrderDao orderDao){
        order orderToKafka = new order(orderDao.getOrderUUID(),
                orderDao.getItems().stream().map(itemDao -> new item(itemDao.getItemUUID(), itemDao.getQuantity()))
                        .collect(Collectors.toList()),
                orderDao.getOrderPrice(), orderDao.getStockStatus(), orderDao.getStockServiceReason(),
                orderDao.getPaymentStatus(), orderDao.getPaymentServiceReason()
        );
        return orderToKafka;
    }
}

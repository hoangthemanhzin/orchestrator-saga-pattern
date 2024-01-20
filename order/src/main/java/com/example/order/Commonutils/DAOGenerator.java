package com.example.order.Commonutils;

import com.example.order.Exception.GenerateException;
import com.example.order.Model.ItemDao;
import com.example.order.Model.OrderDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.ConfigTreePropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DAOGenerator {
    private final long maxItems;

    public DAOGenerator(@Value("1000") long maxItems){
        this.maxItems = maxItems;
    }
    public List<OrderDao> ListOrderDao(Optional<Long> orders, Optional<Long> items){
        long totalItems = orders.orElse(3L)*items.orElse(3L);
        if(totalItems > maxItems){
            throw new GenerateException("The number of items requested has exceeded the allowed level"
            + "number of orders : " + orders.toString() + "number of items : " + items.toString());
        }
        List<OrderDao> listOrderDao = new ArrayList<>();
        for(int i = 0; i <= orders.orElse(3L); i++){
            OrderDao orderDao = new OrderDao();
            orderDao.setOrderPrice(Math.random()*300);
            orderDao.setOrderUUID(UUID.randomUUID().toString());
            List<ItemDao> listItemDao = new ArrayList<>();
            for(int j = 0; j < items.orElse(3L); j++){
                ItemDao itemDao = new ItemDao();
                itemDao.setOrder(orderDao);
                itemDao.setQuantity((int)(Math.random()*10));
                Integer itemNumber = (int)(Math.random()*20);
                itemDao.setItemUUID("itemUUID " + itemNumber.toString());
                listItemDao.add(itemDao);
            }
            orderDao.setItems(listItemDao);
            listOrderDao.add(orderDao);
        }
        return listOrderDao;
    }
}

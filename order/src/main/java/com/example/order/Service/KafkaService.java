package com.example.order.Service;

import com.example.common.entity.order;

public interface KafkaService {
    public void sendOrder(String key, order orderInfor);

}

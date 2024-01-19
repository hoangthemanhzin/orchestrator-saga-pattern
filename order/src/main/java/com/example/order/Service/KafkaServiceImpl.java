package com.example.order.Service;

import com.example.common.entity.order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService{
    @Autowired
    private KafkaTemplate<String, order> kafkaTemplate;
    public void sendOrder(String key, order orderInfor) {
        log.debug("sending orderInfor to order topic : payload = {}", orderInfor);
        kafkaTemplate.send("order", key, orderInfor);
    }
}

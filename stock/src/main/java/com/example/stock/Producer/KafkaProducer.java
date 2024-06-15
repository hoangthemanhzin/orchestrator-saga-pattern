package com.example.stock.Producer;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Slf4j
public class KafkaProducer {
    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String kafkaTopicStock;


    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    public KafkaProducer(KafkaTemplate<String, Order> kafkaTemplate,
                         @Value("${topic.name.stock}") String kafkaTopicStock) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicStock = kafkaTopicStock;
    }

    public void send(String key, Order payload) {
        log.debug("sending payload={}, key={}, topic={}", payload, key, kafkaTopicStock);
        kafkaTemplate.send(kafkaTopicStock, key, payload);
    }
}

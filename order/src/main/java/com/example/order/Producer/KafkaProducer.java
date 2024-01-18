package com.example.order.Producer;

import com.example.common.entity.order;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaProducer {
    public ProducerFactory<String, order> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, order.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, order> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    private final KafkaTemplate<String, order> kafkaTemplate = this.kafkaTemplate();

    public void sendOrder(String key, order orderInfor){
        log.debug("sending orderInfor to order topic : ", orderInfor);
        kafkaTemplate.send("order", key, orderInfor);
    }
}

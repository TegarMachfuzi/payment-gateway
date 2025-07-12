package com.payment.service.config;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;

    @Value("${kafka.username}")
    private String kafkaUsername;

    @Value("${kafka.password}")
    private String kafkaPassword;

    @Bean
    public <K, V> ProducerFactory<K, V> createOrderProducerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        config.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
        config.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        config.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + kafkaUsername + "\" password=\"" + kafkaPassword + "\";");

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public <K, V> KafkaTemplate<K, V> createOrderKafkaTemplate(){
        return new KafkaTemplate<>(createOrderProducerFactory());
    }
}

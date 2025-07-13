package com.payment.service.service.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class KafkaHealthInterceptor implements HandlerInterceptor {

    private final KafkaTemplate<String, Object> kafkaProducer;

    public KafkaHealthInterceptor(KafkaTemplate<String, Object> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            kafkaProducer.partitionsFor("any-topic");
        }catch (Exception e) {
            LoggerFactory.getLogger(getClass()).warn("Kafka unreachable");
        }
        return true;
    }
}

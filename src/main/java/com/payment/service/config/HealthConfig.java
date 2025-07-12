package com.payment.service.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class HealthConfig {

    @Bean
    public HealthIndicator redisHealth(RedisConnectionFactory redisConnectionFactory) {
        return () -> {
            try {
                redisConnectionFactory.getConnection().ping();
                return Health.up().withDetail("Redis", "Available").build();
            }catch (Exception e) {
                return Health.down(e).withDetail("Redis", "Not Available").build();
            }
        };
    }
}

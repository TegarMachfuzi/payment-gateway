package com.payment.service.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

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

    @Bean
    public HealthIndicator kafkaHealth(){
        return () -> {
            try (AdminClient admin = AdminClient.create(Map.of("bootstrap.servers", "localhost:9092"))) {
                DescribeClusterResult describeClusterResult = admin.describeCluster();
                KafkaFuture<String> clusterId = describeClusterResult.clusterId();
                clusterId.get();
                return Health.up().withDetail("Kafka", "Available").build();
            }catch (Exception e) {
                return Health.down(e).withDetail("Kafka", "Not Available").build();
            }
        };
    }

    @Bean
    public HealthIndicator dbHealth(JdbcTemplate jdbcTemplate) {
        return () -> {
            try {
                jdbcTemplate.queryForObject("SELECT 1", Integer.class);
                return Health.up().withDetail("DB", "Available").build();
            }catch (Exception e) {
                return Health.down(e).withDetail("DB", "Not Available").build();
            }
        };
    }

}

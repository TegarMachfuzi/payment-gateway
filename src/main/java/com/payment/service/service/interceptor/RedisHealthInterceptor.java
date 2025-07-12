package com.payment.service.service.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RedisHealthInterceptor implements HandlerInterceptor {

    private final RedisTemplate<?, ?> redisTemplate;

    public RedisHealthInterceptor(RedisTemplate<?, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            redisTemplate.opsForValue().get("ping");
        }catch (Exception e) {
            LoggerFactory.getLogger(getClass()).warn("Redis unreachable");
        }
        return true;
    }
}

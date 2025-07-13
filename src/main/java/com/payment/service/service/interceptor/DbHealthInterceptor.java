package com.payment.service.service.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class DbHealthInterceptor implements HandlerInterceptor {

    private final JdbcTemplate jdbcTemplate;

    public DbHealthInterceptor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        }catch (Exception e) {
            LoggerFactory.getLogger(DbHealthInterceptor.class).error("DbHealthInterceptor preHandle error", e);
        }
        return true;
    }
}

package com.payment.service.service.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import org.slf4j.Logger;

@Component
public class LogFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) servletRequest;
        long start = System.currentTimeMillis();

        filterChain.doFilter(servletRequest, servletResponse);

        long duration = System.currentTimeMillis() - start;
        log.info("[FILTER] {} {} completed in {}ms", http.getMethod(), http.getRequestURI(), duration);

    }
}

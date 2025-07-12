package com.payment.service.service.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class TraceIdInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TraceIdInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = UUID.randomUUID().toString();
        request.setAttribute("traceId", traceId);
        request.setAttribute("startTime", System.currentTimeMillis());
        response.setHeader("X-Trace-Id", traceId);

        log.info("[TRACE][REQUEST ] {} {} | traceId={}", request.getMethod(), request.getRequestURI(), traceId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String traceId = (String) request.getAttribute("traceId");
        long startTime = (long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        log.info("[TRACE][RESPONSE] {} {} | status={} | duration={}ms | traceId={}",
                request.getMethod(), request.getRequestURI(), response.getStatus(), duration, traceId);

        if (ex != null) {
            log.error("[ERROR][EXCEPTION] traceId={} | message={}", traceId, ex.getMessage(), ex);
        }
    }
}

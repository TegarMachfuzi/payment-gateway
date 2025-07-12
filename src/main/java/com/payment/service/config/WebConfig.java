package com.payment.service.config;


import com.payment.service.service.interceptor.RedisHealthInterceptor;
import com.payment.service.service.interceptor.TraceIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Autowired
    private TraceIdInterceptor traceIdInterceptor;
    @Autowired
    private RedisHealthInterceptor redisHealthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceIdInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**", "/actuator/**");
        registry.addInterceptor(redisHealthInterceptor);
    }
}

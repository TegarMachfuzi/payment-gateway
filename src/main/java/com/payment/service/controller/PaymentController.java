package com.payment.service.controller;

import com.payment.service.dto.BaseResponse;
import com.payment.service.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/api/hello")
    public ResponseEntity<BaseResponse<String>> hello(HttpServletRequest request) {
        String traceId = (String) request.getAttribute("traceId");
        return ResponseUtil.success("Hello from API", "Request successful", traceId);
    }
}

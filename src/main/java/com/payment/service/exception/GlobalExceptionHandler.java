package com.payment.service.exception;

import com.payment.service.dto.BaseResponse;
import com.payment.service.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleAllException(HttpServletRequest httpServletRequest,  Exception e) {
        String traceId = (String) httpServletRequest.getAttribute("traceId");
        return ResponseUtil.internalError("Internal Server Error: " + e.getMessage(), traceId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Object>> handleBadRequest(HttpServletRequest request, IllegalArgumentException e) {
        String traceId = (String) request.getAttribute("traceId");
        return ResponseUtil.badRequest("Bad Request: " + e.getMessage(), traceId);
    }
}

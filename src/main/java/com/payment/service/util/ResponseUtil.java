package com.payment.service.util;

import com.payment.service.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T>ResponseEntity<BaseResponse<T>> success(T data , String message, String traceId) {
        return ResponseEntity.ok(new BaseResponse<>(data, message, HttpStatus.OK.value(), true, traceId));
    }

    public static ResponseEntity<BaseResponse<Object>> error(String message, HttpStatus status, String traceId) {
        return ResponseEntity.status(status)
                .body(new BaseResponse<>(null, message, status.value(), false, traceId));
    }

    public static ResponseEntity<BaseResponse<Object>> badRequest(String message, String traceId) {
        return error(message, HttpStatus.BAD_REQUEST, traceId);
    }

    public static ResponseEntity<BaseResponse<Object>> internalError(String message, String traceId) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR, traceId);
    }
}

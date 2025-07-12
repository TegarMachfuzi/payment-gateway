package com.payment.service.dto;

public class BaseResponse<T> {

    private String traceId;
    private String message;
    private int status;
    private boolean success;
    private T data;

    public BaseResponse(T data, String message, int status, boolean success, String traceId) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.success = success;
        this.traceId = traceId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}

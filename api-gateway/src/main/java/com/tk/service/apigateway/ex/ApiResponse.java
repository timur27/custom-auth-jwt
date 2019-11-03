package com.tk.service.apigateway.ex;

public class ApiResponse {
    private int status;
    private String message;

    public ApiResponse(int status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

package com.senla.exceptions;

import lombok.Data;

@Data
public class ApiResponse {
    private int status;
    private String message;

    public ApiResponse(int status, String message){
        this.status = status;
        this.message = message;
    }
}

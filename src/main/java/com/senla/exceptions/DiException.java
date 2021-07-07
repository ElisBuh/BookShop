package com.senla.exceptions;

public class DiException extends RuntimeException{
    public DiException(String message) {
        super(message);
    }

    public DiException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiException(Throwable cause) {
        super(cause);
    }
}

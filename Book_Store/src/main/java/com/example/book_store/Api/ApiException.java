package com.example.book_store.Api;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}

package com.learning.urlshortener.exception;

// Thrown when a requested resource (a URL, a user, etc.) doesn't exist.
// Mapped to HTTP 404 Not Found in GlobalExceptionHandler.
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

package com.learning.urlshortener.exception;

// Thrown when a user tries to register a username/email that's already taken.
// A custom, meaningfully-named exception is far clearer than throwing a generic
// RuntimeException — both for readability and for how we handle it in the
// Global Exception Handler (mapped to HTTP 409 Conflict).
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}

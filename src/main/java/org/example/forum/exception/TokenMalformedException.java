package org.example.forum.exception;

public class TokenMalformedException extends RuntimeException {
    public TokenMalformedException(String message) {
        super(message);
    }
}

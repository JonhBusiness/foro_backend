package org.example.forum.exception;

public class TopicoAlreadyExistsException extends RuntimeException {
    public TopicoAlreadyExistsException(String message) {
        super(message);
    }
}

package org.example.forum.exception;

public class CursoAlreadyExistsException extends RuntimeException {
    public CursoAlreadyExistsException(String message) {
        super(message);
    }
}
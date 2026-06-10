package com.example.Ats.exception;

public class PasswordProtectedFileException extends RuntimeException {

    public PasswordProtectedFileException(String message) {
        super(message);
    }
}
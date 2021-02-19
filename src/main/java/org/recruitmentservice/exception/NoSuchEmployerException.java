package org.recruitmentservice.exception;

public class NoSuchEmployerException extends RuntimeException {
    public NoSuchEmployerException(String message) {
        super(message);
    }

    public NoSuchEmployerException(Long id) {
        super(String.format("Employer with id=%d not found", id));
    }
}


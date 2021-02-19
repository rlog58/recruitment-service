package org.recruitmentservice.exception;

import java.util.NoSuchElementException;

public class NoSuchEmployeeException extends NoSuchElementException {
    public NoSuchEmployeeException(String message) {
        super(message);
    }
    public NoSuchEmployeeException(Long id) {
        super(String.format("Employee with id=%d not found", id));
    }
}


package org.recruitmentservice.exception;

import java.util.NoSuchElementException;

public class NoSuchResumeException extends NoSuchElementException {
    public NoSuchResumeException(String message) {
        super(message);
    }

    public NoSuchResumeException(Long id) {
        super(String.format("Resume with id=%d not exist", id));
    }
}

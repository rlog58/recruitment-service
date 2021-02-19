package org.recruitmentservice.exception;

import java.util.NoSuchElementException;

public class NoSuchSkillException extends NoSuchElementException {
    public NoSuchSkillException(String message) {
        super(message);
    }
}

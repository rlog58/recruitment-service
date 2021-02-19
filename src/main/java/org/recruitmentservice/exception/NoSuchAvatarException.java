package org.recruitmentservice.exception;

public class NoSuchAvatarException extends RuntimeException{
    public NoSuchAvatarException(Long id) {
        super(String.format("Avatar with id=%d not found", id));
    }
}

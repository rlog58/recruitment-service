package org.recruitmentservice.exception;

public class CannotFindAvatarException extends RuntimeException{
    public CannotFindAvatarException(String message){
        super(message);
    }
}

package org.recruitmentservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({NoSuchEmployerException.class,
            NoSuchEmployeeException.class,
            NoSuchAvatarException.class,
            NoSuchResumeException.class,
            NoSuchVacancyException.class,
            CannotFindAvatarException.class,
            NoSuchSkillException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFoundException(Exception ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorType(HttpStatus.NOT_FOUND.value());
        errorDto.setErrorMessage(ex.getMessage());
        return errorDto;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorType(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setErrorMessage(ex.getMessage());
        return errorDto;
    }
}

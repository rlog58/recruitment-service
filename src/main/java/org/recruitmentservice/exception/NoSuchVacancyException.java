package org.recruitmentservice.exception;

public class NoSuchVacancyException extends RuntimeException {
    public NoSuchVacancyException(String message) {
        super(message);
    }

    public NoSuchVacancyException(Long id) {
        super(String.format("Vacancy with id=%d not exist", id));
    }
}

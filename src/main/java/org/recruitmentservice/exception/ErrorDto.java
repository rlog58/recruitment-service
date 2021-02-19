package org.recruitmentservice.exception;

import lombok.Data;

@Data
public class ErrorDto {
    private Integer errorType;
    private String errorMessage;
}

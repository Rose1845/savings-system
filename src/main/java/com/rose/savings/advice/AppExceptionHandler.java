package com.rose.savings.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public SavingsErrorBuilder handleInvalidArgument(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        List<String> codes = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            errors.add(e.getDefaultMessage());
            codes.add(e.getCode());
            fields.add(e.getField());
        });
        SavingsErrorBuilder errorBuilder = SavingsErrorBuilder.builder()
                .errors(errors)
                .codes(codes)
                .fields(fields)
                .build();
        return errorBuilder;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SavingsException.class)
    public Map<String, String> handleValidientException(SavingsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage() != null ? ex.getMessage() : "Internal server error");
        errorMap.put("metadata-info", ex.getMetadata() != null ? ex.getMetadata() : "");
        errorMap.put("statusCode", ex.getStatusCode() != null ? ex.getStatusCode().toString() : Integer.valueOf("400").toString());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Map<String, String> handleEntityException(SQLIntegrityConstraintViolationException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorCode", ex.getSQLState());
        errorMap.put("message", ex.getMessage());
        errorMap.put("cause", ex.getMessage());
        return errorMap;
    }


}

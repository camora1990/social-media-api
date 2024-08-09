package com.web.services.api.controller;

import com.web.services.api.shared.BusinessException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<CustomResponse<Void>>> handleBusinessException(BusinessException e) {
        logger.error("Business exception occurred", e);
        CustomResponse<Void> errorResponse = CustomResponse.buildError(e.getCode(), e.getMessage(), null);
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<CustomResponse<Void>>> handleValidationException(WebExchangeBindException e) {
        logger.error("Validation exception occurred", e);
        String errors = extractValidationErrors(e);
        CustomResponse<Void> errorResponse = CustomResponse.buildError(400, "Validation failed: " + errors, null);
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Mono<ResponseEntity<CustomResponse<Void>>> handleConstraintViolationException(ConstraintViolationException e) {
        logger.error("Constraint violation exception occurred", e);
        String errors = extractConstraintViolations(e);
        CustomResponse<Void> errorResponse = CustomResponse.buildError(400, "Validation failed: " + errors, null);
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse));
    }

    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<CustomResponse<Void>>> handleUnexpectedException(Throwable e) {
        logger.error("Unexpected error occurred", e);
        CustomResponse<Void> errorResponse = CustomResponse.buildError(500, "Internal Server Error", null);
        return Mono.just(ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse));
    }

    private String extractValidationErrors(WebExchangeBindException validException) {
        return validException.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
    }

    private String extractConstraintViolations(ConstraintViolationException constraintViolationException) {
        return constraintViolationException.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
    }
}

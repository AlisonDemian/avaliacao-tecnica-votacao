package com.sicredi.avaliacaotecnicavotacao.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomExceptionResponse> elementNotFoundExceptionHandler(ElementNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(ElementAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomExceptionResponse> elementNotFoundExceptionHandler(
            ElementAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(BusinessGenericException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomExceptionResponse> businessGenericExceptionHandler(BusinessGenericException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomExceptionResponse(exception.getMessage()));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<ErrorValidationsResponse> errorsResponse = fieldErrors.stream()
                .map( error -> new ErrorValidationsResponse( error.getDefaultMessage(), error.getField()))
                .collect(Collectors.toList());
        return ResponseEntity
                .status(status)
                .body(new CustomExceptionResponse(errorsResponse));
    }

}

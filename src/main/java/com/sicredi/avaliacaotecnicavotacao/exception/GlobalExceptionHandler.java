package com.sicredi.avaliacaotecnicavotacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

//    @Override
//    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
//                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return ResponseEntity
//                .status(status)
//                .body(new CustomExceptionResponse(exception.getAllErrors().toString()));
//    }

}

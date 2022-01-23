package com.sicredi.avaliacaotecnicavotacao.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CustomExceptionResponse {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cause;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parameter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public CustomExceptionResponse(String msg) {
        message = msg;
    }

    public CustomExceptionResponse(String msg, String param) {
        message = msg;
        parameter = param;
    }

    public CustomExceptionResponse(List<String> errors) {
        this.errors = errors;
    }
}

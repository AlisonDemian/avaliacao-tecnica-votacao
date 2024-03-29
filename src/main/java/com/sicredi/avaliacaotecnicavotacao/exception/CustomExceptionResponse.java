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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cause;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String parameter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorValidationsResponse> erros;

    public CustomExceptionResponse(String msg) {
        message = msg;
    }

    public CustomExceptionResponse(String msg, String param) {
        message = msg;
        parameter = param;
    }

    public CustomExceptionResponse(List<ErrorValidationsResponse> errors) {
        this.erros = errors;
    }
}


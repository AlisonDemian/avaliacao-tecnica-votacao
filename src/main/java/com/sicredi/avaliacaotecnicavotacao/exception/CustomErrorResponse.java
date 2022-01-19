package com.sicredi.avaliacaotecnicavotacao.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomErrorResponse {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cause;

    public CustomErrorResponse(String msg) {
        message = msg;
    }
}

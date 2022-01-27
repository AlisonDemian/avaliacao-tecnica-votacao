package com.sicredi.avaliacaotecnicavotacao.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorValidationsResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensagem;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String campo;

    public ErrorValidationsResponse(String mensagem, String campo) {
        this.mensagem = mensagem;
        this.campo = campo;
    }
}

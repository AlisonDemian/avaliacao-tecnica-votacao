package com.sicredi.avaliacaotecnicavotacao.enums;

import lombok.Getter;

@Getter
public enum PautaStatusEnum {

    EM_ABERTO("em aberto"),
    REPROVADA("reprovada"),
    APROVADA("aprovada"),
    EMPATE("empate");

    public final String status;

    PautaStatusEnum(String status) {
        this.status = status;
    }
}

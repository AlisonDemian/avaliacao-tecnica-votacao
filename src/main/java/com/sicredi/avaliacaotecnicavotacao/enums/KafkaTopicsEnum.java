package com.sicredi.avaliacaotecnicavotacao.enums;

import lombok.Getter;

@Getter
public enum KafkaTopicsEnum {

    PAUTA_VOTADA("PAUTA_VOTADA");

    private final String topico;

    KafkaTopicsEnum(String topico) {
        this.topico = topico;
    }
}

package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.PautaRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.PautaResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;

import java.util.List;

import static java.util.Collections.singletonList;

public abstract class PautaUtils {

    public static PautaEntity geraPautaEntity() {
        return PautaEntity.builder()
                .id(1L)
                .tema("Teste")
                .votosSim(1)
                .votosNao(1)
                .build();
    }

    public static PautaResponseDto geraPautaResponse() {
        return PautaResponseDto.builder()
                .id(1L)
                .tema("Teste")
                .build();
    }

    public static PautaRequestDto geraPautaRequest() {
        return PautaRequestDto.builder()
                .tema("Teste")
                .build();
    }

    public static List<PautaEntity> geraListaPautaEntity() {
        return singletonList(geraPautaEntity());
    }

    public static List<PautaResponseDto> geraListaPautaResponse() {
        return singletonList(geraPautaResponse());
    }
}

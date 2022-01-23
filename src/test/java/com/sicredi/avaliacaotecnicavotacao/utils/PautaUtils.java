package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.PautaRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.PautaResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;

import java.util.List;

import static com.sicredi.avaliacaotecnicavotacao.enums.PautaStatusEnum.EM_ABERTO;
import static java.util.Collections.singletonList;

public abstract class PautaUtils {

    public static PautaEntity geraPautaAbertaEntity() {
        return PautaEntity.builder()
                .id(1L)
                .tema("Teste")
                .votosSim(1)
                .votosNao(1)
                .statusVotacao(EM_ABERTO.getStatus())
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
        return singletonList(geraPautaAbertaEntity());
    }

    public static List<PautaResponseDto> geraListaPautaResponse() {
        return singletonList(geraPautaResponse());
    }
}

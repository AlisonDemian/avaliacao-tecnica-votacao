package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.PautaRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.PautaResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

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
                .statusVotacao(EM_ABERTO.getStatus())
                .build();
    }

    public static PautaRequestDto geraPautaRequest() {
        return PautaRequestDto.builder()
                .tema("Teste")
                .build();
    }

    public static Page<PautaEntity> geraPagePautaEntity() {
        return new PageImpl<>(singletonList(geraPautaAbertaEntity()));
    }

    public static Page<PautaResponseDto> geraPagePautaResponse() {
        return new PageImpl<>(singletonList(geraPautaResponse()));
    }
}

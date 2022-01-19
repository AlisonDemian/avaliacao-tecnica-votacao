package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.SessaoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;

import java.time.LocalTime;
import java.util.List;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaResponse;
import static java.util.Collections.singletonList;

public abstract class SessaoUtils {

    public static SessaoEntity geraSessaoEntity() {
        return SessaoEntity.builder()
                .id(1L)
                .tempoVotacao(LocalTime.of(00, 00, 10))
                .pauta(geraPautaEntity())
                .build();
    }

    public static SessaoResponseDto geraSessaoResponse() {
        return SessaoResponseDto.builder()
                .id(1L)
                .tempoVotacao(LocalTime.of(00, 00, 10))
                .pauta(geraPautaResponse())
                .build();
    }

    public static SessaoRequestDto geraSessaoRequest() {
        return SessaoRequestDto.builder()
                .tempoVotacao(LocalTime.of(00, 00, 10))
                .idPauta(1L)
                .build();
    }

    public static List<SessaoEntity> geraListaSessaoEntity() {
        return singletonList(geraSessaoEntity());
    }

    public static List<SessaoResponseDto> geraListaSessaoResponseDto() {
        return singletonList(geraSessaoResponse());
    }


}

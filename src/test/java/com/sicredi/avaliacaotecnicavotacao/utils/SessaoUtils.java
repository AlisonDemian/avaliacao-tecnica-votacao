package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.SessaoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaResponse;
import static java.util.Collections.singletonList;

public abstract class SessaoUtils {

    public static SessaoEntity geraSessaoEntity() {
        return SessaoEntity.builder()
                .id(1L)
                .tempoVotacao(LocalDateTime.of(2022, Month.JANUARY, 20, 13, 0, 0))
                .pauta(geraPautaEntity())
                .build();
    }

    public static SessaoResponseDto geraSessaoResponseDto() {
        return SessaoResponseDto.builder()
                .id(1L)
                .tempoVotacao(LocalDateTime.of(2022, Month.JANUARY, 20, 13, 0, 0))
                .pauta(geraPautaResponse())
                .build();
    }

    public static SessaoRequestDto geraSessaoRequestDto() {
        return SessaoRequestDto.builder()
                .tempoVotacao(LocalDateTime.of(2022, Month.JANUARY, 20, 13, 0, 0))
                .idPauta(1L)
                .build();
    }

    public static List<SessaoEntity> geraListaSessaoEntity() {
        return singletonList(geraSessaoEntity());
    }

    public static List<SessaoResponseDto> geraListaSessaoResponseDto() {
        return singletonList(geraSessaoResponseDto());
    }


}

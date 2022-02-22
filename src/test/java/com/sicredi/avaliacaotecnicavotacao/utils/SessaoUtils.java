package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.SessaoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;

import java.time.LocalDateTime;
import java.util.List;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaAbertaEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaResponse;
import static java.util.Collections.singletonList;

public abstract class SessaoUtils {

    public static SessaoEntity geraSessaoEntity() {
        return SessaoEntity.builder()
                .id(1L)
                .tempoVotacao(LocalDateTime.now().plusYears(1))
                .pauta(geraPautaAbertaEntity())
                .statusAberto(true)
                .build();
    }

    public static SessaoResponseDto geraSessaoResponseDto() {
        return SessaoResponseDto.builder()
                .id(1L)
                .tempoVotacao(LocalDateTime.now().plusYears(1))
                .pauta(geraPautaResponse())
                .statusAberto(true)
                .build();
    }

    public static SessaoRequestDto geraSessaoRequestDto() {
        return SessaoRequestDto.builder()
                .tempoVotacao(LocalDateTime.now().plusYears(1))
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

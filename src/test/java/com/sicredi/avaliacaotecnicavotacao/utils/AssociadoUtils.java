package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;

import java.util.List;

import static java.util.Collections.singletonList;

public abstract class AssociadoUtils {

    public static AssociadoEntity geraEntity() {
        return AssociadoEntity.builder()
                .id(1L)
                .cpf("11111111111")
                .build();
    }

    public static AssociadoResponseDto geraResponseDto() {
        return AssociadoResponseDto.builder()
                .id(1L)
                .cpf("11111111111")
                .build();
    }

    public static AssociadoRequestDto geraRequestDto() {
        return AssociadoRequestDto.builder()
                .cpf("11111111111")
                .build();
    }

    public static List<AssociadoEntity> geraEntityList() {
        return singletonList(geraEntity());
    }

    public static List<AssociadoResponseDto> geraResponseDtoList() {
        return singletonList(geraResponseDto());
    }

}

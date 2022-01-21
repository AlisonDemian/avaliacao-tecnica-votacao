package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;

import java.util.List;

import static java.util.Collections.singletonList;

public abstract class AssociadoUtils {

    public static AssociadoEntity geraAssociadoEntity() {
        return AssociadoEntity.builder()
                .id(1L)
                .cpf("11111111111")
                .build();
    }

    public static AssociadoResponseDto geraAssociadoResponseDto() {
        return AssociadoResponseDto.builder()
                .id(1L)
                .cpf("11111111111")
                .build();
    }

    public static AssociadoRequestDto geraAssociadoRequestDto() {
        return AssociadoRequestDto.builder()
                .cpf("11111111111")
                .build();
    }

    public static List<AssociadoEntity> geraAssociadoEntityList() {
        return singletonList(geraAssociadoEntity());
    }

    public static List<AssociadoResponseDto> geraAssociadoResponseDtoList() {
        return singletonList(geraAssociadoResponseDto());
    }

}

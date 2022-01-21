package com.sicredi.avaliacaotecnicavotacao.utils;

import com.sicredi.avaliacaotecnicavotacao.dto.VotoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.VotoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;

import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.geraAssociadoEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.geraAssociadoResponseDto;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.geraSessaoEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.geraSessaoResponseDto;

public abstract class VotoUtils {

    public static VotoSessaoEntity geraVotoEntity() {
        return VotoSessaoEntity.builder()
                .voto(1)
                .sessao(geraSessaoEntity())
                .associado(geraAssociadoEntity())
                .build();
    }

    public static VotoResponseDto geraVotoSimResponseDto() {
        return VotoResponseDto.builder()
                .voto("sim")
                .sessao(geraSessaoResponseDto())
                .associado(geraAssociadoResponseDto())
                .build();
    }

    public static VotoResponseDto geraVotoNaoResponseDto() {
        return VotoResponseDto.builder()
                .voto("não")
                .sessao(geraSessaoResponseDto())
                .associado(geraAssociadoResponseDto())
                .build();
    }

    public static VotoRequestDto geraVotoRequestDto() {
        return VotoRequestDto.builder()
                .voto(1)
                .idAssociado(1L)
                .idSessao(1L)
                .build();
    }
}

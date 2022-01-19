package com.sicredi.avaliacaotecnicavotacao.converter;

import com.sicredi.avaliacaotecnicavotacao.dto.VotoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.VotoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import org.springframework.stereotype.Service;

@Service
public class VotoConverter {

    private AssociadoConverter associadoConverter;
    private SessaoConverter sessaoConverter;

    public VotoSessaoEntity requestDtoTOEntity(VotoRequestDto request) {
        return VotoSessaoEntity.builder()
                .associado(getAssociado(request))
                .sessao(getSessao(request))
                .voto(request.getVoto())
                .build();
    }

    public VotoResponseDto entityToResponseDto(VotoSessaoEntity entity) {
        return VotoResponseDto.builder()
                .id(entity.getId())
                .associado(associadoConverter.entityToResponseDto(entity.getAssociado()))
                .sessao(sessaoConverter.entityToResponseDto(entity.getSessao()))
                .build();
    }

    private SessaoEntity getSessao(VotoRequestDto request) {
        return SessaoEntity.builder().id(request.getIdSessao()).build();
    }

    private AssociadoEntity getAssociado(VotoRequestDto request) {
        return AssociadoEntity.builder().id(request.getIdAssociado()).build();
    }
}

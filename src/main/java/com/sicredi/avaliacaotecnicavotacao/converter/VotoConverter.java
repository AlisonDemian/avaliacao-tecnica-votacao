package com.sicredi.avaliacaotecnicavotacao.converter;

import com.sicredi.avaliacaotecnicavotacao.dto.VotoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.VotoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VotoConverter {

    private final AssociadoConverter associadoConverter;
    private final SessaoConverter sessaoConverter;

    public VotoSessaoEntity requestDtoToEntity(VotoRequestDto request) {
        return VotoSessaoEntity.builder()
                .associado(getAssociado(request))
                .sessao(getSessao(request))
                .voto(request.getVoto())
                .build();
    }

    public VotoResponseDto entityToResponseDto(VotoSessaoEntity entity) {
        if (entity.getVoto() == 1) {
            return VotoResponseDto.builder()
                    .associado(associadoConverter.entityToResponseDto(entity.getAssociado()))
                    .sessao(sessaoConverter.entityToResponseDto(entity.getSessao()))
                    .voto("sim")
                    .build();
        } else {
            return VotoResponseDto.builder()
                    .associado(associadoConverter.entityToResponseDto(entity.getAssociado()))
                    .sessao(sessaoConverter.entityToResponseDto(entity.getSessao()))
                    .voto("não")
                    .build();
        }

    }

    private SessaoEntity getSessao(VotoRequestDto request) {
        return SessaoEntity.builder().id(request.getIdSessao()).build();
    }

    private AssociadoEntity getAssociado(VotoRequestDto request) {
        return AssociadoEntity.builder().id(request.getIdAssociado()).build();
    }
}

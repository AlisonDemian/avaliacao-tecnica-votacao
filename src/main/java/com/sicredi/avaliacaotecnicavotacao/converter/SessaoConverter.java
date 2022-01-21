package com.sicredi.avaliacaotecnicavotacao.converter;

import com.sicredi.avaliacaotecnicavotacao.dto.SessaoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SessaoConverter {

    private final PautaConverter pautaConverter;

    public SessaoEntity requestToEntity(SessaoRequestDto request) {
        return SessaoEntity.builder()
                .tempoVotacao(request.getTempoVotacao())
                .pauta(pautaBuilder(request.getIdPauta()))
                .statusAberto(true)
                .build();
    }

    public SessaoResponseDto entityToResponseDto(SessaoEntity entity) {
        return SessaoResponseDto.builder()
                .id(entity.getId())
                .tempoVotacao(entity.getTempoVotacao())
                .pauta(pautaConverter.entityToResponseDto(entity.getPauta()))
                .statusAberto(entity.isStatusAberto())
                .build();
    }

    public List<SessaoResponseDto> entityListToResponseDtoList(List<SessaoEntity> entities) {
        return entities.stream()
                .map((entity) -> entityToResponseDto(entity))
                .collect(Collectors.toList());
    }

    public PautaEntity pautaBuilder(Long id) {
        return PautaEntity.builder()
                .id(id)
                .build();
    }
}

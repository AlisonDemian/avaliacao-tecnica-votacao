package com.sicredi.avaliacaotecnicavotacao.converter;

import com.sicredi.avaliacaotecnicavotacao.dto.PautaRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.PautaResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.enums.PautaStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PautaConverter {

    public PautaEntity requestDtoToEntity(PautaRequestDto request) {
        return PautaEntity.builder()
                .tema(request.getTema())
                .statusVotacao(PautaStatusEnum.EM_ABERTO.getStatus())
                .votosNao(0)
                .votosSim(0)
                .build();
    }

    public PautaResponseDto entityToResponseDto(PautaEntity entity) {
        return PautaResponseDto.builder()
                .id(entity.getId())
                .tema(entity.getTema())
                .statusVotacao(entity.getStatusVotacao())
                .build();
    }

    public List<PautaResponseDto> entityListToResponseDtoList(List<PautaEntity> entities) {
        return entities.stream()
                .map(this::entityToResponseDto)
                .collect(Collectors.toList());
    }
}

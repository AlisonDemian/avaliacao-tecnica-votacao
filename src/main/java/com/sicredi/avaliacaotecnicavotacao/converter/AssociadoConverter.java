package com.sicredi.avaliacaotecnicavotacao.converter;

import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociadoConverter {

    public AssociadoEntity requestDtoToEntity(AssociadoRequestDto request) {
        return AssociadoEntity.builder()
                .cpf(request.getCpf())
                .build();
    }

    public AssociadoResponseDto entityToResponseDto(AssociadoEntity entity) {
        return AssociadoResponseDto.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .build();
    }

    public List<AssociadoResponseDto> entityListToResponseDtoList(List<AssociadoEntity> entities) {
        return entities.stream()
                .map(entity -> entityToResponseDto(entity))
                .collect(Collectors.toList());
    }
}

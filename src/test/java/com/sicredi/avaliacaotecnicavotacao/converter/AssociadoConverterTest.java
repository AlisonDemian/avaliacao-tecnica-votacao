package com.sicredi.avaliacaotecnicavotacao.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AssociadoConverterTest {

    @InjectMocks
    private AssociadoConverter converter;

    @Test
    void requestDtoToEntity() {

        assertEquals(geraEntity().getCpf(), converter.requestDtoToEntity(geraRequestDto()).getCpf());
    }

    @Test
    void entityToResponseDto() {
        assertEquals(geraResponseDto().getCpf(), converter.entityToResponseDto(geraEntity()).getCpf());

    }

    @Test
    void entityListToResponseDtoList() {
        assertEquals(geraResponseDtoList().get(0).getCpf(),
                converter.entityListToResponseDtoList(geraEntityList()).get(0).getCpf());
    }
}
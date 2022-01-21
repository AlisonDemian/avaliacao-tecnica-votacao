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

        assertEquals(geraAssociadoEntity().getCpf(), converter.requestDtoToEntity(geraAssociadoRequestDto()).getCpf());
    }

    @Test
    void entityToResponseDto() {
        assertEquals(geraAssociadoResponseDto().getCpf(), converter.entityToResponseDto(geraAssociadoEntity()).getCpf());

    }

    @Test
    void entityListToResponseDtoList() {
        assertEquals(geraAssociadoResponseDtoList().get(0).getCpf(),
                converter.entityListToResponseDtoList(geraAssociadoEntityList()).get(0).getCpf());
    }
}
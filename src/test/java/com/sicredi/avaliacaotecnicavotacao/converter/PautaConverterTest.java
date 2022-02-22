package com.sicredi.avaliacaotecnicavotacao.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PautaConverterTest {

    @InjectMocks
    private PautaConverter converter;

    @Test
    void quandoConverter_requestDtoToEntity_retornaSucesso() {
        assertEquals(geraPautaAbertaEntity().getTema(), converter.requestDtoToEntity(geraPautaRequest()).getTema());
    }

    @Test
    void quandoConverter_entityToResponseDto_retornaSucesso() {
        assertEquals(geraPautaResponse().getTema(), converter.entityToResponseDto(geraPautaAbertaEntity()).getTema());
    }

    @Test
    void quandoConverter_entityPageToResponseDtoPage_retornaSucesso() {
        assertEquals(
                geraPagePautaResponse().getContent().get(0).getTema(),
                converter.entityPageToResponseDtoPage(geraPagePautaEntity()).getContent().get(0).getTema()
        );
    }
}
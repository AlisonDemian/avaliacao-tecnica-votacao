package com.sicredi.avaliacaotecnicavotacao.converter;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaResponse;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoConverterTest {

    @InjectMocks
    private SessaoConverter converter;

    @Mock
    private PautaConverter pautaConverter;

    @Test
    void quandoConverter_requestToEntity_retornaSucesso() {
        assertAll(
                () -> assertEquals(PautaEntity.class,
                        converter.requestToEntity(geraSessaoRequest()).getPauta().getClass()),

                () -> assertEquals(geraSessaoEntity().getTempoVotacao(),
                        converter.requestToEntity(geraSessaoRequest()).getTempoVotacao()),

                () -> assertEquals(geraSessaoEntity().getPauta().getId(),
                        converter.requestToEntity(geraSessaoRequest()).getPauta().getId())
        );
    }

    @Test
    void quandoConverter_entityToResponseDto_retornaSucesso() {
        when(pautaConverter.entityToResponseDto(any(PautaEntity.class)))
                .thenReturn(geraPautaResponse());

        SessaoEntity entity = geraSessaoEntity();
        assertAll(
                () -> assertEquals(geraSessaoResponse().getId(),
                        converter.entityToResponseDto(entity).getId()),

                () -> assertEquals(geraSessaoResponse().getTempoVotacao(),
                        converter.entityToResponseDto(entity).getTempoVotacao()),

                () -> assertEquals(geraSessaoResponse().getPauta().getId(),
                        converter.entityToResponseDto(entity).getPauta().getId())
        );
    }

    @Test
    void quandoConverter_entityListToResponseDtoList_retornaSucesso() {
        when(pautaConverter.entityToResponseDto(any(PautaEntity.class)))
                .thenReturn(geraPautaResponse());

        assertAll(
                () -> assertEquals(geraListaSessaoResponseDto().get(0).getId(),
                        converter.entityListToResponseDtoList(geraListaSessaoEntity()).get(0).getId()),

                () -> assertEquals(geraListaSessaoResponseDto().get(0).getTempoVotacao(),
                        converter.entityListToResponseDtoList(geraListaSessaoEntity()).get(0).getTempoVotacao()),

                () -> assertEquals(geraListaSessaoResponseDto().get(0).getPauta().getId(),
                        converter.entityListToResponseDtoList(geraListaSessaoEntity()).get(0).getPauta().getId())
        );
    }
}
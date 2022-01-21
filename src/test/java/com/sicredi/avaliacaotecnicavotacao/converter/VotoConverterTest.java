package com.sicredi.avaliacaotecnicavotacao.converter;

import com.sicredi.avaliacaotecnicavotacao.dto.VotoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.geraAssociadoResponseDto;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.geraSessaoResponseDto;
import static com.sicredi.avaliacaotecnicavotacao.utils.VotoUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoConverterTest {

    @InjectMocks
    private VotoConverter converter;

    @Mock
    private AssociadoConverter associadoConverter;

    @Mock
    private SessaoConverter sessaoConverter;

    @Test
    void quandoConverter_requestDtoToEntity_retornaSucesso() {
        assertAll(
                () -> assertThat(converter.requestDtoToEntity(geraVotoRequestDto()))
                        .isInstanceOf(VotoSessaoEntity.class),

                () -> assertEquals(geraVotoEntity().getVoto(),
                        converter.requestDtoToEntity(geraVotoRequestDto()).getVoto()),

                () -> assertEquals(geraVotoEntity().getAssociado().getId(),
                        converter.requestDtoToEntity(geraVotoRequestDto()).getAssociado().getId()),

                () -> assertEquals(geraVotoEntity().getSessao().getId(),
                        converter.requestDtoToEntity(geraVotoRequestDto()).getSessao().getId())
        );
    }

    @Test
    void quandoConverter_entityToResponseDto_comVotoSim_retornaSucesso() {
        VotoSessaoEntity votoSessaoEntity = geraVotoEntity();

        when(associadoConverter.entityToResponseDto(any(AssociadoEntity.class)))
                .thenReturn(geraAssociadoResponseDto());
        when(sessaoConverter.entityToResponseDto(any(SessaoEntity.class)))
                .thenReturn(geraSessaoResponseDto());

        assertAll(
                () -> assertThat(converter.entityToResponseDto(geraVotoEntity()))
                        .isInstanceOf(VotoResponseDto.class),

                () -> assertEquals("sim", converter.entityToResponseDto(geraVotoEntity()).getVoto()),

                () -> assertEquals(geraVotoSimResponseDto().getAssociado().getCpf(),
                        converter.entityToResponseDto(votoSessaoEntity).getAssociado().getCpf()),

                () -> assertEquals(geraVotoSimResponseDto().getSessao().getPauta().getTema(),
                        converter.entityToResponseDto(votoSessaoEntity).getSessao().getPauta().getTema()),

                () -> assertEquals(geraVotoSimResponseDto().getSessao().getTempoVotacao(),
                        converter.entityToResponseDto(votoSessaoEntity).getSessao().getTempoVotacao())
        );
    }
}
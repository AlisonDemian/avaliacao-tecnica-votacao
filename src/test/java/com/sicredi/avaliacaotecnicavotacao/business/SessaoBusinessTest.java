package com.sicredi.avaliacaotecnicavotacao.business;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.enums.PautaStatusEnum;
import com.sicredi.avaliacaotecnicavotacao.exception.BusinessGenericException;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaAbertaEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.geraSessaoEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoBusinessTest {

    @InjectMocks
    private SessaoBusiness business;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private PautaService pautaService;

    @Test
    void quandoCriarSessao_retornaSucesso() {
        PautaEntity pautaEntity = geraPautaAbertaEntity();
        SessaoEntity sessaoEntity = geraSessaoEntity();

        when(pautaService.buscarPorId(anyLong()))
                .thenReturn(pautaEntity);
        when(sessaoService.criar(any(SessaoEntity.class)))
                .thenReturn(sessaoEntity);

        sessaoService.verificaSePautaNaoTemSessao(pautaEntity.getId());

        verify(sessaoService, times(1)).verificaSePautaNaoTemSessao(1L);
        assertThat(business.criar(sessaoEntity))
                .isNotNull();
    }

    @Test
    void quandoCriarSessao_throwException() {
        PautaEntity pautaEntity = geraPautaAbertaEntity();
        pautaEntity.setStatusVotacao(PautaStatusEnum.APROVADA.getStatus());

        when(pautaService.buscarPorId(anyLong()))
                .thenReturn(pautaEntity);

        SessaoEntity sessaoEntity = geraSessaoEntity();
        sessaoEntity.setPauta(pautaEntity);
        assertThatThrownBy(() -> business.criar(sessaoEntity))
                .isInstanceOf(BusinessGenericException.class)
                .hasMessage(String.format("pauta %d ja foi votada", pautaEntity.getId()));
    }
}
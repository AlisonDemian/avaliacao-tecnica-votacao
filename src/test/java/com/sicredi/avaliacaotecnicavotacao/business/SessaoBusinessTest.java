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

import java.time.LocalDateTime;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaAbertaEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.geraListaSessaoEntity;
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

    @Test
    void quandoListar_retornaSucesso() {
        when(sessaoService.listar())
                .thenReturn(geraListaSessaoEntity());

        assertThat(business.listar())
                .isNotNull()
                .hasSize(1)
                .hasOnlyElementsOfType(SessaoEntity.class);
    }

    @Test
    void quandoBuscarPorId_retornaSucesso() {
        Long id = anyLong();
        when(sessaoService.buscarPorId(id))
                .thenReturn(geraSessaoEntity());

        assertThat(business.buscarPorId(id))
                .isNotNull()
                .isInstanceOf(SessaoEntity.class);
    }

    @Test
    void quandoAtualizar_retornaSucesso() {
        LocalDateTime tempoLimite = LocalDateTime.now().plusYears(1);
        SessaoEntity entityAtualizada = geraSessaoEntity();
        entityAtualizada.setTempoVotacao(tempoLimite);

        Long id = anyLong();
        when(sessaoService.atualizar(id, any(LocalDateTime.class)))
                .thenReturn(entityAtualizada);

        assertThat(business.atualizar(id, tempoLimite))
                .isNotNull()
                .hasFieldOrPropertyWithValue("tempoVotacao", tempoLimite);
    }

    @Test
    void quandoDeletar_retornaSucesso() {
        Long id = anyLong();
        business.deletar(id);

        verify(sessaoService, times(1)).deletar(id);
    }
}
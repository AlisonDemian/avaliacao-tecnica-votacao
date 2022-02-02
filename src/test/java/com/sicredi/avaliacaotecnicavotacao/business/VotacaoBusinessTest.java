package com.sicredi.avaliacaotecnicavotacao.business;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.geraAssociadoEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaAbertaEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.*;
import static com.sicredi.avaliacaotecnicavotacao.utils.VotoUtils.geraVotoEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotacaoBusinessTest {

    @InjectMocks
    private VotacaoBusiness business;

    @Mock
    private SessaoService sessaoService;

    @Mock
    private AssociadoService associadoService;

    @Mock
    private PautaService pautaService;

    @Mock
    private VotoService votoService;

    @Mock
    public CpfApiValidationService cpfApiValidationService;

    @Test
    void quandoVotar_persisteVotoAtualizadoComSucesso() {
        VotoSessaoEntity votoEntity= geraVotoEntity();

        votoService.verificaVotoMultiplo(votoEntity);

        when(sessaoService.buscarPorId(anyLong()))
                .thenReturn(geraSessaoEntity());
        when(associadoService.buscarPorId(anyLong()))
                .thenReturn(geraAssociadoEntity());
        when(pautaService.atualizar(anyLong(), any(PautaEntity.class)))
                .thenReturn(geraPautaAbertaEntity());
        when(votoService.salvar(any(VotoSessaoEntity.class)))
                .thenReturn(votoEntity);

        assertThat(business.votar(geraVotoEntity()))
                .isInstanceOf(VotoSessaoEntity.class)
                .isNotNull();
        verify(cpfApiValidationService, times(1)).validaApiStatusCpf(votoEntity.getAssociado().getCpf());
    }
}
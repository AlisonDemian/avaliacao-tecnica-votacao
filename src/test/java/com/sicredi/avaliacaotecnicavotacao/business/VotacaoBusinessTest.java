package com.sicredi.avaliacaotecnicavotacao.business;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.service.AssociadoService;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils;
import com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils;
import com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.VotoUtils.geraVotoEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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

    @Test
    void quandoVotar_persisteVotoAtualizadoComSucesso() {
        when(sessaoService.buscarPorId(anyLong()))
                .thenReturn(SessaoUtils.geraSessaoEntity());
        when(associadoService.buscarPorId(anyLong()))
                .thenReturn(AssociadoUtils.geraAssociadoEntity());
        when(pautaService.atualizar(anyLong(), any(PautaEntity.class)))
                .thenReturn(PautaUtils.geraPautaEntity());

        assertThat(business.votar(geraVotoEntity()))
                .isInstanceOf(VotoSessaoEntity.class)
                .hasNoNullFieldsOrPropertiesExcept("id");
    }
}
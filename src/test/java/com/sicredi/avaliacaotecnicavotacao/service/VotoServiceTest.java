package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.BusinessGenericException;
import com.sicredi.avaliacaotecnicavotacao.repository.VotoSessaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sicredi.avaliacaotecnicavotacao.utils.VotoUtils.geraVotoEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService service;

    @Mock
    private VotoSessaoRepository repository;

    @Test
    void quandoSalvarVoto_retornaSucesso() {
        when(repository.save(any(VotoSessaoEntity.class)))
                .thenReturn(geraVotoEntity());

        assertThat(service.salvar(geraVotoEntity()))
                .isInstanceOf(VotoSessaoEntity.class)
                .isNotNull()
                .hasFieldOrPropertyWithValue("voto", 1);
    }

    @Test
    void quandoVerificaVotoMultiplo_false_retornaSucesso() {
        when(repository.verificaVotoMultiplo(anyLong(), anyLong()))
                .thenReturn(false);

        service.verificaVotoMultiplo(geraVotoEntity());

        verify(repository, times(1)).verificaVotoMultiplo(anyLong(), anyLong());
    }

    @Test
    void quandoVerificaVotoMultiplo_true_throwException() {
        when(repository.verificaVotoMultiplo(anyLong(), anyLong()))
                .thenReturn(true);

        assertThatThrownBy(() -> service.verificaVotoMultiplo(geraVotoEntity()))
                .isInstanceOf(BusinessGenericException.class)
                .hasMessage(String.format("Associado %d já votou nesta sessão.", 1L));

    }

    @Test
    void quandoDeletarVotacoes_retornaSucesso() {
        service.deletarVotacoes(anyLong());

        verify(repository, times(1)).deleteBySessaoId(anyLong());
    }
}
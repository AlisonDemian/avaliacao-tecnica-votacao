package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.SessaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.geraListaSessaoEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.geraSessaoEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService service;

    @Mock
    private SessaoRepository repository;

    @Test
    void quandoCriar_retornaSucesso() {
        when(repository.save(any(SessaoEntity.class)))
                .thenReturn(geraSessaoEntity());

        assertNotNull(service.criar(geraSessaoEntity()));
    }

    @Test
    void quandoListar_retornaSucesso() {
        when(repository.findAll())
                .thenReturn(geraListaSessaoEntity());

        assertThat(service.listar())
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoBuscarPorId_retornaSucesso() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(geraSessaoEntity()));

        assertNotNull(service.buscarPorId(anyLong()));

    }

    @Test
    void quandoBuscarPorId_throwException() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(anyLong()))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessage("Sessao não encontrada");
    }

    @Test
    void quandoAtualizar_retornaSucesso() {
        SessaoEntity entityAtualiza = geraSessaoEntity();
        entityAtualiza.setTempoVotacao(LocalDateTime.of(2022, Month.JANUARY, 20, 13, 0, 0));
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.of(geraSessaoEntity()));
        when(repository.save(any(SessaoEntity.class)))
                .thenReturn(entityAtualiza);

        assertThat(service.atualizar(id, entityAtualiza.getTempoVotacao()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("tempoVotacao",
                        LocalDateTime.of(2022, Month.JANUARY, 20, 13, 0, 0));
    }

    @Test
    void quandoDeletar_retornaSucesso() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.of(geraSessaoEntity()));

        service.deletar(id);
        verify(repository, times(1)).deleteById(id);
    }
}
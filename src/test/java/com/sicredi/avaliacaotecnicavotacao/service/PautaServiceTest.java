package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementAlreadyExistsException;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraListaPautaEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.geraPautaAbertaEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService service;

    @Mock
    private PautaRepository repository;

    @Test
    void quandoCriarPauta_retornaSucesso() {
        PautaEntity entity = geraPautaAbertaEntity();

        when(repository.save(any(PautaEntity.class)))
                .thenReturn(entity);

        assertNotNull(service.criar(entity));
    }

    @Test
    void quandoListarPauta_retornaSucesso() {
        when(repository.findAll())
                .thenReturn(geraListaPautaEntity());

        assertThat(service.listar())
                .isNotNull()
                .hasOnlyElementsOfType(PautaEntity.class)
                .hasSize(1);
    }

    @Test
    void quandoBuscarPorId_retornaSucesso() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.of(geraPautaAbertaEntity()));

        assertNotNull(service.buscarPorId(id));
    }

    @Test
    void quandoBuscarPorId_retornaException() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessage("Pauta não encontrada");
    }

    @Test
    void quandoBuscarPorTema_false_retornaSucesso() {
        String tema = anyString();
        when(repository.buscaPorTema(tema))
                .thenReturn(false);

        service.buscarPorTema(tema);

        verify(repository, times(1)).buscaPorTema(tema);
    }

    @Test
    void quandoBuscarPorTema_true_throwException() {
        String tema = anyString();
        when(repository.buscaPorTema(tema))
                .thenReturn(true);

        assertThatThrownBy(() -> service.buscarPorTema(tema))
                .isInstanceOf(ElementAlreadyExistsException.class)
                .hasMessage(String.format("Pauta com tema '%s' ja existe", tema));
    }

    @Test
    void quandoAtualizar_retornaSucesso() {
        PautaEntity entity = geraPautaAbertaEntity();
        entity.setTema("teste atualizado");

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(geraPautaAbertaEntity()));

        when(repository.save(any(PautaEntity.class)))
                .thenReturn(entity);

        assertThat(service.atualizar(anyLong(), entity))
                .isNotNull()
                .hasFieldOrPropertyWithValue("tema", "teste atualizado");

    }


    @Test
    void quandoAtualizarPautaComSessaoEncerrada_retornaSucesso() {
        Long id = anyLong();
        PautaEntity pautaEntity = geraPautaAbertaEntity();
        String status = pautaEntity.getStatusVotacao();

        when(repository.findById(id))
                .thenReturn(Optional.of(pautaEntity));

        service.atualizaPautaComSessaoEncerrada(id, status);

        verify(repository, times(1)).atualizaPautaComSessaoEncerrada(id, status);
    }

    @Test
    void quandoDeletar_retornaSucesso() {

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(geraPautaAbertaEntity()));

        service.deletar(anyLong());

        verify(repository, times(1)).deleteById(anyLong());
    }
}
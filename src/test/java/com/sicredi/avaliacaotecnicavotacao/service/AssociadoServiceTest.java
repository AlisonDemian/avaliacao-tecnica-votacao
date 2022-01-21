package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.geraAssociadoEntity;
import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.geraAssociadoEntityList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    @InjectMocks
    private AssociadoService service;

    @Mock
    private AssociadoRepository repository;

    @Test
    void quandoCriarAssociado_retornaSucesso() {
        AssociadoEntity entity = geraAssociadoEntity();

        when(repository.save(any(AssociadoEntity.class)))
                .thenReturn(entity);

        assertNotNull(service.criar(entity));
    }

    @Test
    void quandoListarAssociado_retornaSucesso() {
        when(repository.findAll())
                .thenReturn(geraAssociadoEntityList());

        assertThat(service.listar())
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoBuscarPorId_retornaSucesso() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(geraAssociadoEntity()));

        assertThat(service.buscarPorId(anyLong()))
                .isNotNull()
                .hasFieldOrPropertyWithValue("cpf", "11111111111");
    }

    @Test
    void quandoBuscarPorId_throwException() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(anyLong()))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessage("Associado não encontrado");
    }

    @Test
    void quandoAtualizar_retornaSucesso() {
        AssociadoEntity entityAtualizada = geraAssociadoEntity();
        entityAtualizada.setCpf("22222222222");

        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(geraAssociadoEntity()));
        when(repository.save(any(AssociadoEntity.class)))
                .thenReturn(entityAtualizada);

        assertThat(service.atualizar(anyLong(), entityAtualizada))
                .isNotNull()
                .hasFieldOrPropertyWithValue("cpf", "22222222222");
    }

    @Test
    void quandoDeletar_retornaSucesso() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(geraAssociadoEntity()));

        service.deletar(anyLong());

        verify(repository, times(1)).deleteById(anyLong());
    }
}
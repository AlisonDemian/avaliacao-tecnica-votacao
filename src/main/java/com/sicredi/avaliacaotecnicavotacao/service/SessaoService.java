package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementAlreadyExistsException;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SessaoService {

    private final SessaoRepository repository;

    public SessaoEntity criar(SessaoEntity entity) {
        return repository.save(entity);
    }

    public List<SessaoEntity> listar() {
        return repository.findAll();
    }

    public SessaoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Sessao não encontrada"));
    }

    public List<SessaoEntity> listarSessaoStatusAberto() {
        return repository.findByStatusAberto(true);
    }


    public void verificaSePautaNaoTemSessao(Long idPauta) {
        if (!repository.verificaSePautaNaoTemSessao(idPauta)) {
            throw new ElementAlreadyExistsException(String.format("pauta %d ja possui sessão", idPauta));
        }
    }

    public SessaoEntity atualizar(Long id, SessaoEntity entityAtualizada) {
        buscarPorId(id);
        entityAtualizada.setId(id);
        return repository.save(entityAtualizada);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}

package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementAlreadyExistsException;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PautaService {

    private final PautaRepository repository;

    public PautaEntity criar(PautaEntity entity) {
        buscarPorTema(entity.getTema());
        return repository.save(entity);
    }

    public Page<PautaEntity> listar(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public PautaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Pauta não encontrada"));
    }

    public void buscarPorTema(String tema) {
        if (repository.buscaPorTema(tema)) {
            throw new ElementAlreadyExistsException(String.format("Pauta com tema '%s' ja existe", tema));
        }
    }

    public PautaEntity atualizar(Long id, PautaEntity entityAtualizada) {
        buscarPorId(id);
        entityAtualizada.setId(id);

        return repository.save(entityAtualizada);
    }

    public void atualizaPautaComSessaoEncerrada(Long id, String status) {
        buscarPorId(id);
        repository.atualizaPautaComSessaoEncerrada(id, status);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}

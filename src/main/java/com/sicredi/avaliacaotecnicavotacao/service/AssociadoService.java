package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssociadoService {

    private final AssociadoRepository repository;

    public AssociadoEntity criar(AssociadoEntity entity) {
        return repository.save(entity);
    }

    public List<AssociadoEntity> listar() {
        return repository.findAll();
    }

    public AssociadoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Associado não encontrado"));
    }

    public AssociadoEntity atualizar(Long id, AssociadoEntity entityAtualizada) {
        buscarPorId(id);
        entityAtualizada.setId(id);
        return repository.save(entityAtualizada);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}

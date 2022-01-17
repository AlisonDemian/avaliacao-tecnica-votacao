package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PautaService {

    private final PautaRepository repository;

    public PautaEntity criar(PautaEntity entity) {
        return repository.save(entity);
    }

    public List<PautaEntity> listar() {
        return repository.findAll();
    }

    public PautaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("id inexistente.")); //temporaria ate criar as exceçoes
    }

    public PautaEntity atualizar(Long id, PautaEntity entityAtualizada) {
        buscarPorId(id);
        entityAtualizada.setId(id);

        return repository.save(entityAtualizada);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}

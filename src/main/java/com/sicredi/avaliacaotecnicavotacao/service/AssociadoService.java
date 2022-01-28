package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementAlreadyExistsException;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssociadoService {

    private final CpfApiValidationService cpfValidation;
    private final AssociadoRepository repository;

    public AssociadoEntity criar(AssociadoEntity entity) {
        cpfValidation.validaApiStatusCpf(entity.getCpf());
        verificaPorCpf(entity.getCpf());
        return repository.save(entity);
    }

    public List<AssociadoEntity> listar() {
        return repository.findAll();
    }

    public AssociadoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(String.format("Associado com id %d não encontrado", id)));
    }

    public void verificaPorCpf(String cpf) {
        if (repository.buscarPorCpf(cpf)) {
            throw new ElementAlreadyExistsException(String.format("Associado com o CPF %s já registrado", cpf));
        }
    }

    public AssociadoEntity atualizar(Long id, AssociadoEntity entityAtualizada) {
        cpfValidation.validaApiStatusCpf(entityAtualizada.getCpf());
        buscarPorId(id);
        verificaPorCpf(entityAtualizada.getCpf());
        entityAtualizada.setId(id);
        return repository.save(entityAtualizada);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}

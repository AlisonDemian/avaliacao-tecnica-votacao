package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.BusinessGenericException;
import com.sicredi.avaliacaotecnicavotacao.repository.VotoSessaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VotoService {

    private final VotoSessaoRepository repository;

    public VotoSessaoEntity salvar(VotoSessaoEntity entity) {
        return repository.save(entity);
    }

    public void verificaVotoMultiplo(Long idAssociado, Long idSessao) {
        if (repository.verificaVotoMultiplo(idAssociado, idSessao)) {
            throw new BusinessGenericException(String.format("Associado %d já votou nesta sessão.", idAssociado));
        }
    }

    public void deletarVotacoes(Long idSessao) {
        repository.deleteBySessaoId(idSessao);
    }
}
